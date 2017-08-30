package com.alex.code.foundation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alex.code.foundation.R;
import com.alex.code.foundation.data.preference.IPreference;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    IPreference mIPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
