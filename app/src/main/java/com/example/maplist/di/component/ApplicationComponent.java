package com.example.maplist.di.component;

import com.example.maplist.App;
import com.example.maplist.di.module.ActivityBindingModule;
import com.example.maplist.di.module.ApiModule;
import com.example.maplist.di.module.ApplicationModule;
import com.example.maplist.di.scope.AppScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@AppScope
@Component(modules = {
    ApplicationModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApiModule.class})
public interface ApplicationComponent extends AndroidInjector<App> {

    void inject(App application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App application);

        ApplicationComponent build();
    }
}