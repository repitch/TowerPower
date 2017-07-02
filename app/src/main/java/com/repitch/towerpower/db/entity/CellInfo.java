package com.repitch.towerpower.db.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author i.s.pyavkin
 */
@DatabaseTable(tableName = "cell_info")
public class CellInfo implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    // Mobile Country Code (250 for russia)
    @DatabaseField
    private int mcc;

    // Mobile network code (20 for tele2)
    @DatabaseField
    private int mnc;

    // Location Area Code
    @DatabaseField
    private int lac;

    // CellInfo ID
    @DatabaseField
    private int cid;

    public CellInfo() {
        // empty
    }

    public CellInfo(int mcc, int mnc, int lac, int cid) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.lac = lac;
        this.cid = cid;
    }

    public int getOperatorCode() {
        return Integer.parseInt(String.valueOf(mcc) + String.valueOf(mnc));
    }

    public int getMcc() {
        return mcc;
    }

    public int getMnc() {
        return mnc;
    }

    public int getLac() {
        return lac;
    }

    public int getCid() {
        return cid;
    }
}
