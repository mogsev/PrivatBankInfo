package com.mogsev.privatbankinfo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mogsev.privatbankinfo.R;
import com.mogsev.privatbankinfo.model.Device;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private static final String TAG = MapsActivity.class.getSimpleName();

    private LocationManager mLocationManager;
    private GoogleMap mMap;
    private Device mDevice;
    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(mLocation != null) {
            Log.i(TAG, "Location: " + mLocation.getLatitude() + " / " + mLocation.getLongitude());
        } else {
            Log.i(TAG, "Last location is empty");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30, 30, this);
        }

        if (savedInstanceState != null) {
            mDevice = savedInstanceState.getParcelable(Device.BUNDLE_NAME);
        } else {
            mDevice = getIntent().getParcelableExtra(Device.BUNDLE_NAME);
        }
        Log.i(TAG, "Device: " + mDevice.toString());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "onMapReady");
        mMap = googleMap;

        // Add a markers
        LatLng toLatLng = mDevice.getLatlng();
        mMap.addMarker(new MarkerOptions().position(toLatLng).title(mDevice.getType()));
        LatLng fromLatLng = null;
        if (mLocation != null) { // check and set direction
            fromLatLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(fromLatLng).title("from"));
            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.add(fromLatLng);
            polylineOptions.add(toLatLng);
            polylineOptions.color(Color.GREEN);
            mMap.addPolyline(polylineOptions);
        }
        // set camera to device
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toLatLng, 13.0f));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Device.BUNDLE_NAME, mDevice);
    }

    @Override
    public void onLocationChanged(Location location) {
        // empty
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // empty
    }

    @Override
    public void onProviderEnabled(String provider) {
        // empty
    }

    @Override
    public void onProviderDisabled(String provider) {
        // empty
    }
}
