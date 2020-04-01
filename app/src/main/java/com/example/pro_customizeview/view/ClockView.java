package com.example.pro_customizeview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import com.example.pro_customizeview.R;
import java.time.LocalTime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ClockView extends View {
    //表盘，时针，分针，秒针图片
    private Bitmap bitDial;
    private Bitmap bitH;
    private Bitmap bitM;
    private Bitmap bitS;
    private Bitmap bitCenter;
    //画笔
    private Paint mPainter;
    //时间对象
    private LocalTime mTimer;
    //判断是否View在window中
    private boolean isAttach;
    //线程
    private Thread clockThread;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmap();
        initPainter();
        initTime();
    }

    //初始化图片
    private void initBitmap(){
        bitDial= BitmapFactory.decodeResource(getResources(),R.drawable.clock_dial);
        bitH= BitmapFactory.decodeResource(getResources(),R.drawable.hour_hand);
        bitM= BitmapFactory.decodeResource(getResources(),R.drawable.minute_hand);
        bitS= BitmapFactory.decodeResource(getResources(),R.drawable.sec_hand);
        bitCenter=BitmapFactory.decodeResource(getResources(),R.drawable.hand_center);
    }
    //初始化画笔
    private void initPainter(){
        mPainter=new Paint();
        mPainter.setAntiAlias(true);
    }
    //初始化时间
    private void initTime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            clockThread = new Thread(){
                @Override
                public void run() {
                    while (isAttach){
                        mTimer = LocalTime.now();
                        //invalidate()方法不能在非UI线程重绘
                        postInvalidate();
                        SystemClock.sleep(1000);
                    }
                }
            };
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode){
            case MeasureSpec.AT_MOST:
                widthSize=bitDial.getWidth();
                break;
        }
        switch (heightMode){
            case MeasureSpec.AT_MOST:
                heightSize=bitDial.getHeight();
                break;
        }
        //设置宽高
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int hour=0;
        int minite=0;
        int second=0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //设置当前时间
            hour = mTimer.getHour();
            minite = mTimer.getMinute();
            second = mTimer.getSecond();
        }
        //控件宽高
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int centerX;
        int centerY;
        int rotateCenterX=width/2;
        int rotateCenterY=height/2;
        float rotateDegrees;
        //绘制表盘
        canvas.drawBitmap(bitDial,0,0,mPainter);
        //绘制时针
        centerX = width/2-bitH.getWidth()/2;
        centerY= height/2-bitH.getHeight()+bitCenter.getHeight()/2;
        rotateDegrees=(float)(hour/12.0*360.0);
        canvas.save();
        canvas.rotate(rotateDegrees,rotateCenterX,rotateCenterY);
        canvas.drawBitmap(bitH,centerX,centerY,mPainter);
        canvas.restore();

        //绘制分针
        centerX = width/2-bitM.getWidth()/2;
        centerY= height/2-bitM.getHeight()+bitCenter.getHeight()/2;
        rotateDegrees=(float)(minite/60.0*360);
        canvas.save();
        canvas.rotate(rotateDegrees,rotateCenterX,rotateCenterY);
        canvas.drawBitmap(bitM,centerX,centerY,mPainter);
        canvas.restore();

        //绘制秒针
        centerX = width/2-bitS.getWidth()/2;
        centerY= height/2-bitS.getHeight()+bitCenter.getHeight()/2;
        rotateDegrees=(float)(second/60.0*360);
        canvas.save();
        canvas.rotate(rotateDegrees,rotateCenterX,rotateCenterY);
        canvas.drawBitmap(bitS,centerX,centerY,mPainter);
        canvas.restore();

        //绘制中心点
        centerX = width/2-bitCenter.getWidth()/2;
        centerY= height/2-bitCenter.getHeight()/2;
        canvas.drawBitmap(bitCenter,centerX,centerY,mPainter);
    }
    //组件在window显示
    @Override
    protected void onAttachedToWindow() {
        isAttach=true;
        clockThread.start();
        super.onAttachedToWindow();
    }
    //组件不在window显示
    @Override
    protected void onDetachedFromWindow() {
        isAttach=false;
        super.onDetachedFromWindow();
    }
}
