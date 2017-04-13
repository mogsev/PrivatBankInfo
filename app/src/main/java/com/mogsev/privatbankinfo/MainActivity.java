package com.mogsev.privatbankinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mogsev.privatbankinfo.adapter.DevicesRvAdapter;
import com.mogsev.privatbankinfo.model.Device;
import com.mogsev.privatbankinfo.model.Infrastructure;
import com.mogsev.privatbankinfo.network.Network;
import com.mogsev.privatbankinfo.utils.MainHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private DevicesRvAdapter mDevicesRvAdapter;

    private EditText mEditTextCity;
    private EditText mEditTextAddress;
    private ImageView mImageViewSearch;
    private TextView mTextViewNoResult;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Map<String, String> mQueryMap;
    private boolean isSearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        // initialize view
        initView();
    }

    private void initView() {
        Log.i(TAG, "initView");
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mEditTextCity = (EditText) findViewById(R.id.etCity);
        mEditTextAddress = (EditText) findViewById(R.id.etAddress);
        mImageViewSearch = (ImageView) findViewById(R.id.ivSearch);
        mTextViewNoResult = (TextView) findViewById(R.id.tvNoResult);
        mImageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSearch) {
                    onSearch(mEditTextCity.getText().toString(), mEditTextAddress.getText().toString());
                }
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.rvDefault);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDevicesRvAdapter = new DevicesRvAdapter(this);
        mRecyclerView.setAdapter(mDevicesRvAdapter);

    }

    private void onSearch(@Nullable String city, @Nullable String address) {
        Log.i(TAG, "onSearch");
        isSearch = true;
        mProgressBar.setVisibility(View.VISIBLE);
        if (mQueryMap == null) {
            mQueryMap = new HashMap<>();
        }
        if (TextUtils.isEmpty(city) && TextUtils.isEmpty(address)) {
            MainHelper.requestFocus(this, mEditTextCity);
            Toast.makeText(this, getString(R.string.toast_search_input_warning), Toast.LENGTH_SHORT).show();
            onSearchCancel();
            return;
        }
        mQueryMap.put(Infrastructure.CITY, city);
        mQueryMap.put(Infrastructure.ADDRESS, address);
        Call<Infrastructure> call = Network.API_PRIVATBANK.getInfrastructure(mQueryMap);
        call.enqueue(new Callback<Infrastructure>() {
            @Override
            public void onResponse(Call<Infrastructure> call, Response<Infrastructure> response) {
                if (response.isSuccessful()) {
                    Infrastructure inf = response.body();
                    Log.i(TAG, "Info: " + inf.toString());
                    List<Device> devices = inf.getDevices();
                    if (devices != null && !devices.isEmpty()) {
                        mDevicesRvAdapter.setList(inf.getDevices());
                        mTextViewNoResult.setVisibility(View.GONE);
                    } else {
                        mTextViewNoResult.setVisibility(View.VISIBLE);
                    }
                } else {
                    Network.showResponseErrorBody(TAG, response);
                }
                onSearchCancel();
            }

            @Override
            public void onFailure(Call<Infrastructure> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                onSearchCancel();
            }
        });
    }

    private void onSearchCancel() {
        isSearch = false;
        if (mProgressBar != null) mProgressBar.setVisibility(View.GONE);
    }

}
