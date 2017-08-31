package com.alex.code.foundation.di;

import com.alex.code.foundation.App;
import com.alex.code.foundation.di.component.DaggerAppComponent;


public class AppInjector {

    private AppInjector() {

    }

    public static void init(App app) {
        DaggerAppComponent.builder().create(app).inject(app);
//
//        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                handleActivity(activity);
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//
//            }
//        });
//    }
//
//    private static void handleActivity(Activity activity) {
//        AndroidInjection.inject(activity);
//        // TODO: 17-8-30 根据情况兼容 Fragment/SupportFragment/FragmentActivity
    }
}
