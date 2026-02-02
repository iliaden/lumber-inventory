package com.lumber.inventory.data.ble;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ReekonBleManager_Factory implements Factory<ReekonBleManager> {
  private final Provider<Context> contextProvider;

  public ReekonBleManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ReekonBleManager get() {
    return newInstance(contextProvider.get());
  }

  public static ReekonBleManager_Factory create(Provider<Context> contextProvider) {
    return new ReekonBleManager_Factory(contextProvider);
  }

  public static ReekonBleManager newInstance(Context context) {
    return new ReekonBleManager(context);
  }
}
