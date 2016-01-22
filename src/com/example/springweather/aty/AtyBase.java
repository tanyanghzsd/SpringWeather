package com.example.springweather.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class AtyBase extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	public void showMsg(String msg){
		Toast.makeText(this, msg, 1).show();
	}
	
	public void openActivity(Class<?> pClass){
		Intent intent=new Intent();
		intent.setClass(this,pClass);
		startActivity(intent);
	}
}
