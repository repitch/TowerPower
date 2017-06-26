
package com.repitch.towerpower.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cell {

    public Cell (int lac, int cid) {
        this.lac = lac;
        this.cid = cid;
    }

    @SerializedName("lac")
    @Expose
    private Integer lac;
    @SerializedName("cid")
    @Expose
    private Integer cid;
    @SerializedName("signal")
    @Expose
    private Integer signal;
    @SerializedName("tA")
    @Expose
    private Integer tA;

    public Integer getLac() {
        return lac;
    }

    public void setLac(Integer lac) {
        this.lac = lac;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSignal() {
        return signal;
    }

    public void setSignal(Integer signal) {
        this.signal = signal;
    }

    public Integer getTA() {
        return tA;
    }

    public void setTA(Integer tA) {
        this.tA = tA;
    }

}
