package com.alex.code.foundation.di.component;

import com.alex.code.foundation.di.annotation.PerActivity;
import com.alex.code.foundation.di.module.LoginActivityModule;
import com.alex.code.foundation.login.LoginActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerActivity
@Subcomponent(modules = LoginActivityModule.class)
public interface LoginActivitySubComponent extends AndroidInjector<LoginActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {

    }
}
