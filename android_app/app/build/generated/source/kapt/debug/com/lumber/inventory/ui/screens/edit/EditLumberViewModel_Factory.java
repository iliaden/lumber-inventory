package com.lumber.inventory.ui.screens.edit;

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
public final class EditLumberViewModel_Factory implements Factory<EditLumberViewModel> {
  private final Provider<LumberRepository> repositoryProvider;

  public EditLumberViewModel_Factory(Provider<LumberRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public EditLumberViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static EditLumberViewModel_Factory create(Provider<LumberRepository> repositoryProvider) {
    return new EditLumberViewModel_Factory(repositoryProvider);
  }

  public static EditLumberViewModel newInstance(LumberRepository repository) {
    return new EditLumberViewModel(repository);
  }
}
