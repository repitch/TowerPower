package com.repitch.towerpower.api;

import com.repitch.towerpower.api.cell.Cell;
import com.repitch.towerpower.api.cell.GsmCell;

import java.util.Collections;
import java.util.List;

/**
 * Created by repitch on 25.06.17.
 */
public class LocationRequestManager {

    public static final String API_TOKEN = "90b1f57051b42b";

    public static LocationRequest generateMock() {
        return generateRequest("gsm", 310, 410, Collections.singletonList(new GsmCell(7033, 17811)));
    }

    public static LocationRequest generateRequest(
            String radio,
            int mcc,
            int mnc,
            List<Cell> cells) {
        LocationRequest request = new LocationRequest();
        request.setToken(API_TOKEN);
        request.setRadio(radio);
        request.setMcc(mcc);
        request.setMnc(mnc);
        request.setCells(cells);
        return request;
    }

}
