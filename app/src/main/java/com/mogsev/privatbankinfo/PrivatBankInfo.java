package com.mogsev.privatbankinfo;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */
public class PrivatBankInfo extends Application {
    private static final String TAG = PrivatBankInfo.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        // use vector drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
