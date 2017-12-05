package com.londoncentralmosque;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.londoncentralmosque.Adapter.EventAdapter;
import com.londoncentralmosque.Model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    TextView textView,title,description,date,iccuk;
    String url = "http://iccukapp.org//Api_json_format/get_events";
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView myRecyclerView;
    private EventAdapter eAdapter;
    Button home;
    ImageButton twiter,facebook,instagram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        iccuk=(TextView)findViewById(R.id.iccuk);
        iccuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.iccuk.org/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecycleView);
        eAdapter = new EventAdapter(eventList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(eAdapter);

        twiter=(ImageButton)findViewById(R.id.twiterLink);
        twiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://twitter.com/iccukorg");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        facebook=(ImageButton)findViewById(R.id.facebookLink);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.facebook.com/iccuk.org");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });


        instagram=(ImageButton)findViewById(R.id.instragramLink);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.instagram.com/iccukorg/");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });



        getPoetsName();

    }

    public void getPoetsName(){
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray=response.getJSONArray("result");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                    //    String eventDate=jsonObject.getString("eventDate");
                        String eventda=jsonObject.getString("eventDate");


                      //  int date=eventDate.length();
                 //       Toast.makeText(EventActivity.this, String.valueOf(date), Toast.LENGTH_SHORT).show();
                    String eventyear = eventda.substring(0,4);
                        String eventmonth=eventda.substring(5,7);
                     //  int eventmonth = Integer.valueOf(eventda.substring(5,7));
                      //  Toast.makeText(EventActivity.this, String.valueOf(eventmonth), Toast.LENGTH_SHORT).show();
                        String eventday=eventda.substring(8,10);
                        String eventDate=(eventday+"-"+eventmonth+"-"+eventyear);







                        String eventTitle=jsonObject.getString("eventTitle");
                        String eventDescription=jsonObject.getString("eventDescr");
                        eventDescription = eventDescription.replace("\n", "").replace("\r", "").replace("&nbsp", "").replace("&#39;", "'");
                        //  Toast.makeText(EventActivity.this, eventTitle, Toast.LENGTH_SHORT).show();

                        Event event=new Event(eventDate,eventTitle,eventDescription);
                        eventList.add(event);

                        eAdapter.notifyDataSetChanged();

//
                        //              date.setText(date.getText().toString()+"\n"+eventDate);
                        //                   title.setText(title.getText().toString()+"\n"+eventTitle);


                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError){
                    Toast.makeText(EventActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }


    public void onClick(View v) {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        int id=v.getId();


        Intent myIntent = new Intent(EventActivity.this,
                SinglePageEvent.class);
        startActivity(myIntent);
    }

    public void about(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void wellcome(View view) {
        Intent intent = new Intent(this, WellcomeActivity.class);
        startActivity(intent);
    }


    public void eventLink(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public void departmentLink(View view) {
        Intent intent = new Intent(this, DepartmentActivity.class);
        startActivity(intent);
    }

    public void newsLink(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }



    public void contact(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
        //  Toast.makeText(this, "testing", Toast.LENGTH_SHORT).show();
    }

    public void donate(View view) {
        Intent intent = new Intent(this, DonationActivity.class);
        startActivity(intent);
    }



}

