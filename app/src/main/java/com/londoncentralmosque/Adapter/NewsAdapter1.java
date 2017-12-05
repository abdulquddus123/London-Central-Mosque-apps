package com.londoncentralmosque.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.londoncentralmosque.AppController;
import com.londoncentralmosque.Model.News1;
import com.londoncentralmosque.R;
import com.londoncentralmosque.SinglePageNews;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Android Dev on 6/20/2017.
 */

public class NewsAdapter1  extends RecyclerView.Adapter<NewsAdapter1.MyViewHolder>{

    private List<News1> newsList1;
    Context context;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    public NewsAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainnews_model, parent, false);

        return new NewsAdapter1.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final News1 news1 = newsList1.get(position);
        holder.title.setText(news1.getNewsTitle());
        //  Picasso.with(context).load(news.getNewsImage()).resize(120, 60).into(holder.imageView);
        Picasso.with(context).load(news1.getNewsImage()).into(holder.imageView);
    }

    public NewsAdapter1(Context context,List<News1> newsList1) {
        this.context=context;
        this.newsList1 = newsList1;

    }




    @Override
    public int getItemCount() {
        return newsList1.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            imageView=(ImageView)view.findViewById(R.id.imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(v.getContext(),SinglePageNews.class);
                    i.putExtra("Image",newsList1.get(getAdapterPosition()).getNewsImage());
                    i.putExtra("Title",newsList1.get(getAdapterPosition()).getNewsTitle());
                    i.putExtra("Description",newsList1.get(getAdapterPosition()).getNewsDescription());

                    v.getContext().startActivity(i);

                }
            });
        }
    }
}