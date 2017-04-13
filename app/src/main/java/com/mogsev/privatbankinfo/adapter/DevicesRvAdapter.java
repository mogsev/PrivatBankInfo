package com.mogsev.privatbankinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mogsev.privatbankinfo.R;
import com.mogsev.privatbankinfo.activity.MapsActivity;
import com.mogsev.privatbankinfo.model.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */
public class DevicesRvAdapter extends RecyclerView.Adapter<DevicesRvAdapter.ViewHolder> {
    private static final String TAG = DevicesRvAdapter.class.getSimpleName();

    private List<Device> mList = new ArrayList<>();
    private Context mContext;
    private String mCountryCode = Locale.getDefault().getCountry();

    public DevicesRvAdapter(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_device, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Device device = mList.get(position);
        String value = null;
        // set type
        value = device.getType();
        holder.tvType.setText(mContext.getString(R.string.card_device_type_with_arguments, value));
        // set city
        value = device.getCity(mCountryCode);
        holder.tvCity.setText(mContext.getString(R.string.card_device_city_with_arguments, value));
        // set address
        value = device.getFullAddress(mCountryCode);
        holder.tvAddress.setText(mContext.getString(R.string.card_device_address_with_arguments, value));
        // set place
        value = device.getPlace(mCountryCode);
        holder.tvPlace.setText(mContext.getString(R.string.card_device_place_with_arguments, value));
        holder.llDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapsActivity.class);
                intent.putExtra(Device.BUNDLE_NAME, device);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(@Nullable List<Device> list) {
        if (list != null && !list.isEmpty()) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    /**
     * Class is like the helper
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llDevice;
        public TextView tvType;
        public TextView tvCity;
        public TextView tvAddress;
        public TextView tvPlace;

        public ViewHolder(View itemView) {
            super(itemView);
            llDevice = (LinearLayout) itemView.findViewById(R.id.llDevice);
            tvType = (TextView) itemView.findViewById(R.id.tvType);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
            tvPlace = (TextView) itemView.findViewById(R.id.tvPlace);
        }
    }

}
