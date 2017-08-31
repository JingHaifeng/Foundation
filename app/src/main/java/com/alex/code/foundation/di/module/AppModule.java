package com.alex.code.foundation.di.module;

import android.content.Context;
import android.util.Log;

import com.alex.code.foundation.App;
import com.alex.code.foundation.AppConstants;
import com.alex.code.foundation.BuildConfig;
import com.alex.code.foundation.data.AppDataManager;
import com.alex.code.foundation.data.IDataManager;
import com.alex.code.foundation.data.network.CustomInterceptor;
import com.alex.code.foundation.data.network.IApi;
import com.alex.code.foundation.data.preference.AppPreference;
import com.alex.code.foundation.data.preference.IPreference;
import com.alex.code.foundation.di.annotation.ApplicationContext;
import com.alex.code.foundation.di.annotation.PreferenceInfo;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

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
