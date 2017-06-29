package com.repitch.towerpower.db;

import java.io.Serializable;

/**
 * @author i.s.pyavkin
 */
public class SignalInfo implements Serializable {

    private String operatorName;

    // TelephonyManager#getNetworkType()
    private String networkType;

    // Connectivity#getNetworkClassAsString(int networkType)
    // can be 2G, 3G, 4G or Unknown
    private String networkClass;

    /**
     * Connectivity#getRadioType(int networkType)
     * Can be: gsm, cdma, umts, lte or unknown
     */
    private String radioType;

}
