package com.lumber.inventory.ui.screens.settings;

import com.lumber.inventory.data.repository.LumberRepository;
import com.lumber.inventory.data.repository.SettingsRepository;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<LumberRepository> lumberRepositoryProvider;

  public SettingsViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<LumberRepository> lumberRepositoryProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.lumberRepositoryProvider = lumberRepositoryProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(settingsRepositoryProvider.get(), lumberRepositoryProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<LumberRepository> lumberRepositoryProvider) {
    return new SettingsViewModel_Factory(settingsRepositoryProvider, lumberRepositoryProvider);
  }

  public static SettingsViewModel newInstance(SettingsRepository settingsRepository,
      LumberRepository lumberRepository) {
    return new SettingsViewModel(settingsRepository, lumberRepository);
  }
}
