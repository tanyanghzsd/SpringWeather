package com.example.springweather.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;

import com.example.springweather.R;
import com.example.springweather.model.DatabaseUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import android.view.View;

public class HandleUtil {
	private Context context;
	private final static String FILE_NAME="citycode.json";
	private  static ArrayList<String> list=new ArrayList<String>();



	public static void getAllCity(Context context){
		try {
			String jsonStr=getJsonString(context);
			JSONArray jb=new JSONArray(jsonStr);
			for (int i=0;i<jb.length();i++){
				//得到所有城市
				JSONArray cityArray=jb.getJSONObject(i).getJSONArray("city");
				for (int j = 0; j < cityArray.length(); j++) {
					String cityName=cityArray.getJSONObject(j).getString("name");
					DatabaseUtil.save(context,cityName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//得到asset的json字符串
	public  static String getJsonString(Context context){
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


	public static List<String> removeDuplicate(List<String> list){ 
		for  (int i=0;i<list.size()-1;i++){ 
			for(int j=list.size()-1;j>i;j--){ 
				if(list.get(j).equals(list.get(i))){ 
					list.remove(j); 
				} 
			} 
		} 
		return list;
	} 

	public static Bitmap takeScreenShot(Activity activity) {
		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();
		// 获取状态栏高度
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		Log.i("TAG", "" + statusBarHeight);
		// 获取屏幕长和高
		int width = activity.getWindowManager().getDefaultDisplay().getWidth();
		int height = activity.getWindowManager().getDefaultDisplay()
				.getHeight();
		// 去掉标题栏
		// Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
				- statusBarHeight);
		view.destroyDrawingCache();
		return b;
	}


	public static void savePic(Bitmap b, String strFileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(strFileName);
			if (null != fos) {
				b.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分享功能
	 * 
	 * @param context
	 *            上下文
	 * @param activityTitle
	 *            Activity的名字
	 * @param msgTitle
	 *            消息标题
	 * @param msgText
	 *            消息内容
	 * @param imgPath
	 *            图片路径，不分享图片则传null
	 */
	public static void shareMsg(Context context,String activityTitle, String msgTitle, String msgText,
			String imgPath) {
		android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_SEND);
		if (imgPath == null || imgPath.equals("")) {
			intent.setType("text/plain"); // 纯文本
		} else {
			File f = new File(imgPath);
			if (f != null && f.exists() && f.isFile()) {
				intent.setType("image/jpg");
				Uri u = Uri.fromFile(f);
				intent.putExtra(Intent.EXTRA_STREAM, u);
			}
		}
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, msgTitle);
		intent.putExtra(android.content.Intent.EXTRA_TEXT, msgText);
		intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(android.content.Intent.createChooser(intent, activityTitle));
	}

	public static void tempContainer(String water){
		list.clear();
		list.add(water);
	}

	public static ArrayList<String> getTempContainer(){
		return list;
	}


	public static int changeBackgound(String weather){

		int weatherCode=0;
		if(weather.equals("多云")){weatherCode=0;}
		if(weather.equals("阴")){weatherCode=1;}
		if(weather.equals("晴")){weatherCode=2;}
		if(weather.equals("雨")||weather.equals("小雨")||weather.equals("雷阵雨")||weather.equals("阵雨")){weatherCode=3;}
		if(weather.equals("雪")||weather.equals("小雪")||weather.equals("阵雪")||weather.equals("雨夹雪")||weather.equals("大雪")||weather.equals("中雪")){weatherCode=4;}
		if(!isNight()){
			switch (weatherCode) {
			case 0:
				return R.drawable.duoyun;  
			case 1:
				return R.drawable.yin; 
			case 2:
				return R.drawable.qing; 
			case 3:
				return R.drawable.yu; 
			case 4:
				return R.drawable.xue;
			default:
				return R.drawable.qing;
			}
		}else{
			switch (weatherCode) {

			default:
				return R.drawable.yewan;
			}
		}

	}

	public static int changeBackgound2(String weather){

		int weatherCode=0;
		if(weather.equals("多云")){weatherCode=0;}
		if(weather.equals("阴")){weatherCode=1;}
		if(weather.equals("晴")){weatherCode=2;}
		if(weather.equals("雨")||weather.equals("小雨")||weather.equals("雷阵雨")||weather.equals("阵雨")){weatherCode=3;}
		if(weather.equals("雪")||weather.equals("小雪")||weather.equals("中雪")||weather.equals("阵雪")||weather.equals("雨夹雪")||weather.equals("大雪")){weatherCode=4;}

		switch (weatherCode) {
		case 0:
			return R.drawable.duoyun;  
		case 1:
			return R.drawable.yin; 
		case 2:
			return R.drawable.qing; 
		case 3:
			return R.drawable.yu; 
		case 4:
			return R.drawable.xue;
		default:
			return R.drawable.qing;
		}

	}



public static boolean isNight(){
	Calendar cal = Calendar.getInstance();// 当前日期
	int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
	int minute = cal.get(Calendar.MINUTE);// 获取分钟
	int minuteOfDay = hour * 60 + minute;// 从0:00分开是到目前为止的分钟数
	final int end = 18 * 60;// 结束时间 19:00的分钟数
	if (minuteOfDay >= end) {
		return true;
	}else{
		return false;
	}
}
}
