package com.lumber.inventory.data.api;

/**
 * Exception thrown when an API call fails.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000b"}, d2 = {"Lcom/lumber/inventory/data/api/ApiException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "code", "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "app_debug"})
public final class ApiException extends java.lang.Exception {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer code = null;
    
    public ApiException(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.Integer code) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getCode() {
        return null;
    }
}