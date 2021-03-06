package com.alex.code.foundation.login;

import android.text.TextUtils;

import com.alex.code.foundation.base.BaseMvpPresenter;
import com.alex.code.foundation.login.module.AuthCredentials;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BaseMvpPresenter<LoginView> {

    private AuthCredentials mFakeAuthCredentials = new AuthCredentials("abc", "123");

    @Inject
    public LoginPresenter() {
    }

    public void login(AuthCredentials authCredentials) {
        getView().showLoading();

        addDisposable(Observable.just(authCredentials)
                .map(credentials -> {
                    try {
                        // Simulate network delay
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return TextUtils.equals(credentials.getUsername(), mFakeAuthCredentials.getUsername())
                            && TextUtils.equals(credentials.getPassword(), mFakeAuthCredentials.getPassword());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isAccessed -> {
                    if (isAccessed) {
                        getView().loginSuccessful();
                    } else {
                        getView().showError();
                    }
                }));
    }

}
