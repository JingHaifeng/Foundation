package com.alex.code.foundation.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.alex.code.foundation.di.annotation.ApplicationContext;
import com.alex.code.foundation.di.annotation.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AppPreference implements IPreference {

    private final SharedPreferences mSharedPreferences;

    @Inject
    public AppPreference(@ApplicationContext Context context,
                         @PreferenceInfo String preferencesName) {
        mSharedPreferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isFirstRun() {
        return mSharedPreferences.getBoolean(IPreference.IS_FIRST_RUN, true);
    }

    @Override
    public void setIsFirstRun(boolean isFirstRun) {
        mSharedPreferences.edit().putBoolean(IPreference.IS_FIRST_RUN, isFirstRun).apply();
    }
}
