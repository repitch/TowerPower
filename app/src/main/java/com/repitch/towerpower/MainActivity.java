package com.repitch.towerpower;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Button;
import android.widget.TextView;

import com.repitch.towerpower.api.Connectivity;
import com.repitch.towerpower.api.LocationRequest;
import com.repitch.towerpower.api.LocationRequestManager;
import com.repitch.towerpower.api.RetrofitManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private ProgressDialog progressDialog;
    private TextView txtLocationInfo;
    private Button retryBtn;
    private Button openMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLocationInfo = (TextView) findViewById(R.id.txt_location_info);
        retryBtn = (Button) findViewById(R.id.btn_retry);
        retryBtn.setOnClickListener(v -> retry());
        openMap = (Button) findViewById(R.id.btn_open_map);
        openMap.setOnClickListener(v -> startActivity(MapActivity.createIntent(this)));

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            getNWInfo(this);
        }
    }

    private void retry() {
        getNWInfo(this);
    }

    private void getNWInfo(Context context) {
        /**
         * <uses-permission android:name="android.permission.READ_PHONE_STATE"
         * /> <uses-permission
         * android:name="android.permission.ACCESS_NETWORK_STATE"/>
         */

        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = telephonyManager.getNetworkOperator();
        int mcc = 0, mnc = 0;
        if (networkOperator != null && networkOperator.length() > 3) {
            mcc = Integer.parseInt(networkOperator.substring(0, 3));
            mnc = Integer.parseInt(networkOperator.substring(3));
        }

        String SimNumber = telephonyManager.getLine1Number();

        String SimSerialNumber = telephonyManager.getSimSerialNumber();
        String countryISO = telephonyManager.getSimCountryIso();
        String operatorName = telephonyManager.getSimOperatorName();
        String operator = telephonyManager.getSimOperator();
        int simState = telephonyManager.getSimState();

        String voicemailNumer = telephonyManager.getVoiceMailNumber();
        String voicemailAlphaTag = telephonyManager.getVoiceMailAlphaTag();

        // Getting connected network iso country code
        String networkCountry = telephonyManager.getNetworkCountryIso();
        // Getting the connected network operator ID
        String networkOperatorId = telephonyManager.getNetworkOperator();
        // Getting the connected network operator name
        String networkName = telephonyManager.getNetworkOperatorName();

        int networkType = telephonyManager.getNetworkType();

        String network = "";
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            if (cm.getActiveNetworkInfo().getTypeName().equals("MOBILE"))
                network = "Cell Network/3G";
            else if (cm.getActiveNetworkInfo().getTypeName().equals("WIFI"))
                network = "WiFi";
            else
                network = "N/A";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

        int new_cid = cellLocation.getCid() & 0xffff;
        int new_lac = cellLocation.getLac() & 0xffff;

        String cidLacString = String.format("CID: %d\nLAC: %d\n", new_cid, new_lac);

        String radioType = Connectivity.getRadioType(networkType);

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText("network :" + network +

                "\n" + "countryISO : " + countryISO + "\n" + "operatorName : "
                + operatorName + "\n" + "operator :      " + operator + "\n"
                + "simState :" + simState + "\n" + "Sim Serial Number : "
                + SimSerialNumber + "\n" + "Sim Number : " + SimNumber + "\n"
                + "Voice Mail Numer" + voicemailNumer + "\n"
                + "Voice Mail Alpha Tag" + voicemailAlphaTag + "\n"
                + "Sim State" + simState + "\n" + "Mobile Country Code MCC : "
                + mcc + "\n" + "Mobile Network Code MNC : " + mnc + "\n"
                + "Network Country : " + networkCountry + "\n"
                + "Network OperatorId : " + networkOperatorId + "\n"
                + "Network Name : " + networkName + "\n"
                + "Network Type : " + Connectivity.networkTypeAsString(networkType) + "\n"
                + cidLacString + "\n"
                + "Network class: " + Connectivity.getNetworkClassAsString(networkType)
                + "Radio type: " + radioType
        );

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
//        LocationRequest request = LocationRequestManager.generateMock();
        LocationRequest request = LocationRequestManager.generateRequest(radioType, mcc, mnc, new_lac, new_cid);
        RetrofitManager.getInterface()
                .getLocationInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(progressDialog::hide)
                .subscribe(locationInfo -> {
                    txtLocationInfo.setText(locationInfo.toString());
                });


    }
}
