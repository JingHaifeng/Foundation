package com.alex.code.foundation.di.test;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SingletonUtil {

    @Inject
    public SingletonUtil() {
    }

    public String toTest() {
        return "SingletonUtil:" + hashCode();
    }
}
