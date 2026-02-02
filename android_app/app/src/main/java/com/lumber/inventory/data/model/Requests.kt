package com.lumber.inventory.data.model

import com.google.gson.annotations.SerializedName

/**
 * Request body for creating a new lumber item.
 */
data class CreateLumberRequest(
    val species: String,
    val length: String,
    val width: String,
    val thickness: String,
    val planed: Boolean = false,
    @SerializedName("location_id")
    val locationId: Int? = null,
    @SerializedName("location_name")
    val locationName: String? = null,
    val tags: List<String>? = null,
    @SerializedName("tag_ids")
    val tagIds: List<Int>? = null
)

/**
 * Request body for updating an existing lumber item.
 * All fields are optional - only provided fields will be updated.
 */
data class UpdateLumberRequest(
    val species: String? = null,
    val length: String? = null,
    val width: String? = null,
    val thickness: String? = null,
    val planed: Boolean? = null,
    @SerializedName("location_id")
    val locationId: Int? = null,
    @SerializedName("location_name")
    val locationName: String? = null,
    val tags: List<String>? = null,
    @SerializedName("tag_ids")
    val tagIds: List<Int>? = null
)

/**
 * Request body for creating or updating a location.
 */
data class LocationRequest(
    val name: String
)

/**
 * Request body for creating or updating a tag.
 */
data class TagRequest(
    val name: String
)
