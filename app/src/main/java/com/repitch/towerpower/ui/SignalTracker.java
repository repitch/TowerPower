package com.repitch.towerpower.ui;

import android.content.Context;
import android.location.Location;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.repitch.towerpower.db.TrackInfoRepository;
import com.repitch.towerpower.db.entity.CellInfo;
import com.repitch.towerpower.db.entity.Position;
import com.repitch.towerpower.db.entity.SignalInfo;
import com.repitch.towerpower.db.entity.TrackInfo;

import org.joda.time.DateTime;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by repitch on 02.07.17.
 */
public class SignalTracker {

    public interface TrackListener {
        void onTrackSuccess(int tracksCount);
    }

    private final Context context;
    private final TrackInfoRepository trackInfoRepository;
    private final FusedLocationProviderClient fusedLocationProviderClient;
    private final TrackListener trackListener;

    private int tracksCount = 0;

    public SignalTracker(Context context, TrackInfoRepository trackInfoRepository, TrackListener trackListener) {
        this.context = context;
        this.trackInfoRepository = trackInfoRepository;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        this.trackListener = trackListener;
    }

    public void track(int asuStrength) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        // 1 signal info
        String operatorName = telephonyManager.getNetworkOperatorName();
        int networkType = telephonyManager.getNetworkType();

        final SignalInfo signalInfo = new SignalInfo.Builder()
                .setOperatorName(operatorName)
                .setNetworkType(networkType)
                .setAsuStrength(asuStrength)
                .build();

        // 2 cell info
        String networkOperator = telephonyManager.getNetworkOperator();
        int mcc = 0, mnc = 0;
        if (networkOperator != null && networkOperator.length() > 3) {
            mcc = Integer.parseInt(networkOperator.substring(0, 3));
            mnc = Integer.parseInt(networkOperator.substring(3));
        }
        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
        int lac = cellLocation.getLac() & 0xffff;
        int cid = cellLocation.getCid() & 0xffff;

        final CellInfo cellInfo = new CellInfo(mcc, mnc, lac, cid);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            TrackInfo trackInfo = new TrackInfo(
                    new Position(location.getLatitude(), location.getLongitude()),
                    cellInfo,
                    signalInfo,
                    DateTime.now());
            track(trackInfo);
        });
    }

    private void track(TrackInfo trackInfo) {
        trackInfoRepository.create(trackInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> trackListener.onTrackSuccess(++tracksCount));
    }
}
