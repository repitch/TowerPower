package com.repitch.towerpower.db.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

/**
 * @author i.s.pyavkin
 */
@DatabaseTable(tableName = "track_info")
public class TrackInfo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Position geoPosition;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private CellInfo cellInfo;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private SignalInfo signalInfo;

    @DatabaseField
    private DateTime time;

    public TrackInfo() {
        // empty
    }

    public TrackInfo(Position geoPosition, CellInfo cellInfo, SignalInfo signalInfo, DateTime dateTime) {
        this.geoPosition = geoPosition;
        this.cellInfo = cellInfo;
        this.signalInfo = signalInfo;
        this.time = dateTime;
    }

    public Position getGeoPosition() {
        return geoPosition;
    }

    public CellInfo getCellInfo() {
        return cellInfo;
    }

    public SignalInfo getSignalInfo() {
        return signalInfo;
    }

    public DateTime getTime() {
        return time;
    }
}
