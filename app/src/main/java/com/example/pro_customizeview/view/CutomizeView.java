package com.example.pro_customizeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.pro_customizeview.R;

import androidx.annotation.Nullable;

//自定义绘制View
public class CutomizeView extends View {
    //构造方法
    public CutomizeView(Context context) {
        super(context);
    }
    //构造方法,这个构造必须有
    public CutomizeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //所有的属性存放在AttributeSet中
        int count = attrs.getAttributeCount();
        //1、遍历获取
        for(int i=0;i<count;i++){
            //获取属性名
            String attrName=attrs.getAttributeName(i);
            //获取属性值
            String attrValue=attrs.getAttributeValue(i);
        }
        //2、直接获取到自定义属性
        //在R文件中会自动生成
        TypedArray typedArray= context.obtainStyledAttributes(attrs,R.styleable.CutomizeView);
        //将获取到的属性可以在onDraw，onMesure,onLayout中使用。
        int fontColor=typedArray.getColor(R.styleable.CutomizeView_fontColor,0xFF0022FF);
        float fontSize=typedArray.getDimension(R.styleable.CutomizeView_fontSize,20);
        Log.i("Customize","color:"+fontColor);
        Log.i("Customize","size:"+fontSize);
        //注意使用完释放资源
        typedArray.recycle();
    }

    //重新onDraw方法
    @Override
    protected void onDraw(Canvas canvas) {
        //矩形位置坐标
        RectF rectF=new RectF(100,100,400,400);
        //画笔
        Paint paint=new Paint();
        //设置画笔
        //设置反锯齿
        paint.setAntiAlias(true);
        //设置绘制颜色
        paint.setColor(0xFF20FF22);
        //设置绘制样式
        paint.setStyle(Paint.Style.STROKE);
        //设置绘制文本大小
        paint.setTextSize(100);
        //绘制矩形
        canvas.drawRect(rectF,paint);
        //绘制椭圆
        canvas.drawOval(rectF,paint);
        //绘制圆
        canvas.drawCircle(300,300,200,paint);
        //绘制线段
        canvas.drawLine(0,0,100,100,paint);
        //绘制文本
        canvas.drawText("hello",200,900,paint);
        //绘制圆弧
        canvas.drawArc(rectF,0,100,true,paint);
        //绘制点
        canvas.drawPoint(800,800,paint);
        //绘制图片
        //bitmap位图，1、将项目已有图片转为bitmap
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        canvas.drawBitmap(bitmap,200,1000,paint);
        //2.创建一个bitmap
        //Bitmap bitmap1=Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
        //绘制路径
        drawPath(canvas);
    }
    //绘制路径
    public void drawPath(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(0xFF0022FF);
        paint.setStyle(Paint.Style.STROKE);
        //绘制路径
        Path path=new Path();
        //起点
        path.moveTo(300,400);
        //直线
        path.lineTo(500,200);
        path.lineTo(550,350);
        path.lineTo(800,700);
        //曲线
        path.quadTo(500,300,300,800);
        canvas.drawPath(path,paint);
    }
    //测量方法，获取控件宽高，模式
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //1、获取mode和size
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        //2、通过mode设置合理size
        switch (widthMode){
            //父控件对子控件限制最大值，wrap_content
            case MeasureSpec.AT_MOST:
                //宽度设置,
                break;
            //控件大小是个确定值
            case MeasureSpec.EXACTLY:
                break;
            //父控件对子控件大小不做限定
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        switch (heightMode){
            //父控件对控件限制最大值，wrap_content
            case MeasureSpec.AT_MOST:
                //宽度设置,
                break;
            //控件大小是个确定值,layout中设置为具体20dp等或者match_parent，父控件大小确定
            case MeasureSpec.EXACTLY:
                break;
            //父控件对控件大小不做限定，ListView中子item数量不定
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        //4.设置大小
        setMeasuredDimension(widthSize,heightSize);
    }
    //父控件中子控件布局，ViewGroup必须实现
    //设置容器中子控件布局
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //通过getChildCount获取子控件数量
        //通过getChildAt()获取子控件childView
        //通过childView的layout方法设置子控件位置
    }
}
