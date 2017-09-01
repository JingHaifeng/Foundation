package com.alex.code.foundation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseMvpViewStateActivity<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
        extends MvpViewStateActivity<V, P, VS>
        implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    @Inject
    Provider<P> mPresenterProvider;

    @Inject
    CompositeDisposable mCompositeDisposable;

    @Inject
    Provider<VS> mViewStateProvider;

    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    // Delegate propagation ***********************

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mCompositeDisposable.dispose();
    }

    @NonNull
    @Override
    public P createPresenter() {
        return mPresenterProvider.get();
    }

    @NonNull
    @Override
    public VS createViewState() {
        return mViewStateProvider.get();
    }

    @Override
    public void onNewViewStateInstance() {
        onFirstCreate();
    }

    protected void onFirstCreate() {

    }

    protected void addDispose(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }
}
