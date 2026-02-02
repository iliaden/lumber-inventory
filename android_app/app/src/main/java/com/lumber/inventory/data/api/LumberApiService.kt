package com.lumber.inventory.data.api

import com.lumber.inventory.data.model.ApiResponse
import com.lumber.inventory.data.model.CreateLumberRequest
import com.lumber.inventory.data.model.HealthStatus
import com.lumber.inventory.data.model.InventoryStats
import com.lumber.inventory.data.model.Location
import com.lumber.inventory.data.model.LocationRequest
import com.lumber.inventory.data.model.Lumber
import com.lumber.inventory.data.model.TagRequest
import com.lumber.inventory.data.model.UpdateLumberRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import com.lumber.inventory.data.model.Tag as LumberTag

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

    @GET("species")
    suspend fun getSpecies(): Response<ApiResponse<List<String>>>

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
    suspend fun getTags(): Response<ApiResponse<List<LumberTag>>>

    @GET("tags/{id}")
    suspend fun getTag(@Path("id") id: Int): Response<ApiResponse<LumberTag>>

    @POST("tags")
    suspend fun createTag(@Body request: TagRequest): Response<ApiResponse<LumberTag>>

    @PUT("tags/{id}")
    suspend fun updateTag(
        @Path("id") id: Int,
        @Body request: TagRequest
    ): Response<ApiResponse<LumberTag>>

    @DELETE("tags/{id}")
    suspend fun deleteTag(@Path("id") id: Int): Response<ApiResponse<Unit>>
}
