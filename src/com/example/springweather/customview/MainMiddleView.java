package com.example.springweather.customview;

import com.example.springweather.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainMiddleView extends LinearLayout {
	private TextView tv1,tv2;
	private ImageView iv;
	private LinearLayout layout;

	public MainMiddleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.main_middle2, this);
		tv1=(TextView) findViewById(R.id.main_middle_tv_weekday1);
		tv2=(TextView) findViewById(R.id.main_middle_temp1);
		iv=(ImageView) findViewById(R.id.main_middle_image1);
		layout=(LinearLayout) findViewById(R.id.main_middle2_layout);
	}

	public void setBackGround(int id){
		layout.setBackgroundResource(id);
	}
	public void setFirstTextViewText(String text){
		tv1.setText(text);
	}
	
	public void setSecondTextViewText(String text){
		tv2.setText(text);
	}
	
	public void setBackgroundResource(int resid){
		iv.setBackgroundResource(resid);
	}
	
	public void setBackgroundDrawable(Drawable drawable){
		iv.setBackgroundDrawable(drawable);
	}
}
