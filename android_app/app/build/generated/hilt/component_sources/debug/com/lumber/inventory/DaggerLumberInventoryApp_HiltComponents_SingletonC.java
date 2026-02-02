package com.lumber.inventory;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.gson.Gson;
import com.lumber.inventory.data.api.LumberApiService;
import com.lumber.inventory.data.ble.MeasurementInputManager;
import com.lumber.inventory.data.ble.ReekonBleManager;
import com.lumber.inventory.data.repository.LumberRepository;
import com.lumber.inventory.data.repository.SettingsRepository;
import com.lumber.inventory.di.NetworkModule_ProvideGsonFactory;
import com.lumber.inventory.di.NetworkModule_ProvideLumberApiServiceFactory;
import com.lumber.inventory.di.NetworkModule_ProvideOkHttpClientFactory;
import com.lumber.inventory.di.NetworkModule_ProvideRetrofitFactory;
import com.lumber.inventory.di.NetworkModule_ProvideSettingsRepositoryFactory;
import com.lumber.inventory.di.RepositoryModule_ProvideLumberRepositoryFactory;
import com.lumber.inventory.ui.MainActivity;
import com.lumber.inventory.ui.MainActivity_MembersInjector;
import com.lumber.inventory.ui.screens.add.AddLumberViewModel;
import com.lumber.inventory.ui.screens.add.AddLumberViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lumber.inventory.ui.screens.edit.EditLumberViewModel;
import com.lumber.inventory.ui.screens.edit.EditLumberViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lumber.inventory.ui.screens.inventory.InventoryViewModel;
import com.lumber.inventory.ui.screens.inventory.InventoryViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lumber.inventory.ui.screens.locations.LocationsViewModel;
import com.lumber.inventory.ui.screens.locations.LocationsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lumber.inventory.ui.screens.reekon.ReekonMeasurementViewModel;
import com.lumber.inventory.ui.screens.reekon.ReekonMeasurementViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lumber.inventory.ui.screens.settings.SettingsViewModel;
import com.lumber.inventory.ui.screens.settings.SettingsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lumber.inventory.ui.screens.setup.SetupViewModel;
import com.lumber.inventory.ui.screens.setup.SetupViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lumber.inventory.ui.screens.tags.TagsViewModel;
import com.lumber.inventory.ui.screens.tags.TagsViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerLumberInventoryApp_HiltComponents_SingletonC {
  private DaggerLumberInventoryApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public LumberInventoryApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements LumberInventoryApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public LumberInventoryApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements LumberInventoryApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public LumberInventoryApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements LumberInventoryApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public LumberInventoryApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements LumberInventoryApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public LumberInventoryApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements LumberInventoryApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public LumberInventoryApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements LumberInventoryApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public LumberInventoryApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements LumberInventoryApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public LumberInventoryApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends LumberInventoryApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends LumberInventoryApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends LumberInventoryApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends LumberInventoryApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
      injectMainActivity2(arg0);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(8).add(AddLumberViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(EditLumberViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(InventoryViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LocationsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ReekonMeasurementViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SettingsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SetupViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(TagsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectSettingsRepository(instance, singletonCImpl.provideSettingsRepositoryProvider.get());
      return instance;
    }
  }

  private static final class ViewModelCImpl extends LumberInventoryApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AddLumberViewModel> addLumberViewModelProvider;

    private Provider<EditLumberViewModel> editLumberViewModelProvider;

    private Provider<InventoryViewModel> inventoryViewModelProvider;

    private Provider<LocationsViewModel> locationsViewModelProvider;

    private Provider<ReekonMeasurementViewModel> reekonMeasurementViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private Provider<SetupViewModel> setupViewModelProvider;

    private Provider<TagsViewModel> tagsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addLumberViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.editLumberViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.inventoryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.locationsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.reekonMeasurementViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.setupViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.tagsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(8).put("com.lumber.inventory.ui.screens.add.AddLumberViewModel", ((Provider) addLumberViewModelProvider)).put("com.lumber.inventory.ui.screens.edit.EditLumberViewModel", ((Provider) editLumberViewModelProvider)).put("com.lumber.inventory.ui.screens.inventory.InventoryViewModel", ((Provider) inventoryViewModelProvider)).put("com.lumber.inventory.ui.screens.locations.LocationsViewModel", ((Provider) locationsViewModelProvider)).put("com.lumber.inventory.ui.screens.reekon.ReekonMeasurementViewModel", ((Provider) reekonMeasurementViewModelProvider)).put("com.lumber.inventory.ui.screens.settings.SettingsViewModel", ((Provider) settingsViewModelProvider)).put("com.lumber.inventory.ui.screens.setup.SetupViewModel", ((Provider) setupViewModelProvider)).put("com.lumber.inventory.ui.screens.tags.TagsViewModel", ((Provider) tagsViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.lumber.inventory.ui.screens.add.AddLumberViewModel 
          return (T) new AddLumberViewModel(singletonCImpl.provideLumberRepositoryProvider.get());

          case 1: // com.lumber.inventory.ui.screens.edit.EditLumberViewModel 
          return (T) new EditLumberViewModel(singletonCImpl.provideLumberRepositoryProvider.get());

          case 2: // com.lumber.inventory.ui.screens.inventory.InventoryViewModel 
          return (T) new InventoryViewModel(singletonCImpl.provideLumberRepositoryProvider.get());

          case 3: // com.lumber.inventory.ui.screens.locations.LocationsViewModel 
          return (T) new LocationsViewModel(singletonCImpl.provideLumberRepositoryProvider.get());

          case 4: // com.lumber.inventory.ui.screens.reekon.ReekonMeasurementViewModel 
          return (T) new ReekonMeasurementViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.reekonBleManagerProvider.get(), singletonCImpl.measurementInputManagerProvider.get());

          case 5: // com.lumber.inventory.ui.screens.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.provideSettingsRepositoryProvider.get(), singletonCImpl.provideLumberRepositoryProvider.get());

          case 6: // com.lumber.inventory.ui.screens.setup.SetupViewModel 
          return (T) new SetupViewModel(singletonCImpl.provideSettingsRepositoryProvider.get(), singletonCImpl.provideLumberRepositoryProvider.get());

          case 7: // com.lumber.inventory.ui.screens.tags.TagsViewModel 
          return (T) new TagsViewModel(singletonCImpl.provideLumberRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends LumberInventoryApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends LumberInventoryApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends LumberInventoryApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<SettingsRepository> provideSettingsRepositoryProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Gson> provideGsonProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<LumberApiService> provideLumberApiServiceProvider;

    private Provider<LumberRepository> provideLumberRepositoryProvider;

    private Provider<ReekonBleManager> reekonBleManagerProvider;

    private Provider<MeasurementInputManager> measurementInputManagerProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideSettingsRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<SettingsRepository>(singletonCImpl, 0));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 4));
      this.provideGsonProvider = DoubleCheck.provider(new SwitchingProvider<Gson>(singletonCImpl, 5));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 3));
      this.provideLumberApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<LumberApiService>(singletonCImpl, 2));
      this.provideLumberRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<LumberRepository>(singletonCImpl, 1));
      this.reekonBleManagerProvider = DoubleCheck.provider(new SwitchingProvider<ReekonBleManager>(singletonCImpl, 6));
      this.measurementInputManagerProvider = DoubleCheck.provider(new SwitchingProvider<MeasurementInputManager>(singletonCImpl, 7));
    }

    @Override
    public void injectLumberInventoryApp(LumberInventoryApp lumberInventoryApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.lumber.inventory.data.repository.SettingsRepository 
          return (T) NetworkModule_ProvideSettingsRepositoryFactory.provideSettingsRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 1: // com.lumber.inventory.data.repository.LumberRepository 
          return (T) RepositoryModule_ProvideLumberRepositoryFactory.provideLumberRepository(singletonCImpl.provideLumberApiServiceProvider.get(), singletonCImpl.provideGsonProvider.get());

          case 2: // com.lumber.inventory.data.api.LumberApiService 
          return (T) NetworkModule_ProvideLumberApiServiceFactory.provideLumberApiService(singletonCImpl.provideRetrofitProvider.get());

          case 3: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideGsonProvider.get(), singletonCImpl.provideSettingsRepositoryProvider.get());

          case 4: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 5: // com.google.gson.Gson 
          return (T) NetworkModule_ProvideGsonFactory.provideGson();

          case 6: // com.lumber.inventory.data.ble.ReekonBleManager 
          return (T) new ReekonBleManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.lumber.inventory.data.ble.MeasurementInputManager 
          return (T) new MeasurementInputManager();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
