package com.lumber.inventory.ui.screens.add;

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
public final class AddLumberViewModel_Factory implements Factory<AddLumberViewModel> {
  private final Provider<LumberRepository> repositoryProvider;

  public AddLumberViewModel_Factory(Provider<LumberRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddLumberViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddLumberViewModel_Factory create(Provider<LumberRepository> repositoryProvider) {
    return new AddLumberViewModel_Factory(repositoryProvider);
  }

  public static AddLumberViewModel newInstance(LumberRepository repository) {
    return new AddLumberViewModel(repository);
  }
}
