package com.example.pro_customizeview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

//侧拉菜单
public class SlideMenu extends ViewGroup {
    private View mMenuView;
    private View mContentView;
    private float mStartX;
    private float mStartY;
    private float mDraw;
    //使用scroller创建动画
    private Scroller mScroller;

    public SlideMenu(Context context) {
        super(context);
    }
    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
    }
    //测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //强制测量
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }
    //设置布局layout
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //menu子控件位置，左边坐标：负的menu子控件宽度，右边坐标：0
        //content子控件位置，左边坐标：0，右边坐标屏幕宽度
        //获取子控件
        mMenuView=getChildAt(0);
        mContentView=getChildAt(1);
        int dx = (int)mDraw;
        mMenuView.layout(-mMenuView.getMeasuredWidth()+dx,0,0+dx,mMenuView.getMeasuredHeight());
        mContentView.layout(0+dx,0,mContentView.getMeasuredWidth()+dx,mContentView.getMeasuredHeight());
    }
    //触摸事件
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

//                //方式1：边距限制,当向左滑动，mDraw小于0,当向右滑动距离不能超过menu宽度
//                if (mDraw<0){
//                    mDraw=0;
//                }else if(mDraw>mMenuView.getMeasuredWidth()){
//                    mDraw = mMenuView.getMeasuredWidth();
//                }
//                //方法2：通过scroll方法，限制滑动边距
//               // x为负数向右滑动，y为负数向下滑动
//                scrollBy((int)-dx,0);
//                int scrollX = getScrollX();
//                //向右移到，超出右边界
//                if (scrollX > 0){
//                    scrollTo(0,0);
//                }else if(-scrollX>mMenuView.getMeasuredWidth()){
//                    //向左拖动,超出左边界
//                    scrollTo(-mMenuView.getMeasuredWidth(),0);
//                }
                //使用scroller,动画
               // x为负数向右滑动，y为负数向下滑动
                int scrollX = getScrollX();
                //向右移到，超出右边界
                if (scrollX > 0){
                    //x起点，y起点，偏移量
                    mScroller.startScroll(scrollX,0,mMenuView.getMeasuredWidth(),0);
                    //重绘
                    invalidate();
                }else if(-scrollX>mMenuView.getMeasuredWidth()){
                    //向左拖动,超出左边界
                    //x起点，y起点，偏移量
                    mScroller.startScroll(scrollX,0,-scrollX,0);
                    //重绘
                    invalidate();
                }

                //终点变起点
                mStartX = endX;
                mStartY = endY;
                break;
            case MotionEvent.ACTION_UP:
                //如果滑块滑动menu宽度1/2距离
                float distance = mMenuView.getMeasuredWidth()/2;
                if (mDraw<distance){
                    mDraw = 0;
                }else {
                    mDraw = mMenuView.getMeasuredWidth();
                }
                break;
        }
        //重新布局
        requestLayout();
        //返回true,控件自己响应点击事件
        return true;
    }

    //控件动画,会在draw时调用
    //在scroll完成前会一直递归调用重绘
    @Override
    public void computeScroll() {
        super.computeScroll();
        //是否scroll完成
        if (mScroller.computeScrollOffset()){
            int currentX = mScroller.getCurrX();
            scrollTo(currentX,0);
            //重绘
            invalidate();
        }
    }
}
