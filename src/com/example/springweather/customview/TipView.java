package com.example.springweather.customview;

import com.example.springweather.R;
import com.example.springweather.model.DatabaseUtil;
import com.example.springweather.model.Weather;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

public class TipView extends LinearLayout {
	
	private TextView tvClothes,tvGj,tvSport,tvCold,tvTravel;
	private Weather weather;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				weather=DatabaseUtil.query(getContext());
				tvClothes.setText(weather.getClothes());
				tvGj.setText(weather.getGj());
				tvSport.setText(weather.getCl());
				tvCold.setText(weather.getCold());
				tvTravel.setText(weather.getTravel());
				break;

			default:
				break;
			}
			
		};
	};

	public TipView(Context context) {
		super(context);
	}

	public TipView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
		
		Message msg=new Message();
		msg.what=0;
		handler.sendMessage(msg);
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		if(visibility==View.VISIBLE){
			handler.sendEmptyMessage(0);
		}
	}
	
	private void initView() {
		tvClothes=(TextView)findViewById(R.id.tip_middle_clothing);
		tvGj=(TextView)findViewById(R.id.tip_middle_shopping);
		tvSport=(TextView)findViewById(R.id.tip_middle_sport);
		tvCold=(TextView)findViewById(R.id.tip_middle_cold);
		tvTravel=(TextView)findViewById(R.id.tip_middle_travel);
	}
}
