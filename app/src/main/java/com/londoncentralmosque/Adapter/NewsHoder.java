package com.londoncentralmosque.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.londoncentralmosque.R;

/**
 * Created by Android Dev on 6/20/2017.
 */

public class NewsHoder extends RecyclerView.ViewHolder {
    TextView titleTv;
    ImageView imageView;
    ImageView imageViewTv;
    public NewsHoder(View itemView) {
        super(itemView);

        titleTv=(TextView)itemView.findViewById(R.id.title);
        imageView=(ImageView)itemView.findViewById(R.id.imageView);
        //  imageViewTv=(ImageView) itemView.findViewById(R.id.imageView);
    }
}
