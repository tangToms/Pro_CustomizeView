package com.example.pro_customizeview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pro_customizeview.R;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    Button btn_customize1;
    Button btn_customize2;
    Button btn_customize3;
    Button btn_customize4;
    Button btn_customize5;
    Button btn_customize6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main);
        //跳转到自定义控件1
        btn_customize1=findViewById(R.id.btn_cutomize1);
        btn_customize1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, CustomzizeActivity.class);
                startActivity(intent);
            }
        });
        //自定义控件2
        btn_customize2=findViewById(R.id.btn_cutomize2);
        btn_customize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ScrollViewActivity.class);
                startActivity(intent);
            }
        });
        //自定义控件3
        btn_customize3=findViewById(R.id.btn_cutomize3);
        btn_customize3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TouchEventActivity.class);
                startActivity(intent);
            }
        });
        //自定义控件4
        btn_customize4=findViewById(R.id.btn_cutomize4);
        btn_customize4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ButtonActivity.class);
                startActivity(intent);
            }
        });
        //自定义控件5
        btn_customize5=findViewById(R.id.btn_cutomize5);
        btn_customize5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SlideMenuActivity.class);
                startActivity(intent);
            }
        });
        //自定义控件6
        btn_customize6=findViewById(R.id.btn_cutomize6);
        btn_customize6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ClockActivity.class);
                startActivity(intent);
            }
        });
    }
}
