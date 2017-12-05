package com.londoncentralmosque;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.londoncentralmosque.Adapter.NewsAdapter1;
import com.londoncentralmosque.Alarm.AlarmReceiver;
import com.londoncentralmosque.Model.News1;
import com.londoncentralmosque.Model.Preference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton about_us, contact, department, donate, facebook, news, twiter, wellcome;
    Button home;
    Switch switch_button;
    Button event;
    static String fozarTime, sunriseTime, zohorTime, asosTime, magribTime, esaTime;
    TextView textView, title, description, date, timeTv;
    String url = "http://iccukapp.org/Api_json_format/rest_csv";
    String url1="http://iccukapp.org/Api_json_format";
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;
    private ImageView image;
    private List<News1> newsList1 = new ArrayList<>();
    private RecyclerView myRecyclerView;
    private NewsAdapter1 nAdapter1;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;
    Preference preference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preference=new Preference(this);

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecycleView);
        nAdapter1 = new NewsAdapter1(this,newsList1);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(nAdapter1);

        wellcome = (ImageButton) findViewById(R.id.wellcome);
        about_us = (ImageButton) findViewById(R.id.about_us);
        donate = (ImageButton) findViewById(R.id.donate);
        contact = (ImageButton) findViewById(R.id.contact);
        timeTv = (TextView) findViewById(R.id.timeTv);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);




        getPrayerTime();
        getMainNews();
        switch_button = (Switch)findViewById(R.id.simpleSwitch);
        int data = preference.getUserData();
        if(data==1){
            switch_button.setChecked(true);
              getAlarmTime();
        }
        else {
            switch_button.setChecked(false);
        }
        switch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),
                            "Alarm is on", Toast.LENGTH_LONG).show();
                    preference.saveUserData(1);
                    getAlarmTime();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Alarm is off", Toast.LENGTH_LONG).show();
                    preference.saveUserData(0);
                    Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                    //intent.putExtra("row_id", rowId);
                    final int alarmId = (int) System.currentTimeMillis();
                    PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 1, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent sender1 = PendingIntent.getBroadcast(MainActivity.this, 2, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent sender2 = PendingIntent.getBroadcast(MainActivity.this, 3, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent sender3 = PendingIntent.getBroadcast(MainActivity.this, 4, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent sender4 = PendingIntent.getBroadcast(MainActivity.this, 5, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    am.cancel(sender);
                    am.cancel(sender1);
                    am.cancel(sender2);
                    am.cancel(sender3);
                    am.cancel(sender4);
                }
            }
        });

    }
    public void getAlarmTime() {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("res");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    fozarTime = jsonObject.getString("fazar");
                    zohorTime = jsonObject.getString("zohor");
                    asosTime = jsonObject.getString("asar");
                    magribTime = jsonObject.getString("magrib");
                    esaTime = jsonObject.getString("esa");

                    int len=zohorTime.length();
                    int fozorlen=fozarTime.length();
                    int fozorHour;
                    int fozorMin;
                    if(fozorlen==8){
                        fozorHour = Integer.valueOf(fozarTime.substring(0,2));
                        fozorMin = Integer.valueOf(fozarTime.substring(3,5));
                        setAlarm(fozorHour,fozorMin,1);

                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        fozorHour = Integer.valueOf(fozarTime.substring(0,1));
                        fozorMin = Integer.valueOf(fozarTime.substring(2,4));
                      //  fozorHour+=12;
                        setAlarm(fozorHour,fozorMin,1);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }
                    int zohorHour;
                    int zohorMin;
                    if(len==11){
                        zohorHour = Integer.valueOf(zohorTime.substring(0,2));
                        zohorMin = Integer.valueOf(zohorTime.substring(3,5));
                        setAlarm(zohorHour,zohorMin,2);
                        //  setAlarm(16,55,2);
                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        zohorHour = Integer.valueOf(zohorTime.substring(0,1));
                        zohorMin = Integer.valueOf(zohorTime.substring(2,4));
                        zohorHour+=12;
                        setAlarm(zohorHour,zohorMin,2);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }

                    int asorlen=asosTime.length();
                    int asorHour;
                    int asorMin;
                    if(asorlen==11){
                        asorHour = Integer.valueOf(asosTime.substring(0,2));
                        asorMin = Integer.valueOf(asosTime.substring(3,5));
                        setAlarm(asorHour,asorMin,3);


                    }
                    else{
                        asorHour = Integer.valueOf(asosTime.substring(0,1));
                        asorMin = Integer.valueOf(asosTime.substring(2,4));
                        asorHour+=12;
                        setAlarm(asorHour,asorMin,3);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }


                    int magriblen=magribTime.length();
                    int magribHour;
                    int magribMin;
                    if(magriblen==8){
                        magribHour = Integer.valueOf(magribTime.substring(0,2));
                        magribMin = Integer.valueOf(magribTime.substring(3,5));
                        setAlarm(magribHour,magribMin,4);
                    }
                    else{
                        magribHour = Integer.valueOf(magribTime.substring(0,1));
                        magribMin = Integer.valueOf(magribTime.substring(2,4));
                        magribHour+=12;
                        setAlarm(magribHour,magribMin,4);
                    }


                    int esalen=esaTime.length();
                    int eshaHour;
                    int eshaMin;
                    if(esalen==11){
                        eshaHour = Integer.valueOf(esaTime.substring(0,2));
                        eshaMin = Integer.valueOf(esaTime.substring(3,5));
                        eshaHour+=12;
                        setAlarm(eshaHour,eshaMin,5);

                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        eshaHour = Integer.valueOf(esaTime.substring(0,1));
                        eshaMin = Integer.valueOf(esaTime.substring(2,4));
                        eshaHour+=12;
                        setAlarm(eshaHour,eshaMin,5);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    Toast.makeText(MainActivity.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }



    public void getMainNews(){
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray=response.getJSONArray("result");
                    for (int i=0;i<3;i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        //  String eventDate=jsonObject.getString("eventDate");
                        String newsTitle=jsonObject.getString("title");
                        String newsImage=jsonObject.getString("post_iamge");
                    //    http://iccukapp.org/Api_json_format
                        String Image="http://iccukapp.org/assets/admin/images/"+newsImage;
                        String newsDescription=jsonObject.getString("description");
                        newsDescription = newsDescription.replace("\n", "").replace("\r", "").replace("&nbsp", "");
                        String newsDate=jsonObject.getString("post_created");
                        //    Toast.makeText(MainActivity.this, newsDate, Toast.LENGTH_SHORT).show();
                        //  String eventDescription=jsonObject.getString("eventDescr");
                        //  Toast.makeText(EventActivity.this, eventTitle, Toast.LENGTH_SHORT).show();

                        News1 news1=new News1(newsTitle,Image,newsDescription,newsDate);
                        newsList1.add(news1);

                        nAdapter1.notifyDataSetChanged();

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
                    Toast.makeText(MainActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    //   Prayer time from API
    public void getPrayerTime() {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("res");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    fozarTime = jsonObject.getString("fazar");
                    sunriseTime = jsonObject.getString("sunrise");
                    zohorTime = jsonObject.getString("zohor");
                    asosTime = jsonObject.getString("asar");
                    magribTime = jsonObject.getString("magrib");
                    esaTime = jsonObject.getString("esa");



                    int len=zohorTime.length();
                    int fozorlen=fozarTime.length();

                    int fozorHour;
                    int fozorMin;
                    if(fozorlen==8){
                        fozorHour = Integer.valueOf(fozarTime.substring(0,2));
                        fozorMin = Integer.valueOf(fozarTime.substring(3,5));
                        //     setAlarm(fozorHour,fozorMin,1);

                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        fozorHour = Integer.valueOf(fozarTime.substring(0,1));
                        fozorMin = Integer.valueOf(fozarTime.substring(2,4));
                        //   zohorHour+=12;
                        //     setAlarm(fozorHour,fozorMin,1);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }
                    int zohorHour;
                    int zohorMin;
                    if(len==11){
                        zohorHour = Integer.valueOf(zohorTime.substring(0,2));
                        zohorMin = Integer.valueOf(zohorTime.substring(3,5));
                        zohorHour+=12;
                        //     setAlarm(zohorHour,zohorMin,2);
                        //  setAlarm(16,55,2);
                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        zohorHour = Integer.valueOf(zohorTime.substring(0,1));
                        zohorMin = Integer.valueOf(zohorTime.substring(2,4));
                        zohorHour+=12;
                        //         setAlarm(zohorHour,zohorMin,2);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }

//                    int asorHour = Integer.valueOf(asosTime.substring(0, 1));
//                    int asorMin = Integer.valueOf(asosTime.substring(2, 4));
//                    asorHour+=12;
//                    setAlarm(asorHour, asorMin, 3);
                    int asorlen=asosTime.length();
                    int asorHour;
                    int asorMin;
                    if(asorlen==11){
                        asorHour = Integer.valueOf(asosTime.substring(0,2));
                        asorMin = Integer.valueOf(asosTime.substring(3,5));
                        asorHour+=12;
                        //        setAlarm(asorHour,asorMin,3);

                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        asorHour = Integer.valueOf(asosTime.substring(0,1));
                        asorMin = Integer.valueOf(asosTime.substring(2,4));
                        asorHour+=12;
                        //         setAlarm(asorHour,asorMin,3);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }


                    int magriblen=magribTime.length();
                    Log.d("esa length","lenth"+magriblen);
                    int magribHour;
                    int magribMin;
                    if(magriblen==11){
                        magribHour = Integer.valueOf(magribTime.substring(0,2));
                        magribMin = Integer.valueOf(magribTime.substring(3,5));
                        magribHour+=12;
                        //        setAlarm(asorHour,asorMin,3);
                    }
                    else{
                        magribHour = Integer.valueOf(magribTime.substring(0,1));
                        magribMin = Integer.valueOf(magribTime.substring(2,4));
                        magribHour+=12;
                    }



                    int esalen=esaTime.length();
                    Log.d("esa length","lenth"+esalen);
                    int eshaHour;
                    int eshaMin;
                    if(esalen==11){
                        eshaHour = Integer.valueOf(esaTime.substring(0,2));
                        eshaMin = Integer.valueOf(esaTime.substring(3,5));
                        eshaHour+=12;
                        //        setAlarm(eshaHour,eshaMin,5);

                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        eshaHour = Integer.valueOf(esaTime.substring(0,1));
                        eshaMin = Integer.valueOf(esaTime.substring(2,4));
                        eshaHour+=12;
                        //   Log.d("Esha Length","eshaLength"+esalen);
                        //          setAlarm(eshaHour,eshaMin,3);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }


                    //  Log.d("ourTime", "time" + asorHour + "min" + asorMin);
                    int currentOur = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    // int currentAmPm=Calendar.getInstance().get(Calendar.AM_PM);
                    //    Log.d("Current Our","Time "+ currentOur);

                    if ( currentOur <= fozorHour ) {

                        String mh,mm;
                        String aa;


                        mh = fozarTime.substring(0,4);
                        //mm = Integer.valueOf(fozarTime.substring(2,4));
                        aa = fozarTime.substring(8,10);

                        timeTv.setText(" Fajr Prayer Time : "+mh+" "+aa);
                        //      timeTv.setText("Fajr Prayer Time : "+fozarTime);
                        //    Log.d("Time", "Fozor");
                    }

                    else if (currentOur > fozorHour && currentOur <= zohorHour) {


                        //     Calendar calendar = Calendar.getInstance();
                        //   int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        Calendar calendar = Calendar.getInstance();
                        //     calendar.add(Calendar.DAY_OF_YEAR, noOfDaysFromToday);
                        String name = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
                        //        System.out.println("Day Of Week:- " + name);
                        String day="Friday";
                        Log.d("date","Today is" + name);
                        if(name.equals(day)) {
                            timeTv.setText("Zuhr Prayer Time : " + " ");
                        }
                        else {
                            String mh,mm;
                            String aa;


                            mh = zohorTime.substring(0,4);
                         //   mm = Integer.valueOf(zohorTime.substring(2,4));
                            aa = zohorTime.substring(8,10);
                            timeTv.setText(" Zuhr Prayer Time : "+mh+" "+aa);
                            //  timeTv.setText("Zuhr Prayer Time : "+zohorTime);
                        }



                        //  Log.d("Time", "joghor");
                    }

                    else if (currentOur > zohorHour && currentOur <= asorHour) {
                        String mh,mm;
                        String aa;


                        mh = asosTime.substring(0,4);
                        //mm = Integer.valueOf(asosTime.substring(2,4));
                        aa = asosTime.substring(8,10);

                        timeTv.setText(" Asr Prayer Time : "+mh+" "+aa);
                        //  timeTv.setText("Asr Prayer Time : "+asosTime);
                        // Log.d("Time", "asor");
                    } else if (currentOur > asorHour && currentOur <= magribHour) {
                        //  timeTv.setText(" Maghrib Prayer Time : "+magribTime);
                      //  int mh,mm;
                        String mh,mm;
                        String aa;
                        mh=magribTime.substring(0,4);
                        aa = magribTime.substring(8,10);

                        timeTv.setText(" Maghrib Prayer Time : "+mh+" "+aa);
                        //    Log.d("Time", "magrib");
                    } else if (currentOur > magribHour && currentOur <= eshaHour) {

                        String mh,mm;
                        String aa;

                        mh = esaTime.substring(0,5);
                        //mm = Integer.valueOf(esaTime.substring(3,5));
                        aa = esaTime.substring(9,11);

                        timeTv.setText(" Isha Prayer Time : "+mh+" "+aa);
                        //     timeTv.setText("Isha Prayer Time : "+esaTime);
                        //    Log.d("Time", "esha");
                    } else {

                        String mh,mm;
                        String aa;

                        mh = fozarTime.substring(0,4);
                       // mm = Integer.valueOf(fozarTime.substring(2,4));
                        aa = fozarTime.substring(8,10);
                        timeTv.setText(" Fajr Prayer Time : "+mh+" "+aa);
                        // timeTv.setText("Fajr Prayer Time :" +fozarTime);
                    }

//
//                    int hour = Integer.valueOf(fozarTime.substring(0, 1));
//                    int min = Integer.valueOf(fozarTime.substring(2, 4));
//                    // String amPm = time.substring(5, 7);
//                    setAlarm(hour, min, 1);
//              //      Log.d("Time", "hour: " + hour + " min: " + min);
//
//
//                    hour = Integer.valueOf(zohorTime.substring(0,1));
//                    min = Integer.valueOf(zohorTime.substring(2, 4));
//                    //   String amPm = time.substring(5, 7);
//                    setAlarm(hour, min, 2);
//
//                    hour = Integer.valueOf(asosTime.substring(0, 1));
//                    min = Integer.valueOf(asosTime.substring(2, 4));
//                    //  amPm = asosTime.substring(6, 8);
//                    setAlarm(hour, min, 3);
//
//                    hour = Integer.valueOf(magribTime.substring(0, 1));
//                    min = Integer.valueOf(magribTime.substring(2, 4));
//                    //   String amPm = time.substring(5, 7);
//                    setAlarm(hour, min, 4);
//
//                    hour = Integer.valueOf(esaTime.substring(0, 1));
//                    min = Integer.valueOf(esaTime.substring(2, 4));
//                    //   String amPm = time.substring(5, 7);
//                    setAlarm(hour, min, 5);

                    // Log.d("Time", "hour: " + hour + " min: " + min );
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    Toast.makeText(MainActivity.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(request);
        //   getTime();
    }


    //   Ringtone Alam Manager Method
    public void setAlarm(int hour, int min, int requestCode) {
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        // calendar.set(Calendar.AM_PM, ampm);


        int rowId=requestCode;
        if (Calendar.getInstance().after(calendar)) {
            return;
        }

        Log.d("Alarm Time:", "Cal hour: " + calendar.get(Calendar.HOUR_OF_DAY));
        Log.d("AlarmTime:", "Cal min: " + calendar.get(Calendar.MINUTE));


        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("row_id", rowId);
        final int alarmId = (int) System.currentTimeMillis();
        PendingIntent sender = PendingIntent.getBroadcast(this, rowId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

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

    public void donateLink(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page3.php?section=donate&page=donation"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void twiterLink(View view) {

        Uri uri = Uri.parse("https://twitter.com/iccukorg");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void facebookLink(View view) {
        Uri uri = Uri.parse("https://www.facebook.com/iccuk.org");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void LinkOrg(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
    AlarmActivity aa=new AlarmActivity();


    public void instagramLink(View view) {
        Uri uri = Uri.parse("https://www.instagram.com/iccukorg/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
