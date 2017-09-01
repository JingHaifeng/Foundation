package com.alex.code.foundation.di.test;

import android.support.v4.app.Fragment;

import com.alex.code.foundation.di.annotation.PerFragment;
import com.alex.code.foundation.di.module.BaseFragmentModule;

import javax.inject.Inject;
import javax.inject.Named;

@PerFragment
public class PerFragmentUtil {

    private final Fragment fragment;

    @Inject
    PerFragmentUtil(@Named(BaseFragmentModule.FRAGMENT) Fragment fragment) {
        this.fragment = fragment;
    }

    public String doSomething() {
        return "PerFragmentUtil: " + hashCode() + ", Fragment: " + fragment.hashCode();
    }
}
