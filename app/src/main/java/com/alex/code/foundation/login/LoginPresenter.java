package com.alex.code.foundation.login;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView> {

    @Inject
    public LoginPresenter() {
    }

    public void login() {
        getView().loginSuccessful();
    }
}
