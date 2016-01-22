package com.example.springweather.customview;

import com.example.springweather.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class CustomDialog2 extends Dialog{
	
	private Context context;
	public CustomDialog2(Context context, int theme) {
		super(context, theme);
		this.context=context;
	}

	public CustomDialog2(Context context) {
		super(context);
		this.context=context;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog2);

	}
}
