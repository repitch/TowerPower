package com.repitch.towerpower.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.repitch.towerpower.db.dao.CellDao;
import com.repitch.towerpower.db.dao.TrackInfoDao;
import com.repitch.towerpower.db.entity.CellInfo;
import com.repitch.towerpower.db.entity.TrackInfo;

import java.sql.SQLException;

/**
 * Created by repitch on 02.07.17.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "towerpower.db";
    private static final int DB_VERSION = 2;

    public static  DBHelper instance;

    private CellDao cellDao;
    private TrackInfoDao trackInfoDao;

    private Class[] models = {
            TrackInfo.class,
            CellInfo.class
    };

    public DBHelper(@NonNull Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized  DBHelper getInstance() {
        if (instance == null) {
            throw new IllegalStateException("You need to init(Context) DBHelper in App class");
        }
        return instance;
    }

    public static synchronized void releaseHelper() {
        OpenHelperManager.releaseHelper();
        instance = null;
    }

    public static synchronized  void init(@NonNull Context context) {
        instance = OpenHelperManager.getHelper(context, DBHelper.class);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class c : models) {
                TableUtils.createTableIfNotExists(connectionSource, c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            for (Class c : models) {
                TableUtils.dropTable(connectionSource, c, true);
            }
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CellDao getCellDao() throws SQLException {
        if (cellDao == null) {
            cellDao = new CellDao(getConnectionSource());
        }
        return cellDao;
    }

    public TrackInfoDao getTrackInfoDao() throws SQLException {
        if (trackInfoDao == null) {
            trackInfoDao = new TrackInfoDao(getConnectionSource());
        }
        return trackInfoDao;
    }


}
