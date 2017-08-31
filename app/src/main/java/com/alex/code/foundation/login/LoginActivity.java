package com.alex.code.foundation.login;

import android.animation.LayoutTransition;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.alex.code.foundation.R;
import com.alex.code.foundation.base.BaseMvpActivity;
import com.alex.code.foundation.login.module.AuthCredentials;
import com.alex.code.foundation.utils.KeyboardUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dd.processbutton.iml.ActionProcessButton;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/foundation/login")
public class LoginActivity extends BaseMvpActivity<LoginView, LoginPresenter, LoginViewState> implements LoginView {

    @BindView(R.id.loginForm)
    ViewGroup mLoginForm;

    @BindView(R.id.username)
    EditText mUserName;

    @BindView(R.id.password)
    EditText mPassword;

    @BindView(R.id.loginButton)
    ActionProcessButton mLoginButton;

    @BindView(R.id.errorView)
    View mErrorView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        int startDelay = getResources().getInteger(android.R.integer.config_mediumAnimTime) + 100;
        LayoutTransition transition = new LayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);
        transition.setStartDelay(LayoutTransition.APPEARING, startDelay);
        transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, startDelay);
        mLoginForm.setLayoutTransition(transition);
    }

    @Override
    protected void onFirstCreate() {
        super.onFirstCreate();
        showLoginForm();
    }

    @OnClick(R.id.loginButton)
    public void onLoginClicked() {
        String username = mUserName.getText().toString();
        String password = mPassword.getText().toString();

        mLoginForm.clearAnimation();

        if (TextUtils.isEmpty(username)) {
            mUserName.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            mUserName.startAnimation(shake);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            mPassword.startAnimation(shake);
            return;
        }

        if (!KeyboardUtils.hideKeyboard(mUserName)) {
            KeyboardUtils.hideKeyboard(mPassword);
        }

        getPresenter().login(new AuthCredentials(username, password));
    }

    @Override
    public void showLoginForm() {
        getViewState().setShowLoginForm();
        mErrorView.setVisibility(View.GONE);
        setFormEnabled(true);
        mLoginButton.setProgress(0);
    }

    @Override
    public void showError() {
        getViewState().setShowError();
        setFormEnabled(true);
        mLoginButton.setProgress(0);

        if (!isRestoringViewState()) {
            // Enable animations only if not restoring view state
            mLoginForm.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            mLoginForm.startAnimation(shake);
        }

        mErrorView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showLoading() {
        getViewState().setShowLoading();
        mErrorView.setVisibility(View.GONE);
        setFormEnabled(false);
        // any progress between 0 - 100 shows animation
        mLoginButton.setProgress(30);
    }

    @Override
    public void loginSuccessful() {
        mLoginButton.setProgress(100); // We are done
        ARouter.getInstance().build("/foundation/main").navigation();
        finish();
        overridePendingTransition(0, R.anim.zoom_out);
    }

    private void setFormEnabled(boolean enabled) {
        mUserName.setEnabled(enabled);
        mPassword.setEnabled(enabled);
        mLoginButton.setEnabled(enabled);
    }
}
