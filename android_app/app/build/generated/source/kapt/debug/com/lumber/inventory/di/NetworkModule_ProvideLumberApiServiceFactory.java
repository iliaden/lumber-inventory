package com.lumber.inventory.di;

import com.lumber.inventory.data.api.LumberApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideLumberApiServiceFactory implements Factory<LumberApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideLumberApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public LumberApiService get() {
    return provideLumberApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideLumberApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideLumberApiServiceFactory(retrofitProvider);
  }

  public static LumberApiService provideLumberApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideLumberApiService(retrofit));
  }
}
