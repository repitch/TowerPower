package com.repitch.towerpower.db.entity;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by repitch on 02.07.17.
 */
public class Position implements Serializable {
    public final double latitude;
    public final double longitude;

    public Position(LatLng latLng) {
        latitude = latLng.latitude;
        longitude = latLng.longitude;
    }

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
