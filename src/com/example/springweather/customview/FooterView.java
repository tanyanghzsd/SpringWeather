package com.example.springweather.customview;

import com.example.springweather.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class FooterView extends FrameLayout implements OnClickListener{
	private Button button1,button2,button3;

	
	

	public FooterView(Context context,AttributeSet attrs) {
		super(context,attrs);
		LayoutInflater.from(context).inflate(R.layout.footer_custome, this);
		button1=(Button) findViewById(R.id.button1);
		button2=(Button) findViewById(R.id.button2);
		button3=(Button) findViewById(R.id.button3);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
	}


	public void setLeftButtonBackground(int resid){
		button1.setBackgroundResource(resid);
	}
	
	
	public void setMiddleButtonBackground(int resid){
		button2.setBackgroundResource(resid);
	}
	
	public void setRightButtonBackground(int resid){
		button3.setBackgroundResource(resid);
	}
	
	
	public void setLeftButtonListener(OnClickListener l){
		button1.setOnClickListener(l);
	}
	
	public void setMiddleButtonListener(OnClickListener l){
		button2.setOnClickListener(l);
	}
	
	public void setRightButtonListener(OnClickListener l){
		button3.setOnClickListener(l);
	}

	public void openActivity(Class<?> pClass){
		Intent i=new Intent(getContext(),pClass);
		getContext().startActivity(i);
	}
	
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			((Activity) getContext()).finish();
			break;
		case R.id.button2:
			
			break;
		case R.id.button3:
			
			break;
		default:
			break;
		}
	}

}
