package com.mogsev.privatbankinfo.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */
public final class MainHelper {
    private static final String TAG = MainHelper.class.getSimpleName();

    /**
     * Show keyboard
     */
    public static void requestFocus(@NonNull final Activity activity, @NonNull final View view) {
        Log.i(TAG, "requestFocus");
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null");
        }
        if (view == null) {
            throw new IllegalArgumentException("View cannot be null");
        }
        if (view.requestFocus()) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
