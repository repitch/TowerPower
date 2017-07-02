package com.repitch.towerpower.db.entity;

import com.repitch.towerpower.connection.Connectivity;

import java.io.Serializable;

/**
 * @author i.s.pyavkin
 */
public class SignalInfo implements Serializable {

    private String operatorName;

    // TelephonyManager#getNetworkType()
    private int networkType;

    private int asuStrength;

    public int getAsuStrength() {
        return asuStrength;
    }

    public String getRadioType() {
        return Connectivity.getRadioType(networkType);
    }

    public int getNetworkClass() {
        return Connectivity.getNetworkClass(networkType);
    }

    public int getNetworkType() {
        return networkType;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public static class Builder {
        private String operatorName;
        private int networkType;
        private int asuStrength;

        public Builder setAsuStrength(int asuStrength) {
            this.asuStrength = asuStrength;
            return this;
        }

        public Builder setOperatorName(String operatorName) {
            this.operatorName = operatorName;
            return this;
        }

        public Builder setNetworkType(int networkType) {
            this.networkType = networkType;
            return this;
        }

        public SignalInfo build() {
            SignalInfo signalInfo = new SignalInfo();
            signalInfo.operatorName = operatorName;
            signalInfo.networkType = networkType;
            signalInfo.asuStrength = asuStrength;
            return signalInfo;
        }
    }

}
