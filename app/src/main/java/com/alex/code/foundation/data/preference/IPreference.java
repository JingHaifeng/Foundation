package com.alex.code.foundation.data.preference;

public interface IPreference {
    // 按照一下格式进行
    // KEY
    // set
    // get

    String IS_FIRST_RUN = "is_first_run";

    void setIsFirstRun(boolean isFirstRun);

    boolean isFirstRun();
}
