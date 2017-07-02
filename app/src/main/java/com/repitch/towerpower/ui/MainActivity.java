package com.repitch.towerpower.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.repitch.towerpower.Property;
import com.repitch.towerpower.R;
import com.repitch.towerpower.api.LocationRequest;
import com.repitch.towerpower.api.LocationRequestManager;
import com.repitch.towerpower.api.RetrofitManager;
import com.repitch.towerpower.api.cell.Cell;
import com.repitch.towerpower.connection.ConnectionUtils;
import com.repitch.towerpower.connection.Connectivity;
import com.repitch.towerpower.db.TrackInfoRepository;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SignalTracker.TrackListener {

    private static final int REQUEST_CODE_ALL_PERMISSIONS = 1;

    private ProgressDialog progressDialog;
    private TextView txtLocationInfo;
    private TextView txtSignal;
    private TextView txtLat;
    private TextView txtLon;

    private Button retryBtn;
    private Button btnTracksHistory;
    private Button openMap;
    private Button trackBtn;
    private LatLng cellPosition;
    private List<NeighboringCellInfo> neighboringCellInfos;
    private int accuracy;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private SignalTracker signalTracker;

    private int signalAsu;
    private int signalDbm;
    private List<Property> properties;
    private PhoneStateListener phoneStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signalTracker = new SignalTracker(this, new TrackInfoRepository(), this);

        setContentView(R.layout.activity_main);
        btnTracksHistory = (Button) findViewById(R.id.btn_track_history);
        btnTracksHistory.setOnClickListener(v -> startActivity(TrackHistoryActivity.createIntent(this)));
        trackBtn = (Button) findViewById(R.id.btn_track);
        trackBtn.setOnClickListener(v -> signalTracker.track(signalAsu));
        txtLocationInfo = (TextView) findViewById(R.id.txt_location_info);
        txtSignal = (TextView) findViewById(R.id.txt_signal);
        txtLat = (TextView) findViewById(R.id.txt_lat);
        txtLon = (TextView) findViewById(R.id.txt_lon);
        retryBtn = (Button) findViewById(R.id.btn_retry);
        retryBtn.setOnClickListener(v -> retry());
        openMap = (Button) findViewById(R.id.btn_open_map);
        openMap.setOnClickListener(v -> startActivity(MapActivity.createIntent(this, cellPosition, accuracy)));
        findViewById(R.id.btn_get_location).setOnClickListener(v -> updateCurrentLocation());
        retry();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        retry();
    }

    private void retry() {
        List<String> permissions = new ArrayList<>();
        String[] neededPermissions = {
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET
        };
        for (String permission : neededPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    // todo shit here
                    permissions.add(permission);
                } else {
                    permissions.add(permission);
                }
            }
        }
        if (permissions.isEmpty()) {
            getNWInfo(this);
        } else {
            String[] perm = new String[permissions.size()];
            ActivityCompat.requestPermissions(this, permissions.toArray(perm), REQUEST_CODE_ALL_PERMISSIONS);
        }
    }


    private void getNWInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        if (phoneStateListener == null) {
            phoneStateListener = new PhoneStateListener() {
                @Override
                public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                    super.onSignalStrengthsChanged(signalStrength);

                    TelephonyManager telephonyManager = (TelephonyManager) context
                            .getSystemService(Context.TELEPHONY_SERVICE);
                    int networkType = telephonyManager.getNetworkType();
                    String radioType = Connectivity.getRadioType(networkType);

                    int asu = ConnectionUtils.getStrengthAsAsu(radioType, signalStrength);
                    int dbm = ConnectionUtils.getStrengthAsRssi(radioType, signalStrength);
                    setSignalStrength(asu, dbm);
                    updateCurrentLocation();
                }
            };
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        }

        Connectivity connectivity = new Connectivity(this);
        String networkOperator = telephonyManager.getNetworkOperator();
        int mcc = 0, mnc = 0;
        if (networkOperator != null && networkOperator.length() > 3) {
            mcc = Integer.parseInt(networkOperator.substring(0, 3));
            mnc = Integer.parseInt(networkOperator.substring(3));
        }

        String countryISO = telephonyManager.getSimCountryIso();
        String operatorName = telephonyManager.getSimOperatorName();
        String simOperator = telephonyManager.getSimOperator();
        int simState = telephonyManager.getSimState();

        neighboringCellInfos = telephonyManager.getNeighboringCellInfo();

        // Getting connected network iso country code
        String networkCountry = telephonyManager.getNetworkCountryIso();
        // Getting the connected network operator ID
        String networkOperatorId = telephonyManager.getNetworkOperator();
        // Getting the connected network operator name
        String networkName = telephonyManager.getNetworkOperatorName();

        int networkType = telephonyManager.getNetworkType();

        String network = connectivity.getNetworkTypeName();

        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

        int new_cid = 0;
        int new_lac = 0;
        if (cellLocation != null) {
            new_cid = cellLocation.getCid() & 0xffff;
            new_lac = cellLocation.getLac() & 0xffff;
        }

        String radioType = Connectivity.getRadioType(networkType);

        TextView textView = (TextView) findViewById(R.id.text_view);
        properties = new ArrayList<>();
        properties.add(new Property("network", network));
        properties.add(new Property("countryISO", countryISO));
        properties.add(new Property("operatorName", operatorName));
        properties.add(new Property("simState", String.valueOf(simState)));
        properties.add(new Property("simOperator", simOperator));
        properties.add(new Property("MCC", String.valueOf(mcc)));
        properties.add(new Property("MNC", String.valueOf(mnc)));
        properties.add(new Property("network country", networkCountry));
        properties.add(new Property("network OperatorId", networkOperatorId));
        properties.add(new Property("network name", networkName));
        properties.add(new Property("network type", Connectivity.networkTypeAsString(networkType)));
        properties.add(new Property("CID", String.valueOf(new_cid)));
        properties.add(new Property("LAC", String.valueOf(new_lac)));
        properties.add(new Property("network class", Connectivity.getNetworkClassAsString(networkType)));
        properties.add(new Property("radio type", radioType));
        properties.add(new Property("neighbors", generateShortNeighboursText(neighboringCellInfos)));

        textView.setText(Property.propertiesToString(properties));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Подождите...");
        progressDialog.show();
        openMap.setEnabled(false);

        List<Cell> cells = new ArrayList<>();
        Cell baseCell = Cell.from(new_lac, new_cid, radioType);
        if (signalAsu > 0) {
            baseCell.setAsu(signalAsu);
        }
        cells.add(baseCell);
        for (NeighboringCellInfo cellInfo : neighboringCellInfos) {
            String radio = Connectivity.getRadioType(cellInfo.getNetworkType());
            cells.add(Cell.from(cellInfo.getLac(), cellInfo.getCid(), radio));
        }
        LocationRequest request = LocationRequestManager.generateRequest(radioType, mcc, mnc, cells);
        RetrofitManager.getInterface()
                .getLocationInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(progressDialog::hide)
                .subscribe(locationInfo -> {
                    if (locationInfo.getLat() != null && locationInfo.getLon() != null) {
                        cellPosition = new LatLng(locationInfo.getLat(), locationInfo.getLon());
                        accuracy = locationInfo.getAccuracy();
                        openMap.setEnabled(true);
                    }
                    txtLocationInfo.setText(locationInfo.toString());
                    txtLocationInfo.setTextColor(ContextCompat.getColor(MainActivity.this, locationInfo.isSuccessful() ? R.color.success : R.color.error));
                });
    }

    private String generateShortNeighboursText(List<NeighboringCellInfo> neighboringCellInfos) {
        if (neighboringCellInfos.isEmpty()) {
            return "nothing";
        }
        StringBuilder sb = new StringBuilder();
        for (NeighboringCellInfo info : neighboringCellInfos) {
            sb.append(String.format("[cid: %d lac: %d] ", info.getCid(), info.getLac()));
        }
        return sb.toString();
    }

    private void setSignalStrength(int asu, int dbm) {
        signalAsu = asu;
        signalDbm = dbm;
        txtSignal.setText(String.format("%ddb %dasu", dbm, asu));
    }

    public void updateCurrentLocation() {
        if (fusedLocationProviderClient == null) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, this::setCurrentLocation);
    }

    private void setCurrentLocation(Location location) {
        txtLat.setText(String.format("lat: %.4f", location.getLatitude()));
        txtLon.setText(String.format("lon: %.4f", location.getLongitude()));
    }

    @Override
    public void onTrackSuccess(int tracksCount) {
        ((TextView) findViewById(R.id.txt_tracks_count)).setText("Tracks: " + tracksCount);
    }
}
