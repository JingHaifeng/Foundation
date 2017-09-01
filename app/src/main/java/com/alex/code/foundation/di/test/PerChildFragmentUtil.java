package com.alex.code.foundation.di.test;

import android.support.v4.app.Fragment;

import com.alex.code.foundation.di.annotation.PerChildFragment;
import com.alex.code.foundation.di.module.BaseChildFragmentModule;

import javax.inject.Inject;
import javax.inject.Named;

@PerChildFragment
public class PerChildFragmentUtil {

    private final Fragment childFragment;

    @Inject
    PerChildFragmentUtil(@Named(BaseChildFragmentModule.CHILD_SUPPORT_FRAGMENT) Fragment childFragment) {
        this.childFragment = childFragment;
    }

    public String doSomething() {
        return "PerChildFragmentUtil: " + hashCode()
                + ", child Fragment: " + childFragment.hashCode();
    }
}
