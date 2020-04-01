package com.example.pro_customizeview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.pro_customizeview.R;
import com.example.pro_customizeview.onSlideButtonListener;

import androidx.annotation.Nullable;

//自定义按钮开关
public class ButtonView extends View{
    //绘制按钮背景和按钮图片
    private Bitmap bitBackground;
    private Bitmap bitButton;
    private Paint mPaint;
    //坐标变化
    private float mStartX;
    private float mStartY;
    //绘制变化的button横坐标
    private float mDraw = 0;
    //记录开关状态
    private boolean mOpen = false;
    //开关状态接口
    private onSlideButtonListener onSlideButtonListener;
    public ButtonView(Context context) {
        super(context);
    }
    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initPicture();
    }
    //初始化读取两张图片
    private void initPicture(){
        bitBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        bitButton = BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);
    }
    //初始化画笔
    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }
    //设置接口方法
    public void setOnSlideButtonListener(com.example.pro_customizeview.onSlideButtonListener onSlideButtonListener) {
        this.onSlideButtonListener = onSlideButtonListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //1、获取mode和size
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        //2、通过mode设置合理size
        switch (widthMode){
            //wrap_content
            case MeasureSpec.AT_MOST:
                //宽度设置，宽度为背景图片宽度
                widthSize = bitBackground.getWidth();
                break;
        }
        switch (heightMode){
            //父控件对控件限制最大值，wrap_content
            case MeasureSpec.AT_MOST:
                //高度设置,高度为button图片高度
                heightSize = bitButton.getHeight();
                break;
        }
        //4.设置大小
        setMeasuredDimension(widthSize,heightSize);
    }

    //绘制图片
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.drawBitmap(bitBackground,0,0,mPaint);
        //绘制时控制边界
        //右边界
        float rightLine = getMeasuredWidth() - bitButton.getWidth();
        if (mDraw<0){
            mDraw = 0;
        }else if(mDraw>rightLine){
            mDraw = rightLine;
        }
        //当前开关状态
        if (mDraw == 0){
            mOpen = false;
            onSlideButtonListener.onSlideButtonChange(mOpen);
        }else if(mDraw == rightLine){
            mOpen = true;
            onSlideButtonListener.onSlideButtonChange(mOpen);
        }
        canvas.drawBitmap(bitButton,mDraw,0,mPaint);
    }

    //响应触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取动作类型
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();

                //获取偏移量
                float dx = endX - mStartX;
                mDraw = mDraw + dx;
                //终点变起点
                mStartX = endX;
                mStartY = endY;
                break;
            case MotionEvent.ACTION_UP:
                //如果滑块滑动超过1/4距离
                float distance = getMeasuredWidth()/4;
                if (mDraw<distance){
                    mDraw = 0;
                }else {
                    mDraw = getMeasuredWidth()-bitButton.getWidth();
                }
                break;
        }
        //重新绘制图片
        invalidate();
        //返回true,控件自己响应点击事件
        return true;
    }
}
