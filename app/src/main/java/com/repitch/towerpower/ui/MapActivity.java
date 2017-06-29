package com.repitch.towerpower.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.repitch.towerpower.R;

/**
 * Created by repitch on 27.06.17.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String EXTRA_POSITION = "position";
    private static final String EXTRA_RADIUS = "radius";

    private GoogleMap map;

    public static Intent createIntent(Context context, @Nullable LatLng position, int radius) {
        return new Intent(context, MapActivity.class)
                .putExtra(EXTRA_POSITION, position)
                .putExtra(EXTRA_RADIUS, radius);
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
        map = googleMap;
        map.setMyLocationEnabled(true);
        LatLng position = getIntent().getParcelableExtra(EXTRA_POSITION);
        int radius = getIntent().getIntExtra(EXTRA_RADIUS, 0);
        if (position != null) {
            Circle circle = map.addCircle(new CircleOptions()
                    .center(position)
                    .radius(radius > 0 ? radius : 100)
                    .strokeWidth(2)
                    .strokeColor(ContextCompat.getColor(this, R.color.mapCircleStroke))
                    .fillColor(ContextCompat.getColor(this, R.color.mapCircleFill)));
            map.moveCamera(CameraUpdateFactory.zoomTo(12.0f));
            map.moveCamera(CameraUpdateFactory.newLatLng(position));
        }

    }
}
