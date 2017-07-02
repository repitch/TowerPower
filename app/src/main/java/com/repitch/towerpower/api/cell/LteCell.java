package com.repitch.towerpower.api.cell;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.repitch.towerpower.connection.Connectivity;

/**
 * Created by repitch on 01.07.17.
 */
public class LteCell extends Cell {

    /**
     * Signal strength (RSRP) of the radio, measured in dBm (Optional).
     * Type: integer; Range: -45 to -137
     */
    @SerializedName("signal")
    @Expose
    private int signal;

    /**
     * Timing Advance value (Optional).
     * Type: integer; Range: 0 to 63
     */
    @SerializedName("tA")
    @Expose
    private int tA;

    /**
     * Physical CellInfo ID on LTE (Optional). Type: integer; Range: 0 to 503
     */
    @SerializedName("psc")
    @Expose
    private int psc;

    /**
     * the Arbitrary Strength Unit measured by the mobile phone (Optional). Type: integer; Range: 0 to 97
     */
    @SerializedName("asu")
    @Expose
    private int asu;

    public LteCell(int lac, int cid) {
        super(lac, cid, Connectivity.RADIO_LTE);
    }

    public int getPsc() {
        return psc;
    }

    public void setPsc(int psc) {
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

    public int gettA() {
        return tA;
    }

    public void settA(int tA) {
        this.tA = tA;
    }
}
