package com.alex.code.foundation.di.module;

import android.content.Context;

import com.alex.code.foundation.App;
import com.alex.code.foundation.AppConstants;
import com.alex.code.foundation.data.AppDataManager;
import com.alex.code.foundation.data.IDataManager;
import com.alex.code.foundation.data.preference.AppPreference;
import com.alex.code.foundation.data.preference.IPreference;
import com.alex.code.foundation.di.annotation.ApplicationContext;
import com.alex.code.foundation.di.annotation.PreferenceInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import io.reactivex.disposables.CompositeDisposable;

@Module(includes = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class})
public abstract class AppModule {

    @Provides
    @ApplicationContext
    static Context provideApplicationContext(App app) {
        return app.getApplicationContext();
    }

    /**
     * @return SharedPreference Name
     */
    @Provides
    @PreferenceInfo
    static String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    static IPreference providePreference(AppPreference appPreference) {
        return appPreference;
    }

    /**
     * @param appDataManager
     * @return DataManager
     */
    @Provides
    @Singleton
    static IDataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    static CompositeDisposable providerCompositeDisposable() {
        return new CompositeDisposable();
    }
}
