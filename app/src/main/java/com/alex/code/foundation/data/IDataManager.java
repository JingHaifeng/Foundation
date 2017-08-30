package com.alex.code.foundation.data;

import com.alex.code.foundation.data.network.IApi;
import com.alex.code.foundation.data.preference.IPreference;

public interface IDataManager {

    IPreference getPreferenceHelper();

    IApi getApi();
}
