package com.example.pro_customizeview.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pro_customizeview.R;
import com.example.pro_customizeview.onSlideButtonListener;
import com.example.pro_customizeview.view.ButtonView;

import androidx.annotation.Nullable;

//自定义开关按钮
public class ButtonActivity extends Activity {
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = ButtonActivity.this;
        setContentView(R.layout.l_customize4);
        //获取自定义开关按钮组件
        ButtonView buttonView=findViewById(R.id.btn_1);
        //设置接口
        buttonView.setOnSlideButtonListener(new onSlideButtonListener() {
            @Override
            public void onSlideButtonChange(boolean isOpen) {
                if (isOpen){
                    Toast.makeText(mContext,"开关打开",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext,"开关关闭",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
