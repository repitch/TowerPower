package com.repitch.towerpower;

import android.app.Application;

import com.repitch.towerpower.db.DBHelper;

/**
 * Created by repitch on 02.07.17.
 */
public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DBHelper.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        DBHelper.releaseHelper();
        super.onTerminate();
    }
}
