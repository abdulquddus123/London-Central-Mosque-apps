package com.londoncentralmosque.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.londoncentralmosque.R;

/**
 * Created by Android Dev on 6/20/2017.
 */

public class EventHolder extends RecyclerView.ViewHolder {
    TextView dateTv,titleTv;

    public EventHolder(View itemView) {
        super(itemView);
        dateTv=(TextView)itemView.findViewById(R.id.date);
        titleTv=(TextView)itemView.findViewById(R.id.title);
    }
}
