package com.example.springweather.customview;

import java.util.ArrayList;
import java.util.List;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.example.springweather.R;
import com.example.springweather.model.DatabaseUtil;
import com.example.springweather.model.ThreeHourForcast;
import com.example.springweather.model.Weather;
import com.example.springweather.utils.HandleUtil;
import com.example.springweather.utils.net.HttpUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainView extends LinearLayout implements OnClickListener{

	private TextView tvCityName,tvTemp,tvLHTemp;
	private MainMiddleView mainMiddle1,mainMiddle2,mainMiddle3,mainMiddle4,mainMiddle5;
	private Button btnRefresh;
	private SplineChartView chart;
	private LinearLayout mainHeaderLayout;

	private ProgressDialog progressDialog;
	private Weather weather;

	LinearLayout firstLL,secondLL;  
	LinearLayout root;  

	public Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case 0:
				Weather weather=(Weather) msg.getData().get("weather");
				DatabaseUtil.update(getContext(), weather);
				Drawable[] array=(Drawable[]) msg.obj;

				//header
				tvCityName.setText(weather.getCityName());
				tvTemp.setText(weather.getHighTemp()+"°");
				String weatherStatus=weather.getWeatherStatus();
				//设置动态背景

				int id=HandleUtil.changeBackgound(weatherStatus);
				mainHeaderLayout.setBackgroundResource(id);


				tvLHTemp.setText(weather.getWeatherStatus()+" "+weather.getLowTemp()+"°"+"~"+weather.getHighTemp()+"°");

				//middle
				mainMiddle1.setFirstTextViewText("明天");
				mainMiddle2.setFirstTextViewText("后天");
				mainMiddle3.setFirstTextViewText(HttpUtil.stringToWeekday(weather.getForthWeekday()));
				mainMiddle4.setFirstTextViewText(HttpUtil.stringToWeekday(weather.getFifthWeekday()));
				mainMiddle5.setFirstTextViewText(HttpUtil.stringToWeekday(weather.getSixthWeekday()));

				mainMiddle1.setSecondTextViewText(weather.getSecHighTemp()+"°"+"/"+weather.getSecLowTemp()+"°");
				mainMiddle2.setSecondTextViewText(weather.getThdHighTemp()+"°"+"/"+weather.getThdLowTemp()+"°");
				mainMiddle3.setSecondTextViewText(weather.getForthHighTemp()+"°"+"/"+weather.getForthLowTemp()+"°");
				mainMiddle4.setSecondTextViewText(weather.getFifthHighTemp()+"°"+"/"+weather.getFifthLowTemp()+"°");
				mainMiddle5.setSecondTextViewText(weather.getSixthHighTemp()+"°"+"/"+weather.getSixthLowTemp()+"°");


				mainMiddle1.setBackgroundDrawable(array[0]);
				mainMiddle2.setBackgroundDrawable(array[1]);
				mainMiddle3.setBackgroundDrawable(array[2]);
				mainMiddle4.setBackgroundDrawable(array[3]);
				mainMiddle5.setBackgroundDrawable(array[4]);


				mainMiddle1.setBackGround(HandleUtil.changeBackgound2(weather.getSecDayWeather()));
				mainMiddle2.setBackGround(HandleUtil.changeBackgound2(weather.getThdDayWeather()));
				mainMiddle3.setBackGround(HandleUtil.changeBackgound2(weather.getForthDayWeather()));
				mainMiddle4.setBackGround(HandleUtil.changeBackgound2(weather.getFifthDayWeather()));
				mainMiddle5.setBackGround(HandleUtil.changeBackgound2(weather.getSixthDayWeather()));


				//设置温度曲线表
				List<ThreeHourForcast> list=weather.getE3hourForcast();

				List<String> weathers=new ArrayList<String>();
				List<String> temps1=new ArrayList<String>();
				List<String> temps2=new ArrayList<String>();
				for(int i=0;i<list.size();i++){
					weathers.add(list.get(i).getWeather());
					temps1.add(list.get(i).getTemperature());
					temps2.add("\n"+"\n"+"  "+list.get(i).getTemperature()+"°");
				}

				chart.refreshChart();
				chart.setChartLabels(weathers);
				chart.setChartDataSet(temps1);
				chart.setChartAnchor(temps2);


				if(progressDialog!=null && progressDialog.isShowing()){
					progressDialog.dismiss();
				}

				break;
			case 1:
				load();
				break;
			case 2:
				btnRefresh.setBackgroundResource(R.drawable.ic_sync_white_24dp);

				break;
			default:
				break;
			}

		};
	};





	public MainView(Context context) {
		super(context);
	}

	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
		load();
		btnRefresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				load();
				btnRefresh.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rotate_btn_refresh));
				chart.refreshChart();
			}
		});

	}



	private void load() {
		String strs=getContext().getSharedPreferences("CityView", Context.MODE_PRIVATE).getString("city", "");
		if(!TextUtils.isEmpty(strs)){
			String[] addressArray=strs.split(",");
			getData(addressArray[0]);
		}
		else{

			final CustomDialog2 dialog=new CustomDialog2(getContext(),R.style.MyDialog);
			dialog.show();

		}
	}

	private void initView() {

		//header
		tvCityName=(TextView)findViewById(R.id.main_header_tv_cityname);
		tvLHTemp=(TextView)findViewById(R.id.main_header_tv_lowhightemp);
		tvTemp=(TextView)findViewById(R.id.main_header_tv_temp);
		btnRefresh=(Button) findViewById(R.id.btn_refresh);

		//middle
		mainMiddle1=(MainMiddleView) findViewById(R.id.middle1);
		mainMiddle2=(MainMiddleView) findViewById(R.id.middle2);
		mainMiddle3=(MainMiddleView) findViewById(R.id.middle3);
		mainMiddle4=(MainMiddleView) findViewById(R.id.middle4);
		mainMiddle5=(MainMiddleView) findViewById(R.id.middle5);

		//chart
		chart=(SplineChartView) findViewById(R.id.splinechart);

		mainHeaderLayout=(LinearLayout) findViewById(R.id.main_header_layout);

	}


	private void getData(String address){

		com.baidu.apistore.sdk.network.Parameters para=new com.baidu.apistore.sdk.network.Parameters();
		para.put("area", address);

		para.put("needIndex", "1");
		para.put("needMoreDay", "1");
		para.put("need3HourForcast", "1");

		ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/weather_showapi/address", 
				ApiStoreSDK.GET,
				para, 
				new ApiCallBack() {
			@Override
			public void onSuccess(int status, final String responseString) {
				Log.i("sdkdemo", "onSuccess");

				//根据得到的responseString获取所需要的信息
				weather=HttpUtil.getWeatherInfo(responseString);
				Weather temp=DatabaseUtil.query(getContext());
				if(temp!=null){
					if(weather.getCityName().equals(temp.getCityName())){
						DatabaseUtil.update(getContext(), weather);
					}
					else{
						DatabaseUtil.delete(getContext(), temp);
						DatabaseUtil.save(getContext(), weather);
					}
				}
				DatabaseUtil.save(getContext(), weather);

				//创建一个加载页面
				progressDialog=ProgressDialog.show(getContext(), null, "正在加载...");

				new Thread(new Runnable() {
					@Override
					public void run() {

						Drawable[] array=new Drawable[5];
						//从网络下载图片	
						array[0]=HttpUtil.loadImageFromNetwork(weather.getSecPic());
						array[1]=HttpUtil.loadImageFromNetwork(weather.getThdPic());
						array[2]=HttpUtil.loadImageFromNetwork(weather.getForthPic());
						array[3]=HttpUtil.loadImageFromNetwork(weather.getFifthPic());
						array[4]=HttpUtil.loadImageFromNetwork(weather.getSixthPic());

						Message message=new Message();
						Bundle bundle=new Bundle();
						bundle.putSerializable("weather", weather);
						message.setData(bundle);
						message.obj=array;
						message.arg1=0;
						handler.sendMessage(message);
					}
				}).start();
			}

			@Override
			public void onComplete() {
				Log.i("sdkdemo", "onComplete");
			}

			@Override
			public void onError(int status, String responseString, Exception e) {
				Log.i("sdkdemo", "onError, status: " + status);
				Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
			}  

		});	 
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		if(visibility==View.VISIBLE){
			if(TextUtils.isEmpty(getContext().getSharedPreferences("CityView", Context.MODE_PRIVATE).getString("city", "")))
			{
				btnRefresh.setBackgroundResource(R.drawable.ic_sync_black_24dp);
				Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_btnrefresh);
				btnRefresh.startAnimation(loadAnimation);
				loadAnimation.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation arg0) {

					}

					@Override
					public void onAnimationRepeat(Animation arg0) {

					}

					@Override
					public void onAnimationEnd(Animation arg0) {
						//btnRefresh.setBackgroundResource(R.drawable.ic_sync_white_24dp);
					}
				});
			}

		}
	}





}
