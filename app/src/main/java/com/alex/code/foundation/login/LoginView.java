package com.alex.code.foundation.login;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface LoginView extends MvpView {

    void showLoginForm();

    void showError();

    void showLoading();

    void loginSuccessful();
}
