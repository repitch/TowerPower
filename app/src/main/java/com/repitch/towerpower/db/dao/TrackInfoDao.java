package com.repitch.towerpower.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.repitch.towerpower.db.entity.TrackInfo;

import java.sql.SQLException;

/**
 * Created by repitch on 02.07.17.
 */
public class TrackInfoDao extends BaseDaoImpl<TrackInfo, Integer> {
    public TrackInfoDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, TrackInfo.class);
    }
}
