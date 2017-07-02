package com.repitch.towerpower.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.repitch.towerpower.R;
import com.repitch.towerpower.db.TrackInfoRepository;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by repitch on 02.07.17.
 */
public class TrackHistoryActivity extends AppCompatActivity {

    private TrackInfoRepository trackInfoRepository;

    private RecyclerView recyclerView;
    private TrackHistoryAdapter adapter;
    private View emptyView;

    public static Intent createIntent(Context context) {
        return new Intent(context, TrackHistoryActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_history);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyView = findViewById(R.id.empty_view);

        trackInfoRepository = new TrackInfoRepository();
        trackInfoRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trackInfoList -> {
                    if (trackInfoList.isEmpty()) {
                        emptyView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter = new TrackHistoryAdapter(trackInfoList);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
}
