package com.example.springweather.model;

import java.io.Serializable;
import java.util.List;

public class Weather implements Serializable{
	private String cityName;
	private String lowTemp;
	private String highTemp;//每日平均温度
	private String weatherStatus;
	private int weekday;
	private String day;

	//各时段温度、天气状况


	//天气贴士
	private String gj;
	private String cl;
	private String cold;
	private String clothes;
	private String beauty;
	private String travel;
	private String uv;
	private String wash_car;

	//天气图标
	private String pic;


	//每三个小时的各个时段的温度、天气状态

	//时段、温度、天气状况
	private String e8thHours,e8thTemp,e8thWeather;
	private String e11thHours,e11thTemp,e11thWeather;
	private String e14thHours,e14thTemp,e14thWeather;
	private String e17thHours,e17thTemp,e17thWeather;
	private String e23thHours,e23thTemp,e23thWeather;
	private String e2rdHours,e2rdTemp,e2rdWeather;
	private String e5thHours,e5thTemp,e5thWeather;

	private List<ThreeHourForcast> e3hourForcast;

	//

	//后面几天的属性
	private int secWeekday;
	private int thdWeekday;
	private int forthWeekday;
	private int fifthWeekday;
	private int sixthWeekday;

	private String secPic;
	private String thdPic;
	private String forthPic;
	private String fifthPic;
	private String sixthPic;

	private String secLowTemp;
	private String thdLowTemp;
	private String forthLowTemp;
	private String fifthLowTemp;
	private String sixthLowTemp;

	private String secHighTemp;
	private String thdHighTemp;
	private String forthHighTemp;
	private String fifthHighTemp;
	private String sixthHighTemp;

	private String secDayWeather;
	private String thdDayWeather;
	private String forthDayWeather;
	private String fifthDayWeather;
	private String sixthDayWeather;


	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLowTemp() {
		return lowTemp;
	}
	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}
	public String getHighTemp() {
		return highTemp;
	}
	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}
	public String getWeatherStatus() {
		return weatherStatus;
	}
	public void setWeatherStatus(String weatherStatus) {
		this.weatherStatus = weatherStatus;
	}
	public int getWeekday() {
		return weekday;
	}
	public void setWeekday(int i) {
		this.weekday = i;
	}
	public String getGj() {
		return gj;
	}
	public void setGj(String gj) {
		this.gj = gj;
	}
	public String getCl() {
		return cl;
	}
	public void setCl(String cl) {
		this.cl = cl;
	}
	public String getCold() {
		return cold;
	}
	public void setCold(String cold) {
		this.cold = cold;
	}
	public String getClothes() {
		return clothes;
	}
	public void setClothes(String clothes) {
		this.clothes = clothes;
	}
	public String getBeauty() {
		return beauty;
	}
	public void setBeauty(String beauty) {
		this.beauty = beauty;
	}
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	public String getUv() {
		return uv;
	}
	public void setUv(String uv) {
		this.uv = uv;
	}
	public String getWash_car() {
		return wash_car;
	}
	public void setWash_car(String wash_car) {
		this.wash_car = wash_car;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getSecWeekday() {
		return secWeekday;
	}
	public void setSecWeekday(int secWeekday) {
		this.secWeekday = secWeekday;
	}
	public int getThdWeekday() {
		return thdWeekday;
	}
	public void setThdWeekday(int thdWeekday) {
		this.thdWeekday = thdWeekday;
	}
	public int getForthWeekday() {
		return forthWeekday;
	}
	public void setForthWeekday(int forthWeekday) {
		this.forthWeekday = forthWeekday;
	}
	public int getFifthWeekday() {
		return fifthWeekday;
	}
	public void setFifthWeekday(int fifthWeekday) {
		this.fifthWeekday = fifthWeekday;
	}
	public int getSixthWeekday() {
		return sixthWeekday;
	}
	public void setSixthWeekday(int sixthWeekday) {
		this.sixthWeekday = sixthWeekday;
	}
	public String getSecPic() {
		return secPic;
	}
	public void setSecPic(String secPic) {
		this.secPic = secPic;
	}
	public String getThdPic() {
		return thdPic;
	}
	public void setThdPic(String thdPic) {
		this.thdPic = thdPic;
	}
	public String getForthPic() {
		return forthPic;
	}
	public void setForthPic(String forthPic) {
		this.forthPic = forthPic;
	}
	public String getFifthPic() {
		return fifthPic;
	}
	public void setFifthPic(String fifthPic) {
		this.fifthPic = fifthPic;
	}
	public String getSixthPic() {
		return sixthPic;
	}
	public void setSixthPic(String sixthPic) {
		this.sixthPic = sixthPic;
	}
	public String getSecLowTemp() {
		return secLowTemp;
	}
	public void setSecLowTemp(String secLowTemp) {
		this.secLowTemp = secLowTemp;
	}
	public String getThdLowTemp() {
		return thdLowTemp;
	}
	public void setThdLowTemp(String thdLowTemp) {
		this.thdLowTemp = thdLowTemp;
	}
	public String getForthLowTemp() {
		return forthLowTemp;
	}
	public void setForthLowTemp(String forthLowTemp) {
		this.forthLowTemp = forthLowTemp;
	}
	public String getFifthLowTemp() {
		return fifthLowTemp;
	}
	public void setFifthLowTemp(String fifthLowTemp) {
		this.fifthLowTemp = fifthLowTemp;
	}
	public String getSixthLowTemp() {
		return sixthLowTemp;
	}
	public void setSixthLowTemp(String sixthLowTemp) {
		this.sixthLowTemp = sixthLowTemp;
	}
	public String getSecHighTemp() {
		return secHighTemp;
	}
	public void setSecHighTemp(String secHighTemp) {
		this.secHighTemp = secHighTemp;
	}
	public String getThdHighTemp() {
		return thdHighTemp;
	}
	public void setThdHighTemp(String thdHighTemp) {
		this.thdHighTemp = thdHighTemp;
	}
	public String getForthHighTemp() {
		return forthHighTemp;
	}
	public void setForthHighTemp(String forthHighTemp) {
		this.forthHighTemp = forthHighTemp;
	}
	public String getFifthHighTemp() {
		return fifthHighTemp;
	}
	public void setFifthHighTemp(String fifthHighTemp) {
		this.fifthHighTemp = fifthHighTemp;
	}
	public String getSixthHighTemp() {
		return sixthHighTemp;
	}
	public void setSixthHighTemp(String sixthHighTemp) {
		this.sixthHighTemp = sixthHighTemp;
	}
	public String getE8thHours() {
		return e8thHours;
	}
	public void setE8thHours(String e8thHours) {
		this.e8thHours = e8thHours;
	}
	public String getE8thTemp() {
		return e8thTemp;
	}
	public void setE8thTemp(String e8thTemp) {
		this.e8thTemp = e8thTemp;
	}
	public String getE8thWeather() {
		return e8thWeather;
	}
	public void setE8thWeather(String e8thWeather) {
		this.e8thWeather = e8thWeather;
	}
	public String getE11thHours() {
		return e11thHours;
	}
	public void setE11thHours(String e11thHours) {
		this.e11thHours = e11thHours;
	}
	public String getE11thTemp() {
		return e11thTemp;
	}
	public void setE11thTemp(String e11thTemp) {
		this.e11thTemp = e11thTemp;
	}
	public String getE11thWeather() {
		return e11thWeather;
	}
	public void setE11thWeather(String e11thWeather) {
		this.e11thWeather = e11thWeather;
	}
	public String getE14thHours() {
		return e14thHours;
	}
	public void setE14thHours(String e14thHours) {
		this.e14thHours = e14thHours;
	}
	public String getE14thTemp() {
		return e14thTemp;
	}
	public void setE14thTemp(String e14thTemp) {
		this.e14thTemp = e14thTemp;
	}
	public String getE14thWeather() {
		return e14thWeather;
	}
	public void setE14thWeather(String e14thWeather) {
		this.e14thWeather = e14thWeather;
	}
	public String getE17thHours() {
		return e17thHours;
	}
	public void setE17thHours(String e17thHours) {
		this.e17thHours = e17thHours;
	}
	public String getE17thTemp() {
		return e17thTemp;
	}
	public void setE17thTemp(String e17thTemp) {
		this.e17thTemp = e17thTemp;
	}
	public String getE17thWeather() {
		return e17thWeather;
	}
	public void setE17thWeather(String e17thWeather) {
		this.e17thWeather = e17thWeather;
	}
	public String getE23thHours() {
		return e23thHours;
	}
	public void setE23thHours(String e23thHours) {
		this.e23thHours = e23thHours;
	}
	public String getE23thTemp() {
		return e23thTemp;
	}
	public void setE23thTemp(String e23thTemp) {
		this.e23thTemp = e23thTemp;
	}
	public String getE23thWeather() {
		return e23thWeather;
	}
	public void setE23thWeather(String e23thWeather) {
		this.e23thWeather = e23thWeather;
	}
	public String getE2rdHours() {
		return e2rdHours;
	}
	public void setE2rdHours(String e2rdHours) {
		this.e2rdHours = e2rdHours;
	}
	public String getE2rdTemp() {
		return e2rdTemp;
	}
	public void setE2rdTemp(String e2rdTemp) {
		this.e2rdTemp = e2rdTemp;
	}
	public String getE2rdWeather() {
		return e2rdWeather;
	}
	public void setE2rdWeather(String e2rdWeather) {
		this.e2rdWeather = e2rdWeather;
	}
	public String getE5thHours() {
		return e5thHours;
	}
	public void setE5thHours(String e5thHours) {
		this.e5thHours = e5thHours;
	}
	public String getE5thTemp() {
		return e5thTemp;
	}
	public void setE5thTemp(String e5thTemp) {
		this.e5thTemp = e5thTemp;
	}
	public String getE5thWeather() {
		return e5thWeather;
	}
	public void setE5thWeather(String e5thWeather) {
		this.e5thWeather = e5thWeather;
	}
	public List<ThreeHourForcast> getE3hourForcast() {
		return e3hourForcast;
	}
	public void setE3hourForcast(List<ThreeHourForcast> e3hourForcast) {
		this.e3hourForcast = e3hourForcast;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

	public String getSecDayWeather() {
		return secDayWeather;
	}
	public void setSecDayWeather(String secDayWeather) {
		this.secDayWeather = secDayWeather;
	}
	public String getThdDayWeather() {
		return thdDayWeather;
	}
	public void setThdDayWeather(String thdDayWeather) {
		this.thdDayWeather = thdDayWeather;
	}
	public String getForthDayWeather() {
		return forthDayWeather;
	}
	public void setForthDayWeather(String forthDayWeather) {
		this.forthDayWeather = forthDayWeather;
	}
	public String getFifthDayWeather() {
		return fifthDayWeather;
	}
	public void setFifthDayWeather(String fifthDayWeather) {
		this.fifthDayWeather = fifthDayWeather;
	}
	public String getSixthDayWeather() {
		return sixthDayWeather;
	}
	public void setSixthDayWeather(String sixthDayWeather) {
		this.sixthDayWeather = sixthDayWeather;
	}




}
