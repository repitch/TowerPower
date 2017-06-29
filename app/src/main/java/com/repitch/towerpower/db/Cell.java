package com.repitch.towerpower.db;

import com.j256.ormlite.table.DatabaseTable;

/**
 * @author i.s.pyavkin
 */
@DatabaseTable(tableName = "cell")
public class Cell {

    // Mobile Country Code (250 for russia)
    private int mcc;

    // Mobile network code (20 for tele2)
    private int mnc;

    // Location Area Code
    private int lac;

    // Cell ID
    private int cid;

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
