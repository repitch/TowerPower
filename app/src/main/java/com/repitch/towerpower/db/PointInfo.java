package com.repitch.towerpower.db;

import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author i.s.pyavkin
 */
@DatabaseTable(tableName = "point_info")
public class PointInfo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private LatLng geoPosition;

    @DatabaseField(foreign = true, foreignAutoCreate = true)
    private Cell cell;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private SignalInfo signalInfo;

}
