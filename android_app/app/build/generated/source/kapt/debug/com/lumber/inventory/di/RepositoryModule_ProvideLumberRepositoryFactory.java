package com.lumber.inventory.di;

import com.google.gson.Gson;
import com.lumber.inventory.data.api.LumberApiService;
import com.lumber.inventory.data.repository.LumberRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class RepositoryModule_ProvideLumberRepositoryFactory implements Factory<LumberRepository> {
  private final Provider<LumberApiService> apiServiceProvider;

  private final Provider<Gson> gsonProvider;

  public RepositoryModule_ProvideLumberRepositoryFactory(
      Provider<LumberApiService> apiServiceProvider, Provider<Gson> gsonProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public LumberRepository get() {
    return provideLumberRepository(apiServiceProvider.get(), gsonProvider.get());
  }

  public static RepositoryModule_ProvideLumberRepositoryFactory create(
      Provider<LumberApiService> apiServiceProvider, Provider<Gson> gsonProvider) {
    return new RepositoryModule_ProvideLumberRepositoryFactory(apiServiceProvider, gsonProvider);
  }

  public static LumberRepository provideLumberRepository(LumberApiService apiService, Gson gson) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideLumberRepository(apiService, gson));
  }
}
