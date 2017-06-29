package com.repitch.towerpower.connection;

import android.telephony.SignalStrength;

/**
 * @author i.s.pyavkin
 */
public class ConnectionUtils {

    public static int getStrengthAsRssi(String radioType, SignalStrength signalStrength) {
        String[] parts = signalStrength.toString().split(" ");
        int strengthAsu;
        switch (radioType) {
            case Connectivity.RADIO_GSM:
                strengthAsu = signalStrength.getGsmSignalStrength();
                return 2 * strengthAsu - 113;
            case Connectivity.RADIO_UMTS:
                strengthAsu = signalStrength.getGsmSignalStrength();
                return strengthAsu - 116;
            case Connectivity.RADIO_LTE:
                strengthAsu = Integer.parseInt(parts[8]);
                return strengthAsu - 141;
            case Connectivity.RADIO_CDMA:
                // todo
                return signalStrength.getCdmaDbm();
            default:
                return 99;
        }

    }

    public static int getStrengthAsAsu(String radioType, SignalStrength signalStrength) {
        String[] parts = signalStrength.toString().split(" ");
        switch (radioType) {
            case Connectivity.RADIO_GSM:
            case Connectivity.RADIO_UMTS:
                return signalStrength.getGsmSignalStrength();
            case Connectivity.RADIO_LTE:
                return Integer.parseInt(parts[8]);
            case Connectivity.RADIO_CDMA:
                // todo
                return signalStrength.getCdmaDbm();
            default:
                return -1;
        }

    }

}
