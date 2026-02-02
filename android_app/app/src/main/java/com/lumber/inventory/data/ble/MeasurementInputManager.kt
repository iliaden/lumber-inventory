package com.lumber.inventory.data.ble

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

/**
 * Represents the three dimensions being measured for a lumber piece.
 */
enum class MeasurementSlot {
    LENGTH,
    WIDTH,
    THICKNESS
}

/**
 * State for collecting 3 measurements from a Reekon device.
 */
data class MeasurementInputState(
    val length: ReekonMeasurement? = null,
    val width: ReekonMeasurement? = null,
    val thickness: ReekonMeasurement? = null,
    val currentSlot: MeasurementSlot = MeasurementSlot.LENGTH,
    val lastMeasurement: ReekonMeasurement? = null,
    val isComplete: Boolean = false
) {
    val lengthInches: Double? get() = length?.positionInches
    val widthInches: Double? get() = width?.positionInches
    val thicknessInches: Double? get() = thickness?.positionInches

    val lengthDisplay: String get() = length?.positionFeetInches ?: "--"
    val widthDisplay: String get() = width?.positionFeetInches ?: "--"
    val thicknessDisplay: String get() = thickness?.positionFeetInches ?: "--"

    val filledSlots: Int get() = listOfNotNull(length, width, thickness).size
}

/**
 * Manager for handling 3-measurement input from Reekon devices.
 *
 * Features:
 * - Collects 3 sequential measurements (length, width, thickness)
 * - Detects and ignores duplicate back-to-back measurements
 * - Allows overwriting any slot before confirmation
 */
@Singleton
class MeasurementInputManager @Inject constructor() {

    companion object {
        /**
         * Tolerance in micrometers for considering two measurements as identical.
         * 1/32" = 793.75 μm, so we use ~400 μm (half of smallest common fraction)
         */
        private const val DUPLICATE_TOLERANCE_UM = 400

        /**
         * Time window in milliseconds for considering measurements as "back-to-back".
         * If two measurements come within this window and have same value, treat as duplicate.
         */
        private const val DUPLICATE_TIME_WINDOW_MS = 2000L
    }

    private val _state = MutableStateFlow(MeasurementInputState())
    val state: StateFlow<MeasurementInputState> = _state.asStateFlow()

    /**
     * Process a new measurement from the Reekon device.
     * Automatically detects duplicates and advances to next slot.
     *
     * @return true if measurement was accepted (not a duplicate), false if ignored
     */
    fun processMeasurement(measurement: ReekonMeasurement): Boolean {
        val currentState = _state.value

        // Check for duplicate (back-to-back identical measurement)
        if (isDuplicate(measurement, currentState.lastMeasurement)) {
            return false
        }

        // Update the current slot with the new measurement
        val newState = when (currentState.currentSlot) {
            MeasurementSlot.LENGTH -> currentState.copy(
                length = measurement,
                currentSlot = MeasurementSlot.WIDTH,
                lastMeasurement = measurement
            )
            MeasurementSlot.WIDTH -> currentState.copy(
                width = measurement,
                currentSlot = MeasurementSlot.THICKNESS,
                lastMeasurement = measurement
            )
            MeasurementSlot.THICKNESS -> currentState.copy(
                thickness = measurement,
                isComplete = true,
                lastMeasurement = measurement
            )
        }

        _state.value = newState
        return true
    }

    /**
     * Check if a measurement is a duplicate of the last one.
     * A duplicate is defined as:
     * - Within DUPLICATE_TOLERANCE_UM of the previous measurement
     * - Received within DUPLICATE_TIME_WINDOW_MS of the previous measurement
     */
    private fun isDuplicate(new: ReekonMeasurement, last: ReekonMeasurement?): Boolean {
        if (last == null) return false

        val timeDiff = new.timestamp - last.timestamp
        if (timeDiff > DUPLICATE_TIME_WINDOW_MS) return false

        val positionDiff = abs(new.positionUm - last.positionUm)
        return positionDiff <= DUPLICATE_TOLERANCE_UM
    }

    /**
     * Manually set the current slot to measure.
     * Useful when user wants to re-measure a specific dimension.
     */
    fun setCurrentSlot(slot: MeasurementSlot) {
        _state.value = _state.value.copy(
            currentSlot = slot,
            isComplete = false
        )
    }

    /**
     * Clear a specific measurement slot.
     */
    fun clearSlot(slot: MeasurementSlot) {
        val currentState = _state.value
        _state.value = when (slot) {
            MeasurementSlot.LENGTH -> currentState.copy(
                length = null,
                isComplete = false
            )
            MeasurementSlot.WIDTH -> currentState.copy(
                width = null,
                isComplete = false
            )
            MeasurementSlot.THICKNESS -> currentState.copy(
                thickness = null,
                isComplete = false
            )
        }
    }

    /**
     * Reset all measurements and start fresh.
     */
    fun reset() {
        _state.value = MeasurementInputState()
    }

    /**
     * Mark measurements as complete even if not all slots are filled.
     */
    fun markComplete() {
        _state.value = _state.value.copy(isComplete = true)
    }

    /**
     * Get the measurements as a triple of inches values.
     * Returns null values for unfilled slots.
     */
    fun getMeasurementsInches(): Triple<Double?, Double?, Double?> {
        val s = _state.value
        return Triple(s.lengthInches, s.widthInches, s.thicknessInches)
    }

    /**
     * Check if all three measurements are filled.
     */
    fun hasAllMeasurements(): Boolean {
        val s = _state.value
        return s.length != null && s.width != null && s.thickness != null
    }
}
