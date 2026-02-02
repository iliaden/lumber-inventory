package com.lumber.inventory.data.api;

import com.lumber.inventory.data.model.*;
import retrofit2.Response;
import retrofit2.http.*;

/**
 * Retrofit API service interface for the Lumber Inventory REST API.
 * Base URL should be configured as: {serverUrl}/api/v1/
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J$\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J \u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001a0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001bJ$\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0080\u0001\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001a0\u00040\u00032\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u00142\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\u001f2\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010\u00142\n\b\u0003\u0010#\u001a\u0004\u0018\u00010\u001f2\n\b\u0003\u0010$\u001a\u0004\u0018\u00010\u001f2\n\b\u0003\u0010%\u001a\u0004\u0018\u00010\u001f2\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\u001fH\u00a7@\u00a2\u0006\u0002\u0010\'J\u001a\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001bJ$\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J \u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001a0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u001a\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001bJ.\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010/J.\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0006\u001a\u000201H\u00a7@\u00a2\u0006\u0002\u00102J.\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0006\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u00104\u00a8\u00065"}, d2 = {"Lcom/lumber/inventory/data/api/LumberApiService;", "", "createLocation", "Lretrofit2/Response;", "Lcom/lumber/inventory/data/model/ApiResponse;", "Lcom/lumber/inventory/data/model/Location;", "request", "Lcom/lumber/inventory/data/model/LocationRequest;", "(Lcom/lumber/inventory/data/model/LocationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createLumber", "Lcom/lumber/inventory/data/model/Lumber;", "Lcom/lumber/inventory/data/model/CreateLumberRequest;", "(Lcom/lumber/inventory/data/model/CreateLumberRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createTag", "error/NonExistentClass", "Lcom/lumber/inventory/data/model/TagRequest;", "(Lcom/lumber/inventory/data/model/TagRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLocation", "", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLumber", "deleteTag", "getLocation", "getLocations", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLumber", "getLumberList", "species", "", "locationId", "planed", "tagId", "minLength", "maxLength", "minThickness", "maxThickness", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStats", "Lcom/lumber/inventory/data/model/InventoryStats;", "getTag", "getTags", "healthCheck", "Lcom/lumber/inventory/data/model/HealthStatus;", "updateLocation", "(ILcom/lumber/inventory/data/model/LocationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLumber", "Lcom/lumber/inventory/data/model/UpdateLumberRequest;", "(ILcom/lumber/inventory/data/model/UpdateLumberRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTag", "(ILcom/lumber/inventory/data/model/TagRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface LumberApiService {
    
    @retrofit2.http.GET(value = "health")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object healthCheck(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<com.lumber.inventory.data.model.HealthStatus>>> $completion);
    
    @retrofit2.http.GET(value = "stats")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<com.lumber.inventory.data.model.InventoryStats>>> $completion);
    
    @retrofit2.http.GET(value = "lumber")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLumberList(@retrofit2.http.Query(value = "species")
    @org.jetbrains.annotations.Nullable()
    java.lang.String species, @retrofit2.http.Query(value = "location_id")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer locationId, @retrofit2.http.Query(value = "planed")
    @org.jetbrains.annotations.Nullable()
    java.lang.String planed, @retrofit2.http.Query(value = "tag_id")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer tagId, @retrofit2.http.Query(value = "min_length")
    @org.jetbrains.annotations.Nullable()
    java.lang.String minLength, @retrofit2.http.Query(value = "max_length")
    @org.jetbrains.annotations.Nullable()
    java.lang.String maxLength, @retrofit2.http.Query(value = "min_thickness")
    @org.jetbrains.annotations.Nullable()
    java.lang.String minThickness, @retrofit2.http.Query(value = "max_thickness")
    @org.jetbrains.annotations.Nullable()
    java.lang.String maxThickness, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<java.util.List<com.lumber.inventory.data.model.Lumber>>>> $completion);
    
    @retrofit2.http.GET(value = "lumber/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLumber(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<com.lumber.inventory.data.model.Lumber>>> $completion);
    
    @retrofit2.http.POST(value = "lumber")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createLumber(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.CreateLumberRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<com.lumber.inventory.data.model.Lumber>>> $completion);
    
    @retrofit2.http.PUT(value = "lumber/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLumber(@retrofit2.http.Path(value = "id")
    int id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.UpdateLumberRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<com.lumber.inventory.data.model.Lumber>>> $completion);
    
    @retrofit2.http.DELETE(value = "lumber/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteLumber(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<kotlin.Unit>>> $completion);
    
    @retrofit2.http.GET(value = "locations")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLocations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<java.util.List<com.lumber.inventory.data.model.Location>>>> $completion);
    
    @retrofit2.http.GET(value = "locations/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLocation(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<com.lumber.inventory.data.model.Location>>> $completion);
    
    @retrofit2.http.POST(value = "locations")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createLocation(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.LocationRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<com.lumber.inventory.data.model.Location>>> $completion);
    
    @retrofit2.http.PUT(value = "locations/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLocation(@retrofit2.http.Path(value = "id")
    int id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.LocationRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<com.lumber.inventory.data.model.Location>>> $completion);
    
    @retrofit2.http.DELETE(value = "locations/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteLocation(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<kotlin.Unit>>> $completion);
    
    @retrofit2.http.GET(value = "tags")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTags(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<Response<ApiResponse<List<Tag>>>> $completion);
    
    @retrofit2.http.GET(value = "tags/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTag(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<Response<ApiResponse<Tag>>> $completion);
    
    @retrofit2.http.POST(value = "tags")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createTag(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.TagRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<Response<ApiResponse<Tag>>> $completion);
    
    @retrofit2.http.PUT(value = "tags/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTag(@retrofit2.http.Path(value = "id")
    int id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.TagRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<Response<ApiResponse<Tag>>> $completion);
    
    @retrofit2.http.DELETE(value = "tags/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTag(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.lumber.inventory.data.model.ApiResponse<kotlin.Unit>>> $completion);
    
    /**
     * Retrofit API service interface for the Lumber Inventory REST API.
     * Base URL should be configured as: {serverUrl}/api/v1/
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}