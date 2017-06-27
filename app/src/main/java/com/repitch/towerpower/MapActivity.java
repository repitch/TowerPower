package com.repitch.towerpower;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by repitch on 27.06.17.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String EXTRA_POSITION = "position";

    private GoogleMap mMap;

    public static Intent createIntent(Context context, @Nullable LatLng position) {
        return new Intent(context, MapActivity.class)
                .putExtra(EXTRA_POSITION, position);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng position = getIntent().getParcelableExtra(EXTRA_POSITION);
        if (position != null) {
            mMap.addMarker(new MarkerOptions().position(position).title("Here is BS"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
        }

    }
}
