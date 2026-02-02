package com.lumber.inventory.data.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a piece of lumber in the inventory.
 */
data class Lumber(
    val id: Int,
    val species: String,
    val length: Double,
    val width: Double,
    val thickness: Double,
    val planed: Boolean,
    val location: String?,
    @SerializedName("location_id")
    val locationId: Int?,
    @SerializedName("date_added")
    val dateAdded: String?,
    val tags: List<String>,
    @SerializedName("length_display")
    val lengthDisplay: String? = null,
    @SerializedName("width_display")
    val widthDisplay: String? = null,
    @SerializedName("thickness_display")
    val thicknessDisplay: String? = null
)
