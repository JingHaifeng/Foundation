package com.alex.code.foundation.login;

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

import javax.inject.Inject;

public class LoginViewState implements ViewState<LoginView> {

    private final int STATE_SHOW_LOGIN_FORM = 0x00;
    private final int STATE_SHOW_LOADING = 0x01;
    private final int STATE_SHOW_ERROR = 0x02;

    private int mState = STATE_SHOW_LOGIN_FORM;

    @Inject
    public LoginViewState() {
    }

    @Override
    public void apply(LoginView view, boolean retained) {
        switch (mState) {
            case STATE_SHOW_LOGIN_FORM:
                view.showLoginForm();
                break;
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;
            case STATE_SHOW_ERROR:
                view.showError();
                break;
        }
    }

    public void setShowLoginForm() {
        mState = STATE_SHOW_LOGIN_FORM;
    }

    public void setShowLoading() {
        mState = STATE_SHOW_LOADING;
    }

    public void setShowError() {
        mState = STATE_SHOW_ERROR;
    }
}
