package com.alex.code.foundation;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.alex.code.foundation.di.AppInjector;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.singhajit.sherlock.core.Sherlock;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        initDi();
        initSherlock();
        initARouter();
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy
                .newBuilder()
                .tag(AppConstants.APP_TAG)
                .build()
        ) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initDi() {
        AppInjector.init(this);
    }

    /**
     * https://github.com/ajitsing/Sherlock
     * 通过通知栏显示崩溃信息，帮助分析崩溃
     */
    private void initSherlock() {
        Sherlock.init(this);
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityDispatchingAndroidInjector;
    }
}
