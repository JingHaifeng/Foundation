package com.alex.code.foundation.di.component;

import com.alex.code.foundation.App;
import com.alex.code.foundation.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
//        ActivityBindingModule.class,
//        AndroidModule.class,
//        NetworkModule.class,
})
public interface AppComponent {

    void inject(App app);
}
