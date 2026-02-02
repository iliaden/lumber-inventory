package com.lumber.inventory.ui.screens.tags;

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
public final class TagsViewModel_Factory implements Factory<TagsViewModel> {
  private final Provider<LumberRepository> repositoryProvider;

  public TagsViewModel_Factory(Provider<LumberRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public TagsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static TagsViewModel_Factory create(Provider<LumberRepository> repositoryProvider) {
    return new TagsViewModel_Factory(repositoryProvider);
  }

  public static TagsViewModel newInstance(LumberRepository repository) {
    return new TagsViewModel(repository);
  }
}
