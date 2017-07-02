package com.repitch.towerpower.api.cell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.repitch.towerpower.connection.Connectivity;

/**
 * Created by repitch on 01.07.17.
 */
public class UmtsCell extends Cell {

    /**
     * the Signal strength (RSCP) of the radio, measured in dBm (Optional).
     * Type: integer; Range: -25 to -121
     */
    @SerializedName("signal")
    @Expose
    private int signal;

    /**
     * the Primary scrambling code (Optional).
     * Type: integer; Range: 0 to 511
     */
    @SerializedName("psc")
    @Expose
    private int psc;

    /**
     * the Arbitrary Strength Unit measured by the mobile phone (Optional).
     * Type: integer; Range: -5 to 91
     */
    @SerializedName("asu")
    @Expose
    private int asu;

    public UmtsCell(int lac, int cid) {
        super(lac, cid, Connectivity.RADIO_UMTS);
    }

    public int getPsc() {
        return psc;
    }

    public void setPsc(Integer psc) {
        this.psc = psc;
    }

    @Override
    public int getAsu() {
        return asu;
    }

    public void setAsu(int asu) {
        this.asu = asu;
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }
}
