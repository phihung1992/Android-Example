package com.hung.smap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mapdemo.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapManager implements GoogleMap.OnMyLocationButtonClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private final GoogleMap mMap;
    private Context context;
    private LocationManager locationManager;
    private double latitude;
    private double longitude;
    private GoogleApiClient googleApiClient;

    public MapManager(Context context, GoogleMap googleMap) {
        mMap = googleMap;
        this.context = context;

        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        initMap();
    }

    private boolean isGpsOn() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void showConfirmGps() {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Message")
                .setCancelable(true)
                .setMessage("GPS is off. Turn on to continued?")
                .setPositiveButton("Turn on", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        alertDialog.show();
    }

    private void initMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.setMyLocationEnabled(true);

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

        addMarker(new LatLng(20.985596, 105.636023), "Quốc Oai, Hà Nội, Việt Nam");
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(20.985596, 105.636023), 16);
        mMap.animateCamera(update);
    }

    public void addMarker(LatLng position, String title) {
        MarkerOptions marker = new MarkerOptions();
        marker.position(position);
        marker.title(title);
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        mMap.clear();
        mMap.addMarker(marker);
    }

    public void disconnectGoogleApiClient() {

        if (googleApiClient != null && googleApiClient.isConnected()) {
            //Huy su kien lang nghe
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            //Disconect
            googleApiClient.disconnect();
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        if (isGpsOn()) {
            //return false de chuong trinh chay
            //de hien thi vi tri hien tai
            return false;
        }
        //Bat ra dialog bat nguoi dung bat GPS
        showConfirmGps();
        return true;
    }

    private void addMyMarker(String name, double latitude, double longitude) {
        MarkerOptions options = new MarkerOptions();
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        options.title("My Location");
        options.position(new LatLng(latitude, longitude));

        Marker marker = mMap.addMarker(options);
        marker.setTag(name);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            addMyMarker("Hung", latitude, longitude);
            addMyMarker("Mai", latitude - 0.0002, longitude - 0.0002);

            LocationRequest locationRequest = new LocationRequest();

            //10s cap nhat 1 lan
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(5000);
            locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //Khi thay doi tra ve location
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private LayoutInflater inflater;
        private View view;
        private ImageView ivPhoto;
        private TextView tvName;

        public MyInfoWindowAdapter() {

            inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.view_info_window, null);
            ivPhoto = (ImageView) view.findViewById(R.id.iv_avatar);
            tvName = (TextView) view.findViewById(R.id.tv_name);
        }

        @Override
        public View getInfoContents(Marker marker) {
//            String name = (String) marker.getTag();
//            tvName.setText(name);
            return view;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            String name = (String) marker.getTag();
            tvName.setText(name);
            return view;
        }
    }
}
