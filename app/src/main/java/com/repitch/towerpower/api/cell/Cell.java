
package com.repitch.towerpower.api.cell;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.repitch.towerpower.connection.Connectivity;

public abstract class Cell {

    /**
     * Radio type of the device (Optional). Type: string; Value: gsm
     */
    @SerializedName("radio")
    @Expose
    private String radio;

    /**
     * the Location Area Code of your operator's network.
     * Type: integer; Range: 1 to 65533
     */
    @SerializedName("lac")
    @Expose
    private Integer lac;

    /**
     * the CellInfo ID.
     * Type: integer; Range: 0 to 65535
     */
    @SerializedName("cid")
    @Expose
    private Integer cid;

    /**
     * Mobile Country Code of your operator's network (Optional).
     * Type: integer; Range: 0 to 999
     */
    @SerializedName("mcc")
    @Expose
    private Integer mcc;

    /**
     * Mobile Network Code of your operator's network represented by an integer (Optional).
     * Type: integer; Range: 0 to 999.
     */
    @SerializedName("mnc")
    @Expose
    private Integer mnc;

    @Nullable
    public static Cell from(int lac, int cid, String radio) {
        switch (radio) {
            case Connectivity.RADIO_GSM:
                return new GsmCell(lac, cid);
            case Connectivity.RADIO_CDMA:
                return new CdmaCell(lac, cid);
            case Connectivity.RADIO_UMTS:
                return new UmtsCell(lac, cid);
            case Connectivity.RADIO_LTE:
                return new LteCell(lac, cid);
            default:
                return null;
        }
    }

    public Cell(int lac, int cid, String radio) {
        this.lac = lac;
        this.cid = cid;
        this.radio = radio;
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

    public abstract int getAsu();

    public abstract void setAsu(int asu);

}
