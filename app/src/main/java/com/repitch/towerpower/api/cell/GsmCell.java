package com.repitch.towerpower.api.cell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.repitch.towerpower.connection.Connectivity;

/**
 * Created by repitch on 01.07.17.
 */
public class GsmCell extends Cell {

    /**
     * the Signal strength (RSSI) of the radio, measured in dBm (Optional).
     * Type: integer; Range: -51 to -113
     */
    @SerializedName("signal")
    @Expose
    private int signal;

    /**
     * the Arbitrary Strength Unit measured by the mobile phone (Optional).
     * Type: integer; Range: 0 to 31
     */
    @SerializedName("asu")
    @Expose
    private int asu;

    /**
     *  the Timing Advance value (Optional).
     *  Type: integer; Range: 0 to 63
     */
    @SerializedName("tA")
    @Expose
    private int tA;

    public GsmCell(int lac, int cid) {
        super(lac, cid, Connectivity.RADIO_GSM);
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    @Override
    public int getAsu() {
        return asu;
    }

    public void setAsu(int asu) {
        this.asu = asu;
    }

    public int gettA() {
        return tA;
    }

    public void settA(int tA) {
        this.tA = tA;
    }

}
