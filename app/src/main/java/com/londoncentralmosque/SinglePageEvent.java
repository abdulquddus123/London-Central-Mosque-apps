package com.londoncentralmosque;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SinglePageEvent extends AppCompatActivity {
    TextView dateTv,titleTv,descriptionTv,iccuk;

    Button home;
    ImageButton twiter,facebook,instagram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_page_event);

        dateTv=(TextView)findViewById(R.id.dateEt);
        titleTv=(TextView)findViewById(R.id.titleEt);
        descriptionTv=(TextView)findViewById(R.id.descriptionEt);
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


        dataShow();


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
    public void dataShow(){
        String date = getIntent().getExtras().getString("Date");
        dateTv.setText(date);

        String title = getIntent().getExtras().getString("Title");
        titleTv.setText(title);

        String description = getIntent().getExtras().getString("Description");
        descriptionTv.setText(description);

    }
}
