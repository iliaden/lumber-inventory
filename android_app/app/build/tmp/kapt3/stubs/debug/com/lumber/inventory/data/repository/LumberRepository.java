package com.lumber.inventory.data.repository;

import com.google.gson.Gson;
import com.lumber.inventory.data.api.ApiResult;
import com.lumber.inventory.data.api.LumberApiService;
import com.lumber.inventory.data.model.*;
import retrofit2.Response;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Repository for lumber-related data operations.
 * Handles API calls and converts responses to ApiResult.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u001d0\bH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J$\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001d0\b2\b\b\u0002\u0010!\u001a\u00020\"H\u0086@\u00a2\u0006\u0002\u0010#J\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001d0\bH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\bH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u001c\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001a\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001d0\bH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\bH\u0086@\u00a2\u0006\u0002\u0010\u001eJD\u0010+\u001a\b\u0012\u0004\u0012\u0002H,0\b\"\u0004\b\u0000\u0010,2(\u0010-\u001a$\b\u0001\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H,01000/\u0012\u0006\u0012\u0004\u0018\u00010\u00010.H\u0082@\u00a2\u0006\u0002\u00102J$\u00103\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u00104J$\u00105\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u000206H\u0086@\u00a2\u0006\u0002\u00107J$\u00108\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u00104R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lcom/lumber/inventory/data/repository/LumberRepository;", "", "apiService", "Lcom/lumber/inventory/data/api/LumberApiService;", "gson", "Lcom/google/gson/Gson;", "(Lcom/lumber/inventory/data/api/LumberApiService;Lcom/google/gson/Gson;)V", "createLocation", "Lcom/lumber/inventory/data/api/ApiResult;", "Lcom/lumber/inventory/data/model/Location;", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createLumber", "Lcom/lumber/inventory/data/model/Lumber;", "request", "Lcom/lumber/inventory/data/model/CreateLumberRequest;", "(Lcom/lumber/inventory/data/model/CreateLumberRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createTag", "Lcom/lumber/inventory/data/model/Tag;", "deleteLocation", "", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLumber", "deleteTag", "getLocation", "getLocations", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLumber", "getLumberList", "filter", "Lcom/lumber/inventory/data/model/LumberFilter;", "(Lcom/lumber/inventory/data/model/LumberFilter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSpecies", "getStats", "Lcom/lumber/inventory/data/model/InventoryStats;", "getTag", "getTags", "healthCheck", "Lcom/lumber/inventory/data/model/HealthStatus;", "safeApiCall", "T", "call", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lretrofit2/Response;", "Lcom/lumber/inventory/data/model/ApiResponse;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLocation", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLumber", "Lcom/lumber/inventory/data/model/UpdateLumberRequest;", "(ILcom/lumber/inventory/data/model/UpdateLumberRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTag", "app_debug"})
public final class LumberRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.api.LumberApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    @javax.inject.Inject()
    public LumberRepository(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.api.LumberApiService apiService, @org.jetbrains.annotations.NotNull()
    com.google.gson.Gson gson) {
        super();
    }
    
    /**
     * Get list of all lumber items with optional filtering.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLumberList(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.LumberFilter filter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<? extends java.util.List<com.lumber.inventory.data.model.Lumber>>> $completion) {
        return null;
    }
    
    /**
     * Get a single lumber item by ID.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLumber(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Lumber>> $completion) {
        return null;
    }
    
    /**
     * Create a new lumber item.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createLumber(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.CreateLumberRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Lumber>> $completion) {
        return null;
    }
    
    /**
     * Update an existing lumber item.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateLumber(int id, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.UpdateLumberRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Lumber>> $completion) {
        return null;
    }
    
    /**
     * Delete a lumber item.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteLumber(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Get list of all locations.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLocations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<? extends java.util.List<com.lumber.inventory.data.model.Location>>> $completion) {
        return null;
    }
    
    /**
     * Get a single location by ID.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLocation(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Location>> $completion) {
        return null;
    }
    
    /**
     * Create a new location.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Location>> $completion) {
        return null;
    }
    
    /**
     * Update an existing location.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateLocation(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Location>> $completion) {
        return null;
    }
    
    /**
     * Delete a location.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteLocation(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Get list of all tags.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTags(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<? extends java.util.List<com.lumber.inventory.data.model.Tag>>> $completion) {
        return null;
    }
    
    /**
     * Get a single tag by ID.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTag(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Tag>> $completion) {
        return null;
    }
    
    /**
     * Create a new tag.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createTag(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Tag>> $completion) {
        return null;
    }
    
    /**
     * Update an existing tag.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTag(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.Tag>> $completion) {
        return null;
    }
    
    /**
     * Delete a tag.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTag(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Get inventory statistics.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.InventoryStats>> $completion) {
        return null;
    }
    
    /**
     * Health check to test server connection.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object healthCheck(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<com.lumber.inventory.data.model.HealthStatus>> $completion) {
        return null;
    }
    
    /**
     * Get list of all unique species in the inventory.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSpecies(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<? extends java.util.List<java.lang.String>>> $completion) {
        return null;
    }
    
    /**
     * Execute an API call safely, handling errors and converting responses.
     */
    private final <T extends java.lang.Object>java.lang.Object safeApiCall(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<T>>>, ? extends java.lang.Object> call, kotlin.coroutines.Continuation<? super com.lumber.inventory.data.api.ApiResult<? extends T>> $completion) {
        return null;
    }
}