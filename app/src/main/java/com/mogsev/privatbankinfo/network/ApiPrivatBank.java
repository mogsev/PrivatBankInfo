package com.mogsev.privatbankinfo.network;

import com.mogsev.privatbankinfo.model.Infrastructure;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */
public interface ApiPrivatBank {
    public static final String BASE_URL = "https://api.privatbank.ua";

    public static final String HEADER_ACCEPT_APP_JSON = "Accept: application/json";
    public static final String HEADER_CONTENT_TYPE_APP_JSON = "Content-type: application/json";

    @Headers({HEADER_ACCEPT_APP_JSON, HEADER_CONTENT_TYPE_APP_JSON})
    @GET("p24api/infrastructure?atm")
    Call<Infrastructure> getInfrastructure(@QueryMap Map<String, String> options);
}
