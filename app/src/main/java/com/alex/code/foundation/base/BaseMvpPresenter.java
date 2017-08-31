package com.alex.code.foundation.base;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseMvpPresenter<VIEW extends MvpView> extends MvpNullObjectBasePresenter<VIEW> {

    @Inject
    CompositeDisposable mCompositeDisposable;

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        mCompositeDisposable.dispose();
    }
}
