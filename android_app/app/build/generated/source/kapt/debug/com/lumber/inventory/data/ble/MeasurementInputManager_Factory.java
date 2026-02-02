package com.lumber.inventory.data.ble;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class MeasurementInputManager_Factory implements Factory<MeasurementInputManager> {
  @Override
  public MeasurementInputManager get() {
    return newInstance();
  }

  public static MeasurementInputManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MeasurementInputManager newInstance() {
    return new MeasurementInputManager();
  }

  private static final class InstanceHolder {
    private static final MeasurementInputManager_Factory INSTANCE = new MeasurementInputManager_Factory();
  }
}
