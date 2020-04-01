package com.example.pro_customizeview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class ParentView extends LinearLayout {
    public ParentView(Context context) {
        super(context);
    }
    public ParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    //分发touch事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("Touch","ParentView dispatch touch event");
        return super.dispatchTouchEvent(ev);
    }
    //触摸touch事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Touch","ParentView touch event");
        return super.onTouchEvent(event);
    }
    //拦截touch事件,只有ViewGroup及其子类有拦截事件，普通view没有
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("Touch","ParentView intercept touch event");
        return super.onInterceptTouchEvent(ev);
    }

}
