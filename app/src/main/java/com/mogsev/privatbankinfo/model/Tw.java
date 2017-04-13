package com.mogsev.privatbankinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */
public class Tw implements Parcelable {
    private static final String TAG = Tw.class.getSimpleName();

    public static final String BUNDLE_NAME = Tw.class.getSimpleName();
    private static final String MONDAY = "mon";
    private static final String TUESDAY = "tue";
    private static final String WEDNESDAY = "wed";
    private static final String THURSDAY = "thu";
    private static final String FRIDAY = "fri";
    private static final String SATURDAY = "sat";
    private static final String SUNDAY = "sun";
    private static final String HOLIDAY = "hol";

    @Expose
    @SerializedName(MONDAY)
    private String monday;

    @Expose
    @SerializedName(TUESDAY)
    private String tuesday;

    @Expose
    @SerializedName(WEDNESDAY)
    private String wednesday;

    @Expose
    @SerializedName(THURSDAY)
    private String thursday;

    @Expose
    @SerializedName(FRIDAY)
    private String friday;

    @Expose
    @SerializedName(SATURDAY)
    private String saturday;

    @Expose
    @SerializedName(SUNDAY)
    private String sunday;

    @Expose
    @SerializedName(HOLIDAY)
    private String holiday;

    protected Tw(final Parcel in) {
        monday = in.readString();
        tuesday = in.readString();
        wednesday = in.readString();
        thursday = in.readString();
        friday = in.readString();
        saturday = in.readString();
        sunday = in.readString();
        holiday = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeString(monday);
        parcel.writeString(tuesday);
        parcel.writeString(wednesday);
        parcel.writeString(thursday);
        parcel.writeString(friday);
        parcel.writeString(saturday);
        parcel.writeString(sunday);
        parcel.writeString(holiday);
    }

    public static final Parcelable.Creator<Tw> CREATOR = new Parcelable.Creator<Tw>() {
        @Override
        public Tw createFromParcel(Parcel in) {
            return new Tw(in);
        }

        @Override
        public Tw[] newArray(int size) {
            return new Tw[size];
        }
    };

    @Override
    public String toString() {
        return "Tw{" +
                "monday='" + monday + '\'' +
                ", tuesday='" + tuesday + '\'' +
                ", wednesday='" + wednesday + '\'' +
                ", thursday='" + thursday + '\'' +
                ", friday='" + friday + '\'' +
                ", saturday='" + saturday + '\'' +
                ", sunday='" + sunday + '\'' +
                ", holiday='" + holiday + '\'' +
                '}';
    }
}
