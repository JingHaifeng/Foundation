package com.alex.code.foundation.di.test;

import android.app.Activity;

import com.alex.code.foundation.di.annotation.PerActivity;

import javax.inject.Inject;

@PerActivity
public class PerActivityUtil {

    private final Activity activity;

    @Inject
    public PerActivityUtil(Activity activity) {
        this.activity = activity;
    }

    public String doTest() {
        return "PerActivityUtil:" + hashCode() + ", Activity:" + activity.hashCode();
    }
}
