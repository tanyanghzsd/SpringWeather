package com.example.springweather.customview;

import com.example.springweather.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleView extends LinearLayout {
	private TextView tv;
	
	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title_custome	, this);
		tv=(TextView)findViewById(R.id.textview);
		
	}
	
	public void setText(String text){
		tv.setText(text);
	}
	
	

}
