package com.repitch.towerpower.api.cell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.repitch.towerpower.connection.Connectivity;

/**
 * Created by repitch on 02.07.17.
 */
public class CdmaCell extends Cell {

    /**
     * the Signal strength of the radio, measured in dBm (Optional).
     * Type: integer; Range: -75 to -100
     */
    @SerializedName("signal")
    @Expose
    private int signal;

    /**
     * the Arbitrary Strength Unit measured by the mobile phone (Optional).
     * Type: integer; Range: 1 to 16
     */
    @SerializedName("asu")
    @Expose
    private int asu;

    public CdmaCell(int lac, int cid) {
        super(lac, cid, Connectivity.RADIO_CDMA);
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(Integer signal) {
        this.signal = signal;
    }

    @Override
    public int getAsu() {
        return asu;
    }

    public void setAsu(int asu) {
        this.asu = asu;
    }
}
