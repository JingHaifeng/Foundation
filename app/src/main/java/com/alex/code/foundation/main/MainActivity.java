package com.alex.code.foundation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alex.code.foundation.R;
import com.alex.code.foundation.data.preference.IPreference;
import com.alibaba.android.arouter.facade.annotation.Route;

import javax.inject.Inject;

@Route(path = "/foundation/main")
public class MainActivity extends AppCompatActivity {

    @Inject
    IPreference mIPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
