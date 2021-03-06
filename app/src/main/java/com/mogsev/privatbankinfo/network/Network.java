package com.mogsev.privatbankinfo.network;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */
public final class Network {
    private static final String TAG = Network.class.getSimpleName();

    public static final int CONNECT_TIMEOUT_DEFAULT = 10;
    public static final int WRITE_TIMEOUT_DEFAULT = 20;
    public static final int READ_TIMEOUT_DEFAULT = 30;

    @NonNull
    public static final OkHttpClient okHttpClientDefault = new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .build();

    @NonNull
    public static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @NonNull
    public static final ApiPrivatBank API_PRIVATBANK = new Retrofit.Builder()
            .baseUrl(ApiPrivatBank.BASE_URL)
            .client(okHttpClientDefault)
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
            .create(ApiPrivatBank.class);

    public static void showResponseErrorBody(String tag, @NonNull Response<?> response) {
        if (response == null) {
            throw new NullPointerException("response cannot be null");
        }
        try {
            String error = response.errorBody().string();
            if (!TextUtils.isEmpty(error))
                Log.e(tag, "Response error: ".concat(error));
        } catch (IOException e) {
            if (!TextUtils.isEmpty(e.getMessage()))
                Log.e(tag, "IOException: ".concat(e.getMessage()));
        } catch (Exception e) {
            if (!TextUtils.isEmpty(e.getMessage()))
                Log.e(tag, "Exception: ".concat(e.getMessage()));
        }
    }
}
