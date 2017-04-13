package com.mogsev.privatbankinfo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */
public class Device implements Parcelable {
    private static final String TAG = Device.class.getSimpleName();

    public static final String BUNDLE_NAME = Device.class.getSimpleName();
    private static final String TYPE = "type";
    private static final String CITY_RU = "cityRU";
    private static final String CITY_UA = "cityUA";
    private static final String CITY_EN = "cityEN";
    private static final String FULL_ADDRESS_RU = "fullAddressRu";
    private static final String FULL_ADDRESS_UA = "fullAddressUa";
    private static final String FULL_ADDRESS_EN = "fullAddressEn";
    private static final String PLACE_RU = "placeRu";
    private static final String PLACE_UA = "placeUa";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String TW = "tw";

    private static final String RU = "RU";
    private static final String UA = "UA";

    @Expose
    @SerializedName(TYPE)
    private String type;

    @Expose
    @SerializedName(CITY_RU)
    private String cityRu;

    @Expose
    @SerializedName(CITY_UA)
    private String cityUa;

    @Expose
    @SerializedName(CITY_EN)
    private String cityEn;

    @Expose
    @SerializedName(FULL_ADDRESS_RU)
    private String fullAddressRu;

    @Expose
    @SerializedName(FULL_ADDRESS_UA)
    private String fullAddressUa;

    @Expose
    @SerializedName(FULL_ADDRESS_EN)
    private String fullAddressEn;

    @Expose
    @SerializedName(PLACE_RU)
    private String placeRu;

    @Expose
    @SerializedName(PLACE_UA)
    private String placeUa;

    @Expose
    @SerializedName(LATITUDE)
    private String latitude;

    @Expose
    @SerializedName(LONGITUDE)
    private String longitude;

    @Expose
    @SerializedName(TW)
    private Tw tw;

    protected Device(final Parcel in) {
        type = in.readString();
        cityRu = in.readString();
        cityUa = in.readString();
        cityEn = in.readString();
        fullAddressRu = in.readString();
        fullAddressUa = in.readString();
        fullAddressEn = in.readString();
        placeRu = in.readString();
        placeUa = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        tw = in.readParcelable(getClass().getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeString(type);
        parcel.writeString(cityRu);
        parcel.writeString(cityUa);
        parcel.writeString(cityEn);
        parcel.writeString(fullAddressRu);
        parcel.writeString(fullAddressUa);
        parcel.writeString(fullAddressEn);
        parcel.writeString(placeRu);
        parcel.writeString(placeUa);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeParcelable(tw, flags);
    }

    public String getType() {
        return type;
    }

    @Nullable
    public String getCity(@Nullable String lngCode) {
        if (TextUtils.isEmpty(lngCode)) {
            return cityEn;
        }
        switch (lngCode) {
            case RU:
                return cityRu;
            case UA:
                return cityUa;
            default:
                return cityEn;
        }
    }

    public String getCityRu() {
        return cityRu;
    }

    public String getCityUa() {
        return cityUa;
    }

    public String getCityEn() {
        return cityEn;
    }

    @Nullable
    public String getFullAddress(@Nullable String lngCode) {
        if (TextUtils.isEmpty(lngCode)) {
            return fullAddressEn;
        }
        switch (lngCode) {
            case RU:
                return fullAddressRu;
            case UA:
                return fullAddressUa;
            default:
                return fullAddressEn;
        }
    }

    public String getFullAddressRu() {
        return fullAddressRu;
    }

    public String getFullAddressUa() {
        return fullAddressUa;
    }

    public String getFullAddressEn() {
        return fullAddressEn;
    }

    @Nullable
    public String getPlace(@Nullable String lngCode) {
        if (TextUtils.isEmpty(lngCode)) {
            return placeUa;
        }
        switch (lngCode) {
            case RU:
                return placeRu;
            default:
                return placeUa;
        }
    }

    public String getPlaceRu() {
        return placeRu;
    }

    public String getPlaceUa() {
        return placeUa;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Tw getTw() {
        return tw;
    }

    @NonNull
    public LatLng getLatlng() {
        LatLng latLng = null;
        try {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);
            latLng = new LatLng(lat, lon);
        } catch (Exception ex) {
            Log.e(TAG, "LatLng did not create: " + ex.getMessage());
            return new LatLng(0, 0);
        }
        return latLng;
    }

    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    @Override
    public String toString() {
        return "Device{" +
                "type='" + type + '\'' +
                ", cityRu='" + cityRu + '\'' +
                ", cityUa='" + cityUa + '\'' +
                ", cityEn='" + cityEn + '\'' +
                ", fullAddressRu='" + fullAddressRu + '\'' +
                ", fullAddressUa='" + fullAddressUa + '\'' +
                ", fullAddressEn='" + fullAddressEn + '\'' +
                ", placeRu='" + placeRu + '\'' +
                ", placeUa='" + placeUa + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", tw=" + tw +
                '}';
    }
}
