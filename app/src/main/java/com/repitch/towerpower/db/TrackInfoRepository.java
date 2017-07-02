package com.repitch.towerpower.db;

import com.repitch.towerpower.db.dao.TrackInfoDao;
import com.repitch.towerpower.db.entity.TrackInfo;

import java.sql.SQLException;
import java.util.List;

import rx.Completable;
import rx.Single;

/**
 * Created by repitch on 02.07.17.
 */
public class TrackInfoRepository {

    private TrackInfoDao dao;

    public TrackInfoRepository() {
        try {
            dao = DBHelper.getInstance().getTrackInfoDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Completable create(TrackInfo trackInfo) {
        return Completable.fromCallable(() -> dao.create(trackInfo));
    }

    public Single<List<TrackInfo>> getAll() {
        return Single.fromCallable(() -> dao.queryForAll());
    }
}
