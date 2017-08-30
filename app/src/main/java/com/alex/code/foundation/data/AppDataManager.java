package com.alex.code.foundation.data;

import com.alex.code.foundation.data.network.IApi;
import com.alex.code.foundation.data.preference.IPreference;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AppDataManager implements IDataManager {

    private final IPreference mIPreference;

    private final IApi mIApi;

    @Inject
    public AppDataManager(IPreference IPreference, IApi IApi) {
        mIPreference = IPreference;
        mIApi = IApi;
    }

    @Override
    public IPreference getPreferenceHelper() {
        return mIPreference;
    }

    @Override
    public IApi getApi() {
        return mIApi;
    }
}
