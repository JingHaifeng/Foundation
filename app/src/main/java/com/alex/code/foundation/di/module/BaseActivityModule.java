package com.alex.code.foundation.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.alex.code.foundation.di.annotation.PerActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule {

    public static final String ACTIVITY_SUPPORT_FRAGMENT_MANAGER = "BaseActivityModule.SupportManager";

    @Binds
    @PerActivity
    abstract Activity activity(AppCompatActivity appCompatActivity);

    @Binds
    @PerActivity//@PerActivity 并不是必须的，仅是为了方便阅读理解。
    abstract Context activityContext(Activity activity);

    @Provides
    @Named(ACTIVITY_SUPPORT_FRAGMENT_MANAGER)
    @PerActivity
    static FragmentManager activitySupportFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
