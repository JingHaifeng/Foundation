package com.alex.code.foundation.di.module;

import com.alex.code.foundation.login.LoginActivity;
import com.alex.code.foundation.main.MainActivity;
import com.alex.code.foundation.di.annotation.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivity();
}
