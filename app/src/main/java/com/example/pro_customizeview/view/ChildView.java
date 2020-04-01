package com.example.pro_customizeview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ChildView extends View {
    public ChildView(Context context) {
        super(context);
    }
    public ChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    //分发touch事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("Touch","ChildView dispatch touch event");
        return super.dispatchTouchEvent(ev);
    }
    //触摸touch事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Touch","ChildView touch event");
        return super.onTouchEvent(event);
    }
}
