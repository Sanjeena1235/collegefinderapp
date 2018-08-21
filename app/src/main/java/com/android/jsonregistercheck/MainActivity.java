package com.android.jsonregistercheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.jsonregistercheck.authentication.Register;
import com.android.jsonregistercheck.collegeinfo.College_Display;
import com.android.jsonregistercheck.front.Nearby_College;
import com.android.jsonregistercheck.search.Searchcollege;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 =(Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile=new Intent(MainActivity.this,College_Display.class);
                startActivity(profile);
            }
        });

        btn2 =(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile =new Intent(MainActivity.this,Register.class);
                startActivity(profile);
            }
        });

        btn3=(Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(MainActivity.this,Searchcollege.class);
                startActivity(profile);
            }
        });

        btn4=(Button)findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile=new Intent(MainActivity.this,Nearby_College.class);
                startActivity(profile);
            }
        });

    }
    public void backArrow(View view) {
        finish();
    }
}

