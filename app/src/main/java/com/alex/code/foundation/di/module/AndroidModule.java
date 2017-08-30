package com.alex.code.foundation.di.module;


import android.content.res.AssetManager;
import android.content.res.Resources;

import com.alex.code.foundation.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AndroidModule {

    private AndroidModule() {
    }

    @Provides
    @Singleton
    static Resources provideResources(App app) {
        return app.getResources();
    }

    @Provides
    @Singleton
    static AssetManager provideAssetManager(Resources resources) {
        return resources.getAssets();
    }
}
