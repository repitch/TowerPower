package com.repitch.towerpower.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.repitch.towerpower.R;
import com.repitch.towerpower.connection.Connectivity;
import com.repitch.towerpower.db.entity.CellInfo;
import com.repitch.towerpower.db.entity.Position;
import com.repitch.towerpower.db.entity.SignalInfo;
import com.repitch.towerpower.db.entity.TrackInfo;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by repitch on 02.07.17.
 */
public class TrackHistoryAdapter extends RecyclerView.Adapter<TrackHistoryAdapter.ViewHolder> {

    private List<TrackInfo> trackInfoList;

    public TrackHistoryAdapter(List<TrackInfo> trackInfoList) {
        this.trackInfoList = trackInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrackInfo info = trackInfoList.get(position);
        CellInfo cellInfo = info.getCellInfo();
        holder.txtOperatorCode.setText(String.valueOf(cellInfo.getOperatorCode()));
        holder.txtCid.setText(String.format("cid: %d", cellInfo.getCid()));
        holder.txtLac.setText(String.format("lac: %d", cellInfo.getLac()));

        Position geoPosition = info.getGeoPosition();
        holder.txtLat.setText(String.format("%.2f",geoPosition.latitude));
        holder.txtLon.setText(String.format("%.2f",geoPosition.longitude));

        SignalInfo signalInfo = info.getSignalInfo();
        holder.txtAsu.setText(String.valueOf(signalInfo.getAsuStrength()));
        holder.txtNetwork.setText(String.format("%s (%s)",
                Connectivity.networkTypeAsString(signalInfo.getNetworkType()),
                Connectivity.getNetworkClassAsString(signalInfo.getNetworkType())));

        holder.txtDate.setText(new SimpleDateFormat("dd MMM").format(info.getTime().toDate()));
        holder.txtTime.setText(new SimpleDateFormat("HH:mm").format(info.getTime().toDate()));
    }

    @Override
    public int getItemCount() {
        return trackInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOperatorCode;
        TextView txtLac;
        TextView txtCid;
        View wrapLocation;
        TextView txtLat;
        TextView txtLon;
        TextView txtAsu;
        TextView txtNetwork;
        TextView txtDate;
        TextView txtTime;

        public ViewHolder(View itemView) {
            super(itemView);
            txtOperatorCode = (TextView) itemView.findViewById(R.id.txt_operator_code);
            txtLac = (TextView) itemView.findViewById(R.id.txt_lac);
            txtCid = (TextView) itemView.findViewById(R.id.txt_cid);
            wrapLocation = itemView.findViewById(R.id.wrap_location);
            txtLat = (TextView) itemView.findViewById(R.id.txt_lat);
            txtLon = (TextView) itemView.findViewById(R.id.txt_lon);
            txtAsu = (TextView) itemView.findViewById(R.id.txt_asu);
            txtNetwork = (TextView) itemView.findViewById(R.id.txt_network);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date);
            txtTime = (TextView) itemView.findViewById(R.id.txt_time);
        }
    }
}
