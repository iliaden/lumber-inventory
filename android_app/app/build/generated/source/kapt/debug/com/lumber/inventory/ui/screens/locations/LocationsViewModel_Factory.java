package com.lumber.inventory.ui.screens.locations;

import com.lumber.inventory.data.repository.LumberRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class LocationsViewModel_Factory implements Factory<LocationsViewModel> {
  private final Provider<LumberRepository> repositoryProvider;

  public LocationsViewModel_Factory(Provider<LumberRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LocationsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static LocationsViewModel_Factory create(Provider<LumberRepository> repositoryProvider) {
    return new LocationsViewModel_Factory(repositoryProvider);
  }

  public static LocationsViewModel newInstance(LumberRepository repository) {
    return new LocationsViewModel(repository);
  }
}
