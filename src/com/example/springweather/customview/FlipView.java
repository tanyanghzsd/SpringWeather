package com.example.springweather.customview;

import com.example.springweather.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FlipView extends LinearLayout implements OnClickListener,RotateAnimation.InterpolatedTimeListener{
	private LinearLayout m_first_ll,m_second_ll;
	private LinearLayout view;
	private boolean enableRefresh;
	private View clickView;
	private Context context;

	public FlipView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
	}

	public FlipView(Context context) {
		super(context);
		this.context=context;
	}

	public void initViews(){
		view=(LinearLayout) inflate(context, R.layout.flip_view, null);
		m_first_ll=(LinearLayout) findViewById(R.id.first_ll);
		m_second_ll=(LinearLayout) findViewById(R.id.second_ll);
		m_first_ll.setOnClickListener(this);
		m_second_ll.setOnClickListener(this);
		addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
	}

	public void addViews(LinearLayout ll1,LinearLayout ll2)  
	{  
		m_first_ll=ll1;  
		m_second_ll=ll2;  
		m_first_ll.setOnClickListener(this);  
		m_second_ll.setOnClickListener(this);  
		addView(m_first_ll, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);  
		addView(m_second_ll, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);  
	}

	/** 
	 * flag=0 翻到正面 
	 * flag=1 翻到反面 
	 * @param flag 
	 */  
	public void show(int flag)  
	{  
		enableRefresh = true;  
		RotateAnimation rotateAnim = null;  
		float cX = this.getWidth() / 2.0f;  
		float cY = this.getHeight() / 2.0f;  
		if(flag==0)  
			rotateAnim = new RotateAnimation(cX, cY,  
					RotateAnimation.ROTATE_DECREASE);  
		else if(flag==1)  
			rotateAnim = new RotateAnimation(cX, cY,  
					RotateAnimation.ROTATE_INCREASE);  
		if (rotateAnim != null) {  
			rotateAnim.setInterpolatedTimeListener(this);  
			rotateAnim.setFillAfter(true);  
			this.startAnimation(rotateAnim);  
		}  
	}  
	@Override  
	public void onClick(View v) {  
		Log.d("click:",v.toString());  
		enableRefresh = true;  
		clickView=v;  
		RotateAnimation rotateAnim = null;  
		float cX = this.getWidth() / 2.0f;  
		float cY = this.getHeight() / 2.0f;  
		if (m_first_ll==v) {  
			rotateAnim = new RotateAnimation(cX, cY,  
					RotateAnimation.ROTATE_INCREASE);  
		} else if (m_second_ll == v) {  
			rotateAnim = new RotateAnimation(cX, cY,  
					RotateAnimation.ROTATE_DECREASE);  
		}  

		if (rotateAnim != null) {  
			rotateAnim.setInterpolatedTimeListener(this);  
			rotateAnim.setFillAfter(true);  
			this.startAnimation(rotateAnim);  
		}  
	}  

	@Override  
	public void interpolatedTime(float interpolatedTime) {  
		if (enableRefresh && interpolatedTime > 0.5f) {  
			setHint();  
			enableRefresh = false;  
		}  
	}  

	public void setHint() {  
		if (clickView == m_first_ll) {  
			m_first_ll.setVisibility(View.GONE);  
			m_second_ll.setVisibility(View.VISIBLE);  
		} else if (clickView==m_second_ll) {  
			m_second_ll.setVisibility(View.GONE);  
			m_first_ll.setVisibility(View.VISIBLE);  
		}  

	}  
}  



