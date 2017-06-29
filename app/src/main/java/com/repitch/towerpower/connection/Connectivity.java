package com.repitch.towerpower.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Check device's network connectivity and speed
 *
 * @author emil http://stackoverflow.com/users/220710/emil
 */
public class Connectivity {

    public static final int NETWORK_CLASS_UNKNOWN = 0;
    public static final int NETWORK_CLASS_2_G = 1;
    public static final int NETWORK_CLASS_3_G = 2;
    public static final int NETWORK_CLASS_4_G = 3;

    public static final String RADIO_GSM = "gsm";
    public static final String RADIO_CDMA = "cdma";
    public static final String RADIO_UMTS = "umts";
    public static final String RADIO_LTE = "lte";
    public static final String RADIO_UNKNOWN = "unknown";

    private final ConnectivityManager cm;

    public Connectivity(Context context) {
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public NetworkInfo getNetworkInfo() {
        return cm.getActiveNetworkInfo();
    }

    public boolean isConnected() {
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected());
    }

    public boolean isConnectedWifi() {
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public boolean isConnectedMobile() {
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    public String getNetworkTypeName() {
        return getNetworkInfo().getTypeName();
    }

    public static String getRadioType(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GSM:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return RADIO_GSM;
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA: // don't know
                return RADIO_CDMA;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return RADIO_UMTS;
            case TelephonyManager.NETWORK_TYPE_LTE:
            case 19: //TelephonyManager.NETWORK_TYPE_LTE_CA:
                return RADIO_LTE;
            case TelephonyManager.NETWORK_TYPE_IWLAN:
            case TelephonyManager.NETWORK_TYPE_IDEN:
            default:
                return RADIO_UNKNOWN;
        }
    }

    public static String getNetworkClassAsString(int networkType) {
        switch (getNetworkClass(networkType)) {
            case NETWORK_CLASS_2_G:
                return "2G";
            case NETWORK_CLASS_3_G:
                return "3G";
            case NETWORK_CLASS_4_G:
                return "4G";
            default:
                return "Unknown";
        }
    }

    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_GSM:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                return NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
            case TelephonyManager.NETWORK_TYPE_IWLAN:
            case 19: //TelephonyManager.NETWORK_TYPE_LTE_CA:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }

    public static String networkTypeAsString(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return "UNKNOWN";
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "EVDO_0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "EVDO_A";
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA";
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "IDEN";
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return "EVDO_B";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "LTE";
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return "EHRPD";
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "HSPAP";
            case TelephonyManager.NETWORK_TYPE_GSM:
                return "GSM";
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                return "TD_SCDMA";
            case TelephonyManager.NETWORK_TYPE_IWLAN:
                return "IWLAN";
            case 19:
                return "LTE_CA";
            default:
                return "";
        }
    }

}
