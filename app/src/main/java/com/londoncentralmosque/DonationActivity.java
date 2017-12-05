package com.londoncentralmosque;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DonationActivity extends AppCompatActivity {
    View section1, section2, section3, section4, section5, section6;
    String url = "http://iccukapp.org/Api_json_format/rest_csv";
    Button home;
    ImageButton twiter,facebook,instagram;

    TextView iccuk,fajr,sunrise,zuhr,asr,maghrib,isha,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        fajr=(TextView)findViewById(R.id.fajr);
        date=(TextView)findViewById(R.id.dateCurrent);
        sunrise=(TextView)findViewById(R.id.sunrise);
        asr=(TextView)findViewById(R.id.asr);
        zuhr=(TextView)findViewById(R.id.zuhr);
        maghrib=(TextView)findViewById(R.id.magrib);
        isha=(TextView)findViewById(R.id.isha);
        iccuk=(TextView)findViewById(R.id.iccuk);
        iccuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.iccuk.org/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

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



        getPrayerTime();
//        section1 = findViewById(R.id.section1);
//        section2 = findViewById(R.id.section2);
//        section3 = findViewById(R.id.section3);
//        section4 = findViewById(R.id.section4);
//        section5 = findViewById(R.id.section5);
//        section6 = findViewById(R.id.section6);
//
//        View header1 = findViewById(R.id.header1);
//        header1.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if (section1.getVisibility() == View.GONE)
//                {
//                    section1.setVisibility(View.VISIBLE);
//                    section2.setVisibility(View.GONE);
//                    section3.setVisibility(View.GONE);
//                    section4.setVisibility(View.GONE);
//                    section5.setVisibility(View.GONE);
//                    section6.setVisibility(View.GONE);
//                }
//                else
//                {
//                    section1.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        View header2 = findViewById(R.id.header2);
//        header2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if (section2.getVisibility() == View.GONE)
//                {
//                    section2.setVisibility(View.VISIBLE);
//                    section1.setVisibility(View.GONE);
//
//                    section3.setVisibility(View.GONE);
//                    section4.setVisibility(View.GONE);
//                    section5.setVisibility(View.GONE);
//                    section6.setVisibility(View.GONE);
//                }
//                else
//                {
//                    section2.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        View header3 = findViewById(R.id.header3);
//        header3.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if (section3.getVisibility() == View.GONE)
//                {
//                    section3.setVisibility(View.VISIBLE);
//                    section1.setVisibility(View.GONE);
//                    section2.setVisibility(View.GONE);
//
//                    section4.setVisibility(View.GONE);
//                    section5.setVisibility(View.GONE);
//                    section6.setVisibility(View.GONE);
//                }
//                else
//                {
//                    section3.setVisibility(View.GONE);
//                }
//            }
//        });
//        View header4 = findViewById(R.id.header4);
//        header4.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if (section4.getVisibility() == View.GONE)
//                {
//                    section4.setVisibility(View.VISIBLE);
//                    section1.setVisibility(View.GONE);
//                    section2.setVisibility(View.GONE);
//                    section3.setVisibility(View.GONE);
//
//                    section5.setVisibility(View.GONE);
//                    section6.setVisibility(View.GONE);
//                }
//                else
//                {
//                    section4.setVisibility(View.GONE);
//                }
//            }
//        });
//        View header5 = findViewById(R.id.header5);
//        header5.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if (section5.getVisibility() == View.GONE)
//                {
//                    section5.setVisibility(View.VISIBLE);
//                    section1.setVisibility(View.GONE);
//                    section2.setVisibility(View.GONE);
//                    section3.setVisibility(View.GONE);
//                    section4.setVisibility(View.GONE);
//
//                    section6.setVisibility(View.GONE);
//                }
//                else
//                {
//                    section5.setVisibility(View.GONE);
//                }
//            }
//        });
//        View header6 = findViewById(R.id.header6);
//        header6.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if (section6.getVisibility() == View.GONE)
//                {
//                    section6.setVisibility(View.VISIBLE);
//                    section1.setVisibility(View.GONE);
//                    section2.setVisibility(View.GONE);
//                    section3.setVisibility(View.GONE);
//                    section4.setVisibility(View.GONE);
//                    section5.setVisibility(View.GONE);
//
//                }
//                else
//                {
//                    section6.setVisibility(View.GONE);
//                }
//            }
//        });
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

    public void getPrayerTime() {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("res");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String fozarTime = jsonObject.getString("fazar");
                    String  sunriseTime = jsonObject.getString("sunrise");
                    String zohorTime = jsonObject.getString("zohor");
                    String asosTime = jsonObject.getString("asar");
                    String magribTime = jsonObject.getString("magrib");
                    String esaTime = jsonObject.getString("esa");

                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => " + c.getTime());

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c.getTime());


                    date.setText(formattedDate);

                    int mh,mm;
                    String aa;


                    mh = Integer.valueOf(fozarTime.substring(0,1));
                    mm = Integer.valueOf(fozarTime.substring(2,4));
                    aa = fozarTime.substring(8,10);
                    fajr.setText(+mh+":"+mm+" "+aa);

                    int mh1,mm1;
                    String aa1;
                    mh1 = Integer.valueOf(sunriseTime.substring(0,1));
                    mm1 = Integer.valueOf(sunriseTime.substring(2,4));
                    aa1 = sunriseTime.substring(8,10);
                    sunrise.setText(+mh1+":"+mm1+" "+aa1);

                    int mh2,mm2;
                    String aa2;
                    mh2 = Integer.valueOf(zohorTime.substring(0,1));
                    mm2 = Integer.valueOf(zohorTime.substring(2,4));
                    aa2 = zohorTime.substring(8,10);
                    zuhr.setText(+mh2+":"+mm2+" "+aa2);

                    int mh3,mm3;
                    String aa3;
                    mh3 = Integer.valueOf(asosTime.substring(0,1));
                    mm3 = Integer.valueOf(asosTime.substring(2,4));
                    aa3 = asosTime.substring(8,10);
                    asr.setText(+mh3+":"+mm3+" "+aa3);

                    int magriblen=magribTime.length();

                    int mh4,mm4;
                    String aa4;
                    if(magriblen==10) {
                        mh4 = Integer.valueOf(magribTime.substring(0, 1));
                        mm4 = Integer.valueOf(magribTime.substring(2, 4));
                        aa4 = magribTime.substring(8, 10);
                        maghrib.setText(+mh4 + ":" + mm4 + " " + aa4);
                    }else{
                        mh4 = Integer.valueOf(magribTime.substring(0, 2));
                        mm4 = Integer.valueOf(magribTime.substring(3, 5));
                        aa4 = magribTime.substring(9, 11);
                        maghrib.setText(+mh4 + ":" + mm4 + " " + aa4);
                    }

                    int esalen=esaTime.length();
                    int mh5,mm5;
                    String aa5;
                    if(esalen==11) {
                        mh5 = Integer.valueOf(esaTime.substring(0, 2));
                        mm5 = Integer.valueOf(esaTime.substring(3, 5));
                        aa5 = esaTime.substring(9, 11);
                        isha.setText(+mh5 + ":" + mm5 + " " + aa5);
                    }else{
                        mh5 = Integer.valueOf(esaTime.substring(0, 1));
                        mm5 = Integer.valueOf(esaTime.substring(2, 4));
                        aa5 = esaTime.substring(8, 10);
                        isha.setText(+mh5 + ":" + mm5 + " " + aa5);
                    }
//                    zuhr.setText(zohorTime);
//                    asr.setText(asosTime);
//                    maghrib.setText(magribTime);
//                    isha.setText(esaTime);


                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    Toast.makeText(DonationActivity.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(request);
        //   getTime();
    }


    public void donateLink(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_zakat"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateFitr(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_zakatalfitr"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateSadaqah(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_sadaqah"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateFidya(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_fidya"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateKaffarah(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_fidya"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateMosque(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_mosque"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateZakat(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_zakat"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}


