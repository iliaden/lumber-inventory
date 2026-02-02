package com.lumber.inventory.data.repository

import com.google.gson.Gson
import com.lumber.inventory.data.api.ApiResult
import com.lumber.inventory.data.api.LumberApiService
import com.lumber.inventory.data.model.*
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for lumber-related data operations.
 * Handles API calls and converts responses to ApiResult.
 */
@Singleton
class LumberRepository @Inject constructor(
    private val apiService: LumberApiService,
    private val gson: Gson
) {

    // ========================================================================
    // Lumber Operations
    // ========================================================================

    /**
     * Get list of all lumber items with optional filtering.
     */
    suspend fun getLumberList(filter: LumberFilter = LumberFilter.EMPTY): ApiResult<List<Lumber>> {
        return safeApiCall {
            apiService.getLumberList(
                species = filter.species,
                locationId = filter.locationId,
                planed = filter.planed?.toString(),
                tagId = filter.tagId,
                minLength = filter.minLength,
                maxLength = filter.maxLength,
                minThickness = filter.minThickness,
                maxThickness = filter.maxThickness
            )
        }
    }

    /**
     * Get a single lumber item by ID.
     */
    suspend fun getLumber(id: Int): ApiResult<Lumber> {
        return safeApiCall { apiService.getLumber(id) }
    }

    /**
     * Create a new lumber item.
     */
    suspend fun createLumber(request: CreateLumberRequest): ApiResult<Lumber> {
        return safeApiCall { apiService.createLumber(request) }
    }

    /**
     * Update an existing lumber item.
     */
    suspend fun updateLumber(id: Int, request: UpdateLumberRequest): ApiResult<Lumber> {
        return safeApiCall { apiService.updateLumber(id, request) }
    }

    /**
     * Delete a lumber item.
     */
    suspend fun deleteLumber(id: Int): ApiResult<Unit> {
        return safeApiCall { apiService.deleteLumber(id) }
    }

    // ========================================================================
    // Location Operations
    // ========================================================================

    /**
     * Get list of all locations.
     */
    suspend fun getLocations(): ApiResult<List<Location>> {
        return safeApiCall { apiService.getLocations() }
    }

    /**
     * Get a single location by ID.
     */
    suspend fun getLocation(id: Int): ApiResult<Location> {
        return safeApiCall { apiService.getLocation(id) }
    }

    /**
     * Create a new location.
     */
    suspend fun createLocation(name: String): ApiResult<Location> {
        return safeApiCall { apiService.createLocation(LocationRequest(name)) }
    }

    /**
     * Update an existing location.
     */
    suspend fun updateLocation(id: Int, name: String): ApiResult<Location> {
        return safeApiCall { apiService.updateLocation(id, LocationRequest(name)) }
    }

    /**
     * Delete a location.
     */
    suspend fun deleteLocation(id: Int): ApiResult<Unit> {
        return safeApiCall { apiService.deleteLocation(id) }
    }

    // ========================================================================
    // Tag Operations
    // ========================================================================

    /**
     * Get list of all tags.
     */
    suspend fun getTags(): ApiResult<List<Tag>> {
        return safeApiCall { apiService.getTags() }
    }

    /**
     * Get a single tag by ID.
     */
    suspend fun getTag(id: Int): ApiResult<Tag> {
        return safeApiCall { apiService.getTag(id) }
    }

    /**
     * Create a new tag.
     */
    suspend fun createTag(name: String): ApiResult<Tag> {
        return safeApiCall { apiService.createTag(TagRequest(name)) }
    }

    /**
     * Update an existing tag.
     */
    suspend fun updateTag(id: Int, name: String): ApiResult<Tag> {
        return safeApiCall { apiService.updateTag(id, TagRequest(name)) }
    }

    /**
     * Delete a tag.
     */
    suspend fun deleteTag(id: Int): ApiResult<Unit> {
        return safeApiCall { apiService.deleteTag(id) }
    }

    // ========================================================================
    // Stats & Health
    // ========================================================================

    /**
     * Get inventory statistics.
     */
    suspend fun getStats(): ApiResult<InventoryStats> {
        return safeApiCall { apiService.getStats() }
    }

    /**
     * Health check to test server connection.
     */
    suspend fun healthCheck(): ApiResult<HealthStatus> {
        return safeApiCall { apiService.healthCheck() }
    }

    // ========================================================================
    // Helper Methods
    // ========================================================================

    /**
     * Execute an API call safely, handling errors and converting responses.
     */
    private suspend fun <T> safeApiCall(
        call: suspend () -> Response<ApiResponse<T>>
    ): ApiResult<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.success) {
                    if (body.data != null) {
                        ApiResult.Success(body.data)
                    } else {
                        @Suppress("UNCHECKED_CAST")
                        ApiResult.Success(Unit as T)
                    }
                } else {
                    ApiResult.Error(body?.message ?: "Unknown error", response.code())
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    val apiError = gson.fromJson(errorBody, ApiError::class.java)
                    apiError.error
                } catch (e: Exception) {
                    "Server error: ${response.code()}"
                }
                ApiResult.Error(errorMessage, response.code())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }
}
