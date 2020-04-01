package com.example.pro_customizeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.pro_customizeview.R;

import androidx.annotation.Nullable;

//滚动的组件
public class ScrollView extends View {
    //自定义属性
    private int mSourceId;
    private int mSpeed;
    private int mStartPos;
    private Bitmap bitmap;
    //画笔
    private Paint paint;
    public ScrollView(Context context) {
        super(context);
    }
    public ScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray typedArray= context.obtainStyledAttributes(attrs, R.styleable.ScrollView);
        //获取自定义属性值
        mSourceId=typedArray.getResourceId(R.styleable.ScrollView_picSourceId,R.drawable.ic_launcher);
        mSpeed=typedArray.getInteger(R.styleable.ScrollView_picSpeed,2);
        typedArray.recycle();
        //通过资源id，获取位图
        bitmap= BitmapFactory.decodeResource(getResources(),mSourceId);
        //创建画笔
        paint=new Paint();
        //初始化绘制位置
        mStartPos=0;
    }
    //设置组件宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取组件宽度
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        //设置组件宽高
        setMeasuredDimension(widthSize,bitmap.getHeight());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //控制绘制起始坐标边长
        if (mStartPos<0-bitmap.getWidth()){
            mStartPos=0;
        }
        //不断修改起始绘制坐标
        int left=mStartPos;
        //当控件宽度没有超过屏幕一直绘制
        while (left<getMeasuredWidth()){
            canvas.drawBitmap(bitmap,left,0,paint);
            left+=bitmap.getWidth();
        }
        //根据速度修改起始坐标
        mStartPos -=mSpeed;
        //重绘
        invalidate();
    }
}
