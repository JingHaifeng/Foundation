package com.alex.code.foundation.di.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alex.code.foundation.di.annotation.PerFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseFragmentModule {

    public static final String CHILD_SUPPORT_FRAGMENT_MANAGER = "BaseFragmentModule.ChildSupportFragmentManager";

    public static final String FRAGMENT = "BaseFragmentModule.Fragment";

    @Provides
    @Named(CHILD_SUPPORT_FRAGMENT_MANAGER)
    @PerFragment
    static FragmentManager childFragmentManager(@Named(FRAGMENT) Fragment fragment) {
        return fragment.getChildFragmentManager();
    }
}
