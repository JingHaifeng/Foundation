package com.alex.code.foundation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby3.mvp.delegate.MvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.HasFragmentInjector;

public abstract class BaseActivity<
        VIEW extends MvpView,
        PRESENTER extends MvpPresenter<VIEW>,
        VIEW_STATE extends ViewState<VIEW>>
        extends AppCompatActivity implements MvpViewStateDelegateCallback<VIEW, PRESENTER, VIEW_STATE>,
        HasFragmentInjector {

    @Inject
    Provider<PRESENTER> mPresenterProvider;
    private PRESENTER mPresenter;

    @Inject
    Provider<VIEW_STATE> mViewStateProvider;
    private VIEW_STATE mViewState;
    private boolean mViewStateRestoreInProgress;

    protected ActivityMvpDelegate<VIEW, PRESENTER> mActivityMvpDelegate;

    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    protected abstract int getLayoutId();

    // Delegate propagation ***********************

    @SuppressWarnings("unchecked")
    private ActivityMvpDelegate<VIEW, PRESENTER> getMvpDelegate() {
        if (mActivityMvpDelegate == null) {
            mActivityMvpDelegate = new ActivityMvpViewStateDelegateImpl<>(this, this, true);
        }
        return mActivityMvpDelegate;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        getMvpDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

    // MVP related *********************************************************************************

    @Override
    @SuppressWarnings("unchecked")
    public VIEW getMvpView() {
        return (VIEW) this;
    }

    @NonNull
    @Override
    public PRESENTER createPresenter() {
        return mPresenterProvider.get();
    }

    @Override
    public PRESENTER getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(PRESENTER presenter) {
        mPresenter = presenter;
    }

    // View state related **************************************************************************


    @NonNull
    @Override
    public VIEW_STATE createViewState() {
        return mViewStateProvider.get();
    }

    @Override
    public VIEW_STATE getViewState() {
        return mViewState;
    }

    @Override
    public void setViewState(VIEW_STATE viewState) {
        mViewState = viewState;
    }

    @Override
    public void setRestoringViewState(boolean restoringViewState) {
        mViewStateRestoreInProgress = restoringViewState;
    }

    public boolean isViewStateRestoreInProgress() {
        return mViewStateRestoreInProgress;
    }

    /**
     * Called right after the state of the view has been restored from the {@link VIEW_STATE}.
     *
     * @param instanceStateRetained
     */
    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        // 默认为空，当需要是复写
    }

    @Override
    public void onNewViewStateInstance() {
        onFirstCreate();
    }

    /**
     * Default implementation not doing anything. Override when required to perform long running
     * tasks only once, then save their state in the {@link VIEW_STATE}
     */
    protected void onFirstCreate() {
        // 默认为空，当需要是复写
    }

}
