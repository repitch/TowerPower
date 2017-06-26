package com.repitch.towerpower.api;

import java.util.Collections;

/**
 * Created by repitch on 25.06.17.
 */
public class LocationRequestManager {

    public static final String API_TOKEN = "90b1f57051b42b";

    public static LocationRequest generateMock() {
        return generateRequest("gsm", 310, 410, 7033, 17811);
    }

    public static LocationRequest generateRequest(String radio, int mcc, int mnc, int lac, int cid) {
        LocationRequest request = new LocationRequest();
        request.setToken(API_TOKEN);
        request.setRadio(radio);
        request.setMcc(mcc);
        request.setMnc(mnc);
        request.setCells(Collections.singletonList(new Cell(lac, cid)));
        return request;
    }

}
