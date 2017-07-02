package com.repitch.towerpower.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.repitch.towerpower.db.entity.CellInfo;

import java.sql.SQLException;

/**
 * Created by repitch on 02.07.17.
 */
public class CellDao extends BaseDaoImpl<CellInfo, Integer> {
    public CellDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, CellInfo.class);
    }
}
