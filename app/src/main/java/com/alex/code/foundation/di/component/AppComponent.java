package com.alex.code.foundation.di.component;

import com.alex.code.foundation.App;
import com.alex.code.foundation.di.module.ActivityBindingModule;
import com.alex.code.foundation.di.module.AndroidModule;
import com.alex.code.foundation.di.module.AppModule;
import com.alex.code.foundation.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        AndroidModule.class,
        NetworkModule.class,
})
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
