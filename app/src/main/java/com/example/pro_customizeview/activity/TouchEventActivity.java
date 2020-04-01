package com.example.pro_customizeview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.pro_customizeview.R;

import androidx.annotation.Nullable;

public class TouchEventActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_customize3);
    }
    //Activity中dispatch事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("Touch","TouchEventActivity  dispatch touch event");
        return super.dispatchTouchEvent(ev);
    }
    //Actiity中touch事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Touch","TouchEventActivity touch event");
        return super.onTouchEvent(event);
    }
}
