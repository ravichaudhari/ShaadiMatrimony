package com.shaadi.matrimony.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class ShaadiMatrimony extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        
        MultiDex.install(ShaadiMatrimony.this);
    }

}
