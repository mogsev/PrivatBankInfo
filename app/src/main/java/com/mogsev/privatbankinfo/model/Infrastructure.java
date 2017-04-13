package com.mogsev.privatbankinfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */
public class Infrastructure {
    private static final String TAG = Infrastructure.class.getSimpleName();

    public static final String BUNDLE_NAME = Infrastructure.class.getSimpleName();
    public static final String CITY = "city";
    public static final String ADDRESS = "address";
    private static final String DEVICES = "devices";

    @Expose
    @SerializedName(CITY)
    private String city;

    @Expose
    @SerializedName(ADDRESS)
    private String address;

    @Expose
    @SerializedName(DEVICES)
    private List<Device> devices;

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public String toString() {
        return "Infrastructure{" +
                "city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", devices=" + devices +
                '}';
    }
}
