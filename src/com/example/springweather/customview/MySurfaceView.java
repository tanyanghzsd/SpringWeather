package com.example.springweather.customview;

import com.example.springweather.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

@SuppressLint("WrongCall")
public class MySurfaceView extends SurfaceView implements Callback,Runnable{
	private Context context;
	private SurfaceHolder surfaceHolder;
	private boolean flag=false;
	private Bitmap bitmap_bg;
	
	private float mSurfaceWidth,mSurfaceHeight;
	
	private int mBitposX;
	
	private Canvas canvas;
	
	private Thread thread;
	
	private enum State{
		LEFT,RIGHT
	}
	
	private State state=State.LEFT;
	
	private final int BITMAP_STEP=2;
	
	private int id;
	
	public void setBitmap(int pId){
		bitmap_bg=BitmapFactory.decodeResource(getResources(), pId);
	}
	public int getId(){
		return this.id;
	}
	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		flag=true;
		this.context=context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		surfaceHolder=getHolder();
		surfaceHolder.addCallback(this);
	}

	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	protected void onDraw() {
		drawBG();
		updateBG();
	}
	
	
	public void drawBG() {
		canvas.drawColor(Color.TRANSPARENT,Mode.CLEAR);
		canvas.drawBitmap(bitmap_bg, mBitposX, 0,null);
	}

	public void updateBG(){
		switch (state) {
		case LEFT:
			mBitposX-=BITMAP_STEP;
			break;
		case RIGHT:
			mBitposX+=BITMAP_STEP;
			break;
		default:
			break;
			
		}
		if(mBitposX<=-(bitmap_bg.getWidth()-getWidth())-5){
			state=State.RIGHT;
		}
		if(mBitposX>=0){
			state=State.LEFT;
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mSurfaceWidth=getWidth();
		mSurfaceHeight=getHeight();
		int mWidth=(int)(mSurfaceWidth*3/2);
		
		bitmap_bg=BitmapFactory.decodeResource(getResources(), R.drawable.qing);
		
		thread=new Thread(this);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag=false;
	}

	@Override
	public void run() {
		while(flag){
			synchronized(surfaceHolder){
				canvas=surfaceHolder.lockCanvas();
				onDraw();
				surfaceHolder.unlockCanvasAndPost(canvas);
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
