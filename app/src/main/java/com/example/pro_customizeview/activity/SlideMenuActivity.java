package com.example.pro_customizeview.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.pro_customizeview.R;
import com.example.pro_customizeview.view.SlideMenu;

import androidx.annotation.Nullable;

public class SlideMenuActivity extends Activity {
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_customize5);
        mContext= SlideMenuActivity.this;
    }
}
