package com.lumber.inventory.data.ble

/**
 * Represents a discovered Reekon BLE device.
 */
data class ReekonDevice(
    val name: String,
    val address: String,
    val deviceType: ReekonDeviceType,
    val deviceId: Long,
    val rssi: Int
)

/**
 * Reekon device types from the BLE SDK.
 * device_type mapping from manufacturer data.
 */
enum class ReekonDeviceType(val code: Int, val displayName: String) {
    NULL(0, "Unknown"),
    T1(1, "T1 Tomahawk"),
    T1M_16FT(2, "T1M Utility 16ft"),
    T1M_25FT(3, "T1M Utility 25ft");

    companion object {
        fun fromCode(code: Int): ReekonDeviceType {
            return entries.find { it.code == code } ?: NULL
        }
    }
}

/**
 * Represents a measurement received from a Reekon tool.
 * Based on the measurement record layout (opcode 4 payload).
 */
data class ReekonMeasurement(
    val uuid: Long,
    val flags: Long,
    val positionUm: Int,
    val zeroDeltaUm: Long,
    val activeOffsetUm: Int,
    val groupNumber: Int? = null,
    val measurementNumber: Int? = null,
    val timestamp: Long = System.currentTimeMillis()
) {
    /**
     * Position in inches (converted from micrometers).
     */
    val positionInches: Double
        get() = positionUm / 25400.0

    /**
     * Position in feet and inches string (e.g., "2' 3 1/4\"").
     */
    val positionFeetInches: String
        get() {
            val totalInches = positionInches
            val feet = (totalInches / 12).toInt()
            val inches = totalInches % 12
            return if (feet > 0) {
                "$feet' ${formatInches(inches)}\""
            } else {
                "${formatInches(inches)}\""
            }
        }

    /**
     * Is relative mode active.
     */
    val isRelativeMode: Boolean
        get() = (flags and 0x02L) != 0L

    /**
     * Is measure back mode active.
     */
    val isMeasureBackMode: Boolean
        get() = (flags and 0x04L) != 0L

    /**
     * Is center mode active.
     */
    val isCenterMode: Boolean
        get() = (flags and 0x08L) != 0L

    /**
     * Has measurement been synced over BLE.
     */
    val isSynced: Boolean
        get() = (flags and 0x10L) != 0L

    private fun formatInches(inches: Double): String {
        val wholeInches = inches.toInt()
        val fraction = inches - wholeInches

        val fractionStr = when {
            fraction < 0.03125 -> ""
            fraction < 0.09375 -> " 1/16"
            fraction < 0.15625 -> " 1/8"
            fraction < 0.21875 -> " 3/16"
            fraction < 0.28125 -> " 1/4"
            fraction < 0.34375 -> " 5/16"
            fraction < 0.40625 -> " 3/8"
            fraction < 0.46875 -> " 7/16"
            fraction < 0.53125 -> " 1/2"
            fraction < 0.59375 -> " 9/16"
            fraction < 0.65625 -> " 5/8"
            fraction < 0.71875 -> " 11/16"
            fraction < 0.78125 -> " 3/4"
            fraction < 0.84375 -> " 13/16"
            fraction < 0.90625 -> " 7/8"
            fraction < 0.96875 -> " 15/16"
            else -> ""
        }

        val adjustedWhole = if (fraction >= 0.96875) wholeInches + 1 else wholeInches
        return "$adjustedWhole$fractionStr".trim()
    }
}

/**
 * BLE connection state.
 */
enum class BleConnectionState {
    DISCONNECTED,
    SCANNING,
    CONNECTING,
    CONNECTED,
    DISCONNECTING
}

/**
 * Reekon BLE UUIDs from the SDK.
 */
object ReekonBleUuids {
    // Company ID for manufacturer data
    const val REEKON_COMPANY_ID = 0x0df4

    // Sync Service (primary integration point)
    const val SYNC_SERVICE_UUID = "43744f76-de4d-4b05-a14a-0c133a785d8e"

    // Comm characteristic (write, notify, indicate)
    const val SYNC_COMM_CHARACTERISTIC_UUID = "6f0b2d1e-99e2-4ef4-bfab-70a5f3890f32"

    // Protocol Version characteristic (read)
    const val PROTOCOL_VERSION_CHARACTERISTIC_UUID = "2731f0a6-c56c-4da8-85bd-a586145ad952"

    // Position Service
    const val POSITION_SERVICE_UUID = "7ad09b02-1f68-4c68-922f-a063dfdb7912"

    // Live Measurement characteristic (read, 4 bytes = current measurement in μm)
    const val LIVE_MEASUREMENT_CHARACTERISTIC_UUID = "0d689f63-fdf9-4f0f-8522-736d4723ad2f"

    // Client Characteristic Configuration Descriptor (for enabling notifications)
    const val CCCD_UUID = "00002902-0000-1000-8000-00805f9b34fb"
}

/**
 * Reekon opcodes for Sync Service Comm.
 */
object ReekonOpcodes {
    const val NOP = 0
    const val START_UPDATE = 1  // Do Not Use - internal
    const val CANCEL_UPDATE = 2  // Do Not Use - internal
    const val LOCATE_TOOL = 3
    const val MEASUREMENT = 4  // Tool -> App
    const val REQUEST_UNSYNCED_MEASUREMENTS = 5
    const val RESPOND_UNSYNCED_MEASUREMENTS = 6  // Tool -> App
    const val REQUEST_ALL_MEASUREMENTS = 7
    const val RESPOND_ALL_MEASUREMENTS = 8  // Tool -> App
    const val REQUEST_MEASUREMENTS_BY_GROUP = 9
    const val RESPOND_MEASUREMENTS_FROM_GROUP = 10  // Tool -> App
    const val SET_LASER = 11
    const val TARE_BLACK_BOX = 13  // Do Not Modify - internal
}
