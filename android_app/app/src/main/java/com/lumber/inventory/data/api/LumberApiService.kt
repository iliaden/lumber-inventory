package com.lumber.inventory.data.api

import com.lumber.inventory.data.model.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit API service interface for the Lumber Inventory REST API.
 * Base URL should be configured as: {serverUrl}/api/v1/
 */
interface LumberApiService {

    // ========================================================================
    // Health & Stats
    // ========================================================================

    @GET("health")
    suspend fun healthCheck(): Response<ApiResponse<HealthStatus>>

    @GET("stats")
    suspend fun getStats(): Response<ApiResponse<InventoryStats>>

    // ========================================================================
    // Lumber
    // ========================================================================

    @GET("lumber")
    suspend fun getLumberList(
        @Query("species") species: String? = null,
        @Query("location_id") locationId: Int? = null,
        @Query("planed") planed: String? = null,
        @Query("tag_id") tagId: Int? = null,
        @Query("min_length") minLength: String? = null,
        @Query("max_length") maxLength: String? = null,
        @Query("min_thickness") minThickness: String? = null,
        @Query("max_thickness") maxThickness: String? = null
    ): Response<ApiResponse<List<Lumber>>>

    @GET("lumber/{id}")
    suspend fun getLumber(@Path("id") id: Int): Response<ApiResponse<Lumber>>

    @POST("lumber")
    suspend fun createLumber(@Body request: CreateLumberRequest): Response<ApiResponse<Lumber>>

    @PUT("lumber/{id}")
    suspend fun updateLumber(
        @Path("id") id: Int,
        @Body request: UpdateLumberRequest
    ): Response<ApiResponse<Lumber>>

    @DELETE("lumber/{id}")
    suspend fun deleteLumber(@Path("id") id: Int): Response<ApiResponse<Unit>>

    // ========================================================================
    // Locations
    // ========================================================================

    @GET("locations")
    suspend fun getLocations(): Response<ApiResponse<List<Location>>>

    @GET("locations/{id}")
    suspend fun getLocation(@Path("id") id: Int): Response<ApiResponse<Location>>

    @POST("locations")
    suspend fun createLocation(@Body request: LocationRequest): Response<ApiResponse<Location>>

    @PUT("locations/{id}")
    suspend fun updateLocation(
        @Path("id") id: Int,
        @Body request: LocationRequest
    ): Response<ApiResponse<Location>>

    @DELETE("locations/{id}")
    suspend fun deleteLocation(@Path("id") id: Int): Response<ApiResponse<Unit>>

    // ========================================================================
    // Tags
    // ========================================================================

    @GET("tags")
    suspend fun getTags(): Response<ApiResponse<List<Tag>>>

    @GET("tags/{id}")
    suspend fun getTag(@Path("id") id: Int): Response<ApiResponse<Tag>>

    @POST("tags")
    suspend fun createTag(@Body request: TagRequest): Response<ApiResponse<Tag>>

    @PUT("tags/{id}")
    suspend fun updateTag(
        @Path("id") id: Int,
        @Body request: TagRequest
    ): Response<ApiResponse<Tag>>

    @DELETE("tags/{id}")
    suspend fun deleteTag(@Path("id") id: Int): Response<ApiResponse<Unit>>
}
