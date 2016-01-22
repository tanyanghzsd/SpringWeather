package com.example.springweather.utils.net;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.springweather.model.ThreeHourForcast;
import com.example.springweather.model.Weather;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class HttpUtil {



	public static Weather getWeatherInfo(String jsonStr){

		Weather weather=new Weather();
		try {
			JSONObject jb=new JSONObject(jsonStr);
			JSONObject retData=jb.getJSONObject("showapi_res_body");
			JSONObject cityInfo=retData.getJSONObject("cityInfo");
			weather.setCityName(cityInfo.getString("c3"));

			JSONObject f1=retData.getJSONObject("f1");

			weather.setHighTemp(f1.getString("day_air_temperature"));

			weather.setDay(f1.getString("day"));
			
			weather.setWeatherStatus(f1.getString("day_weather"));

			weather.setWeekday(f1.getInt("weekday"));

			weather.setPic(f1.getString("day_weather_pic"));

			JSONObject index=f1.getJSONObject("index");
			weather.setCl(index.getJSONObject("cl").getString("desc"));
			weather.setBeauty(index.getJSONObject("beauty").getString("desc"));
			weather.setClothes(index.getJSONObject("clothes").getString("desc"));
			weather.setCold(index.getJSONObject("cold").getString("desc"));
			weather.setTravel(index.getJSONObject("travel").getString("desc"));
			weather.setUv(index.getJSONObject("uv").getString("desc"));
			weather.setWash_car(index.getJSONObject("wash_car").getString("desc"));
			weather.setGj(index.getJSONObject("gj").getString("desc"));
			weather.setLowTemp(f1.getString("night_air_temperature"));

			//三小时温度、天气
			JSONArray p3hourForcast=f1.getJSONArray("3hourForcast");
			List<ThreeHourForcast> list=new ArrayList<ThreeHourForcast>();
			for (int i = 0; i < p3hourForcast.length()-2; i++) {
				ThreeHourForcast forcast=new ThreeHourForcast();
				JSONObject json=p3hourForcast.getJSONObject(i);
				forcast.setHour(json.getString("hour"));
				forcast.setTemperature(json.getString("temperature"));
				forcast.setWeather(json.getString("weather"));
				list.add(forcast);
			}
			weather.setE3hourForcast(list);


			//second day
			JSONObject f2=retData.getJSONObject("f2");
			weather.setSecWeekday(f2.getInt("weekday"));
			weather.setSecHighTemp(f2.getString("day_air_temperature"));
			weather.setSecLowTemp(f2.getString("night_air_temperature"));
			weather.setSecPic(f2.getString("day_weather_pic"));
			weather.setSecDayWeather(f2.getString("day_weather"));

			//third day
			JSONObject f3=retData.getJSONObject("f3");
			weather.setThdWeekday(f3.getInt("weekday"));
			weather.setThdHighTemp(f3.getString("day_air_temperature"));
			weather.setThdLowTemp(f3.getString("night_air_temperature"));
			weather.setThdPic(f3.getString("day_weather_pic"));
			weather.setThdDayWeather(f3.getString("day_weather"));

			//forth day
			JSONObject f4=retData.getJSONObject("f4");
			weather.setForthWeekday(f4.getInt("weekday"));
			weather.setForthHighTemp(f4.getString("day_air_temperature"));
			weather.setForthLowTemp(f4.getString("night_air_temperature"));
			weather.setForthPic(f4.getString("day_weather_pic"));
			weather.setForthDayWeather(f4.getString("day_weather"));


			//fifth day
			JSONObject f5=retData.getJSONObject("f5");
			weather.setFifthWeekday(f5.getInt("weekday"));
			weather.setFifthHighTemp(f5.getString("day_air_temperature"));
			weather.setFifthLowTemp(f5.getString("night_air_temperature"));
			weather.setFifthPic(f5.getString("day_weather_pic"));
			weather.setFifthDayWeather(f5.getString("day_weather"));
			
			//sixth day
			JSONObject f6=retData.getJSONObject("f6");
			weather.setSixthWeekday(f6.getInt("weekday"));
			weather.setSixthHighTemp(f6.getString("day_air_temperature"));
			weather.setSixthLowTemp(f6.getString("night_air_temperature"));
			weather.setSixthPic(f6.getString("day_weather_pic"));
			weather.setSixthDayWeather(f6.getString("day_weather"));



		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return weather;
	}

	public static String stringToWeekday(int i){
		String str = null;
		switch (i) {
		case 1:
			str="周一";
			break;
		case 2:
			str="周二";
			break;
		case 3:
			str="周三";
			break;
		case 4:
			str="周四";
			break;
		case 5:
			str="周五";
			break;
		case 6:
			str="周六";
			break;
		case 7:
			str="周日";
			break;
		default:
			break;
		}
		return str;
	}

	public static Drawable loadImageFromNetwork(String urladdr) {
		Drawable drawable = null;
		try{
			drawable = Drawable.createFromStream(new URL(urladdr).openStream(), "image.jpg");
		}catch(IOException e){
			e.printStackTrace();
		}
		if(drawable == null){
			Log.d("test","null drawable");
		}else{
			Log.d("test","not null drawable");
		}
		return drawable;
	}
}
