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
     * 创建 OkHttpClient
     *
     * @param customInterceptor
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(CustomInterceptor customInterceptor) {
        // TODO: 17-8-30
        // 1. 基本授权
        // 2. 基础Url设置
        // 3. 重试方案 For https://github.com/airbnb/okreplay library, recording and replaying server responses.
        // 4. ...
        // 5. 添加 log 打印

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(AppConstants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.READ_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(customInterceptor)
                .addInterceptor((new HttpLoggingInterceptor()
                        .setLevel(BuildConfig.DEBUG || Log.isLoggable("OkHttp", Log.VERBOSE) ?
                                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE)));
        return builder.build();
    }

    /**
     * 提供 Network 相关 Api
     *
     * @param okHttpClient
     * @return
     */
    @Provides
    @Singleton
    static IApi provideApiHelper(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.API_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IApi.class);
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

}
