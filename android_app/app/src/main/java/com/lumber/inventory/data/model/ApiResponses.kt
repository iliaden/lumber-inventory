package com.lumber.inventory.data.model

import com.google.gson.annotations.SerializedName

/**
 * Generic API response wrapper matching the Flask API format.
 */
data class ApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T? = null
)

/**
 * API error response.
 */
data class ApiError(
    val error: String
)

/**
 * Health check response.
 */
data class HealthStatus(
    val status: String,
    val version: String
)

/**
 * Inventory statistics response.
 */
data class InventoryStats(
    @SerializedName("total_lumber")
    val totalLumber: Int,
    @SerializedName("total_locations")
    val totalLocations: Int,
    @SerializedName("total_tags")
    val totalTags: Int,
    @SerializedName("planed_count")
    val planedCount: Int,
    @SerializedName("rough_count")
    val roughCount: Int,
    @SerializedName("species_counts")
    val speciesCounts: Map<String, Int>
)
