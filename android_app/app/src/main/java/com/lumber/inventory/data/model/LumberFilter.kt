package com.lumber.inventory.data.model

/**
 * Represents filter criteria for searching lumber inventory.
 */
data class LumberFilter(
    val species: String? = null,
    val locationId: Int? = null,
    val planed: Boolean? = null,
    val tagId: Int? = null,
    val minLength: String? = null,
    val maxLength: String? = null,
    val minThickness: String? = null,
    val maxThickness: String? = null
) {
    /**
     * Returns true if any filter is active.
     */
    fun isActive(): Boolean {
        return species != null ||
               locationId != null ||
               planed != null ||
               tagId != null ||
               minLength != null ||
               maxLength != null ||
               minThickness != null ||
               maxThickness != null
    }

    /**
     * Returns the number of active filters.
     */
    fun activeCount(): Int {
        var count = 0
        if (species != null) count++
        if (locationId != null) count++
        if (planed != null) count++
        if (tagId != null) count++
        if (minLength != null || maxLength != null) count++
        if (minThickness != null || maxThickness != null) count++
        return count
    }

    companion object {
        val EMPTY = LumberFilter()
    }
}
