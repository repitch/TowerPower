
package com.repitch.towerpower.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.repitch.towerpower.api.cell.Cell;

import java.util.List;

public class LocationRequest {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("radio")
    @Expose
    private String radio;
    @SerializedName("mcc")
    @Expose
    private Integer mcc;
    @SerializedName("mnc")
    @Expose
    private Integer mnc;
    @SerializedName("cells")
    @Expose
    private List<Cell> cells = null;
    @SerializedName("address")
    @Expose
    private Integer address;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public Integer getMcc() {
        return mcc;
    }

    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    public Integer getMnc() {
        return mnc;
    }

    public void setMnc(Integer mnc) {
        this.mnc = mnc;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

}
