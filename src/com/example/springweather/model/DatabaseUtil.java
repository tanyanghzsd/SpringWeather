package com.example.springweather.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.example.springweather.utils.HandleUtil;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseUtil extends SQLiteOpenHelper {
	
	public static final String CREATE_CITYLIST = "create table citylist ("
			+ "id integer primary key autoincrement, "
			+ "name text)";
	public static final String CREATE_WEATHER = "create table weather ("
			+ "id integer primary key autoincrement, "
			+ "name text,"
			+ "time text,"
			+ "cl text,"
			+ "beauty text,"
			+ "clothes text,"
			+ "cold text,"
			+ "travel text,"
			+ "uv text,"
			+ "wash_car text,"
			+ "gj text)";
	
	private static final String TABLE_NAME="citylist";
	
	private static final String TABLE_NAME2="weather";
	
	private static DatabaseUtil instance=null;
	
	private static SQLiteDatabase db=null;
	
	private Context mContext;
	
	
	
	
	public static DatabaseUtil getInstance(Context context){
		if(instance==null){
			instance=new DatabaseUtil(context, "CityList", null, 1);
		}
		return instance;
	}
	
	
	public DatabaseUtil(Context context, String name, CursorFactory
			factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CITYLIST);
		db.execSQL(CREATE_WEATHER);
	}
	
	public static void openDatabase(){
		if(db==null){
			db=instance.getWritableDatabase();
		}
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public static void save(Context context,String name){
		db=new DatabaseUtil(context, "CityList", null, 1).getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("name", name);
		db.insert(TABLE_NAME, null, values);
	}
	
	public static boolean isCityValid(Context context,String name){
		db=new DatabaseUtil(context, "CityList", null, 1).getWritableDatabase();
		Cursor cursor=db.query(TABLE_NAME, null, "name=?", new String[]{name}, null, null, null);
		if(cursor.moveToFirst()){
			return true;
		}
		else{
			cursor.close();
			return false;
		}
		
	}
	
	public static void save(Context context,Weather weather){
		db=new DatabaseUtil(context, "Weather", null, 2).getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("name", weather.getCityName());
		values.put("time", weather.getDay());
		values.put("cl", weather.getCl());
		values.put("beauty", weather.getBeauty());
		values.put("clothes", weather.getClothes());
		values.put("cold", weather.getCold());
		values.put("travel", weather.getTravel());
		values.put("uv", weather.getUv());
		values.put("wash_car", weather.getWash_car());
		values.put("gj", weather.getGj());
		db.insert(TABLE_NAME2, null, values);
	}
	
	public static void delete(Context context,City city){
		db=new DatabaseUtil(context, "CityList", null, 1).getWritableDatabase();
		db.delete(TABLE_NAME, "name=?", new String[]{city.getCity()});
	}
	
	public static ArrayList<City> queryAll(){
		ArrayList<City> list=new ArrayList<City>();
		openDatabase();
		Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city=new City();
				String name=cursor.getString(cursor.getColumnIndex("name"));
				city.setCity(name);
				list.add(city);
			}while(cursor.moveToNext());
		}
		cursor.close();
		return list;
	}
	
	public static void update(String name){
		openDatabase();
		ContentValues values=new ContentValues();
		values.put("name", name);
		db.update(TABLE_NAME, values, "name=?", new String[]{name});
	}
	
	public static void update(Context context,Weather weather){
		db=new DatabaseUtil(context, "Weather", null, 2).getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("name", weather.getCityName());
		values.put("time", weather.getDay());
		values.put("cl", weather.getCl());
		values.put("beauty", weather.getBeauty());
		values.put("clothes", weather.getClothes());
		values.put("cold", weather.getCold());
		values.put("travel", weather.getTravel());
		values.put("uv", weather.getUv());
		values.put("wash_car", weather.getWash_car());
		values.put("gj", weather.getGj());
		
		db.update(TABLE_NAME2, values, "name=?", new String[]{weather.getCityName()});
	}
	
	public static Weather query(Context context){
		Weather weather=new Weather();
		db=new DatabaseUtil(context, "Weather", null, 2).getWritableDatabase();
		Cursor cursor=db.query(TABLE_NAME2, null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				weather.setCityName(cursor.getString(cursor.getColumnIndex("name")));
				weather.setDay(cursor.getString(cursor.getColumnIndex("time")));
				weather.setCl(cursor.getString(cursor.getColumnIndex("cl")));
				weather.setClothes(cursor.getString(cursor.getColumnIndex("clothes")));
				weather.setCold(cursor.getString(cursor.getColumnIndex("cold")));
				weather.setBeauty(cursor.getString(cursor.getColumnIndex("beauty")));
				weather.setUv(cursor.getString(cursor.getColumnIndex("uv")));
				weather.setTravel(cursor.getString(cursor.getColumnIndex("travel")));
				weather.setGj(cursor.getString(cursor.getColumnIndex("gj")));
			}while(cursor.moveToNext());
		}
		cursor.close();
		return weather;
	}
	
	public static void delete(Context context,Weather weather){
		db=new DatabaseUtil(context, "Weather", null, 2).getWritableDatabase();
		db.delete(TABLE_NAME2, "time=?", new String[]{weather.getDay()});
	}
	
	public static void removeDuplicate(City city){
		openDatabase();
		
	}
	

}


