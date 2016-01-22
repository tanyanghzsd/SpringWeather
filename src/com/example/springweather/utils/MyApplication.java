package com.example.springweather.utils;

import com.baidu.apistore.sdk.ApiStoreSDK;

import android.app.Application;

public class MyApplication extends Application{

	@Override
	public void onCreate() {
		ApiStoreSDK.init(this, "89db5811888f133677dcdf32d4e16018");
		super.onCreate();
	}
}
