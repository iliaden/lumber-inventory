package com.lumber.inventory.data.repository;

import com.google.gson.Gson;
import com.lumber.inventory.data.api.LumberApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class LumberRepository_Factory implements Factory<LumberRepository> {
  private final Provider<LumberApiService> apiServiceProvider;

  private final Provider<Gson> gsonProvider;

  public LumberRepository_Factory(Provider<LumberApiService> apiServiceProvider,
      Provider<Gson> gsonProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public LumberRepository get() {
    return newInstance(apiServiceProvider.get(), gsonProvider.get());
  }

  public static LumberRepository_Factory create(Provider<LumberApiService> apiServiceProvider,
      Provider<Gson> gsonProvider) {
    return new LumberRepository_Factory(apiServiceProvider, gsonProvider);
  }

  public static LumberRepository newInstance(LumberApiService apiService, Gson gson) {
    return new LumberRepository(apiService, gson);
  }
}
