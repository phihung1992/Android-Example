package com.hung.smap;

import android.app.Activity;
import android.os.Bundle;

import com.example.mapdemo.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends Activity {
    private MapManager mapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mapManager = new MapManager(MainActivity.this, googleMap);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mapManager.disconnectGoogleApiClient();
        super.onDestroy();
    }
}
