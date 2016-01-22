package com.example.springweather.aty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;

import com.baidu.apistore.sdk.asb.e;
import com.baidu.apistore.sdk.network.d;
import com.example.springweather.R;
import com.example.springweather.model.DatabaseUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

public class AtyMain extends AtyBase{
	private TabHost tabHost;
	private boolean isFirstOpen;
	private final static String FILE_NAME="citycode.json";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main);

		new JSONThread().start();

		tabHost=(TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		final TabSpec tabSpec1=tabHost.newTabSpec("weather").setIndicator(null, getResources().getDrawable(R.drawable.sun2)).setContent(R.id.tab1);
		final TabSpec tabSpec2=tabHost.newTabSpec("tip").setIndicator(null, getResources().getDrawable(R.drawable.love)).setContent(R.id.tab2);
		final TabSpec tabSpec3=tabHost.newTabSpec("list").setIndicator(null, getResources().getDrawable(R.drawable.list)).setContent(R.id.tab3);

		
		tabHost.addTab(tabSpec1);
		tabHost.addTab(tabSpec2);
		tabHost.addTab(tabSpec3);

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String arg0) {
				if(arg0.equals("weather")){
					ImageView iv=(ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.sun2));
					iv = (ImageView)tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.love));
					iv = (ImageView)tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.list));
				}
				else if(arg0.equals("tip")){
					ImageView iv=(ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.sun));
					iv = (ImageView)tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.love2));
					iv = (ImageView)tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.list));
				}else if(arg0.equals("list")){
					ImageView iv=(ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.sun));
					iv = (ImageView)tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.love));
					iv = (ImageView)tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.icon);
					iv.setImageDrawable(getResources().getDrawable(R.drawable.list2));
				}

			}
		});

		TabWidget tabWidget=tabHost.getTabWidget();
		for (int i=0; i<tabWidget.getChildCount(); i++){							//循环每个tabView
			View view = tabWidget.getChildAt(i);									//获取tabView项	
			view.setBackgroundResource(R.color.transparent);
			
		}
		
		
		SharedPreferences pref=this.getSharedPreferences("mypref", MODE_PRIVATE);
		if(pref.getString("my_id","")==null){
			SharedPreferences.Editor editor=pref.edit();
			editor.putString("my_id", "id");
			editor.commit();
			tabWidget.bringChildToFront(tabWidget.getChildAt(2));
		}else{
			
		}
	}

	class JSONThread extends Thread {
		private final static String FILE_NAME="citycode.json";
		JSONArray jb;

		@Override
		public void run() {
			String jsonStr=getJson(getBaseContext(), FILE_NAME);

			try {
				jb = new JSONArray(jsonStr);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			for (int i=0;i<jb.length();i++){
				//得到所有城市
				JSONArray cityArray = null;
				try {
					cityArray = jb.getJSONObject(i).getJSONArray("city");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				for (int j = 0; j < cityArray.length(); j++) {
					String cityName = null;
					try {
						cityName = cityArray.getJSONObject(j).getString("name");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					DatabaseUtil.save(AtyMain.this,cityName);
				}
			}

		}

		public  String getJson(Context context,String fileName){
			StringBuilder stringBuilder=new StringBuilder();
			try {
				AssetManager assetManager=context.getAssets();
				BufferedReader bf=new BufferedReader(new InputStreamReader(assetManager.open(FILE_NAME)));
				String line;
				while((line=bf.readLine())!=null){
					stringBuilder.append(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return stringBuilder.toString();
		}
	}

	
}
