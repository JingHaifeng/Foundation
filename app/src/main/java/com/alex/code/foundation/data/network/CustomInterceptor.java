package com.alex.code.foundation.data.network;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建 OkHTTP 的拦截器
 */
public class CustomInterceptor implements Interceptor {

    @Inject
    public CustomInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl build = request.url().newBuilder().build();
        return chain.proceed(request.newBuilder().url(build).build());
    }
}
