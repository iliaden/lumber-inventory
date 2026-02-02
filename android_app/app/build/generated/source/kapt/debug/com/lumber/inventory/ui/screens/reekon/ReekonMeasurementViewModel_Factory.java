package com.lumber.inventory.ui.screens.reekon;

import android.content.Context;
import com.lumber.inventory.data.ble.MeasurementInputManager;
import com.lumber.inventory.data.ble.ReekonBleManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ReekonMeasurementViewModel_Factory implements Factory<ReekonMeasurementViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<ReekonBleManager> bleManagerProvider;

  private final Provider<MeasurementInputManager> measurementInputManagerProvider;

  public ReekonMeasurementViewModel_Factory(Provider<Context> contextProvider,
      Provider<ReekonBleManager> bleManagerProvider,
      Provider<MeasurementInputManager> measurementInputManagerProvider) {
    this.contextProvider = contextProvider;
    this.bleManagerProvider = bleManagerProvider;
    this.measurementInputManagerProvider = measurementInputManagerProvider;
  }

  @Override
  public ReekonMeasurementViewModel get() {
    return newInstance(contextProvider.get(), bleManagerProvider.get(), measurementInputManagerProvider.get());
  }

  public static ReekonMeasurementViewModel_Factory create(Provider<Context> contextProvider,
      Provider<ReekonBleManager> bleManagerProvider,
      Provider<MeasurementInputManager> measurementInputManagerProvider) {
    return new ReekonMeasurementViewModel_Factory(contextProvider, bleManagerProvider, measurementInputManagerProvider);
  }

  public static ReekonMeasurementViewModel newInstance(Context context, ReekonBleManager bleManager,
      MeasurementInputManager measurementInputManager) {
    return new ReekonMeasurementViewModel(context, bleManager, measurementInputManager);
  }
}
