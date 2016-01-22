package com.example.springweather.customview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.PointD;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.info.AnchorDataPoint;
import org.xclcharts.renderer.plot.PlotGrid;
import org.xclcharts.view.ChartView;
import org.xclcharts.view.GraphicalView;

import com.example.springweather.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

public class SplineChartView extends GraphicalView {

	private SplineChart chart = new SplineChart();
	//分类轴标签集合
	private LinkedList<String> labels = new LinkedList<String>();
	private LinkedList<SplineData> chartData = new LinkedList<SplineData>();
	Paint pToolTip = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public SplineChartView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public SplineChartView(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public SplineChartView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 


	@Override
	public void refreshChart() {
		super.refreshChart();
		labels.clear();
		chartData.clear();
	}
	
	
	 private void initView()
	 {
		 	List<String> weather=new ArrayList<String>();
		 	for(int i=0;i<6;i++){
		 		weather.add("晴");
		 	}
		 	List<String> temps=new ArrayList<String>();
		 	for(int i=0;i<6;i++){
		 		temps.add("1");
		 	}
		 	setChartLabels(weather);
		 	setChartDataSet(temps);
		 	setChartAnchor(temps);
		 	
		 	chartRender();
	 }
	 
	 
	@Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w,h);
    }  				
	
	protected int[] getBarLnDefaultSpadding()
	{
		int [] ltrb = new int[4];
		ltrb[0] = DensityUtil.dip2px(getContext(), 10); //left	
		ltrb[1] = DensityUtil.dip2px(getContext(), 0); //top	
		ltrb[2] = DensityUtil.dip2px(getContext(), 10); //right	
		ltrb[3] = DensityUtil.dip2px(getContext(), 50); //bottom						
		return ltrb;
	}
	private void chartRender()
	{
		try {
						
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
			int [] ltrb = getBarLnDefaultSpadding();
			chart.setPadding(ltrb[0] + DensityUtil.dip2px(this.getContext(), 20), ltrb[1],
						ltrb[2]+DensityUtil.dip2px(this.getContext(), 30), ltrb[3]);	
			
		
			
			
			//显示边框
			chart.showRoundBorder();
			
			//数据源	
			chart.setCategories(labels);
			chart.setDataSource(chartData);
		//	chart.setCustomLines(mCustomLineDataset);
						
			//坐标系
			//数据轴最大值
			chart.getDataAxis().setAxisMax(40);
			chart.getDataAxis().setAxisMin(-40);
			//数据轴刻度间隔
			chart.getDataAxis().setAxisSteps(2);
			
			//标签轴最大值
			chart.setCategoryAxisMax(5);	
			//标签轴最小值
			chart.setCategoryAxisMin(0);	
			
			//chart.setCategoryAxisCustomLines(mXCustomLineDataset); //x轴
			//chart.setCustomLines(mYCustomLineDataset); //y轴
			
			//背景网格
			PlotGrid plot = chart.getPlotGrid();		
			plot.hideHorizontalLines();
			plot.hideVerticalLines();		
			
			
			chart.getPlotArea().setBackgroundColor(true, Color.TRANSPARENT);
		
						
			chart.getCategoryAxis().getAxisPaint().setColor(Color.WHITE);
			chart.getCategoryAxis().getAxisPaint().setTextSize(4);
			chart.getCategoryAxis().hideTickMarks();					
			chart.getCategoryAxis().getTickLabelPaint().setColor(Color.WHITE);	
			chart.getCategoryAxis().getTickLabelPaint().setFakeBoldText(true);
			chart.getCategoryAxis().setTickLabelMargin(25);
			chart.getCategoryAxis().getTickLabelPaint().setTextSize(25);		
			
			//不使用精确计算，忽略Java计算误差,提高性能
			chart.disableHighPrecision();
			
			chart.disablePanMode();
			chart.hideBorder();
			chart.getPlotLegend().hide();
			chart.getDataAxis().hide();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("tag", e.toString());
		}
	}
	
	public void setChartDataSet(List<String> temps)
	{
		//线1的数据集
		List<PointD> linePoint1 = new ArrayList<PointD>();				
		linePoint1.add(new PointD(0d, Double.parseDouble(temps.get(0))));	
		linePoint1.add(new PointD(1d, Double.parseDouble(temps.get(1))));					
		linePoint1.add(new PointD(2d, Double.parseDouble(temps.get(2))));				
		linePoint1.add(new PointD(3d, Double.parseDouble(temps.get(3))));				
		linePoint1.add(new PointD(4d, Double.parseDouble(temps.get(4))));
		linePoint1.add(new PointD(5d, Double.parseDouble(temps.get(5))));
		
		SplineData dataSeries1 = new SplineData("Go",linePoint1,
				Color.WHITE ); 
		//把线弄细点
		dataSeries1.getLinePaint().setStrokeWidth(3);
		dataSeries1.setLineStyle(XEnum.LineStyle.DASH);	
		dataSeries1.setLabelVisible(false);					
		dataSeries1.setDotStyle(XEnum.DotStyle.RING);
		dataSeries1.getDotPaint().setColor(getResources().getColor(R.color.white));				
		dataSeries1.getPlotLine().getPlotDot().setRingInnerColor(getResources().getColor(R.color.transparent));
	
		chartData.add(dataSeries1);	
		this.invalidate();
	}
	
	public void setChartLabels(List<String> weather){
		String[] times={"20:00\n","23:00\n","2:00\n","5:00\n","8:00\n","11:00\n"};
		for(int i=0;i<weather.size();i++){
			labels.add(times[i]+weather.get(i));
		}
		this.invalidate();
	}
	
	
	public void setChartAnchor(List<String> temps){
		//激活点击监听
				chart.ActiveListenItemClick();
				//为了让触发更灵敏，可以扩大5px的点击监听范围
				chart.extPointClickRange(5);		
				chart.showClikedFocus();		
								
				//批注
				List<AnchorDataPoint> mAnchorSet = new ArrayList<AnchorDataPoint>();
				
				
				AnchorDataPoint an2 = new AnchorDataPoint(0,0,XEnum.AnchorStyle.TOBOTTOM);
				an2.setBgColor(Color.WHITE);
				an2.setLineWidth(15);
				an2.setLineStyle(XEnum.LineStyle.DASH);
				
				an2.setTextColor(Color.WHITE);
				an2.setTextSize(55);
				an2.setAnchor(temps.get(0));
				
				
				AnchorDataPoint an3 = new AnchorDataPoint(0,1,XEnum.AnchorStyle.TOBOTTOM);
				an3.setBgColor(Color.WHITE);
				an3.setLineStyle(XEnum.LineStyle.DASH);
				an3.setTextColor(Color.WHITE);
				an3.setTextSize(55);
				an3.setAnchor(temps.get(1));
				
				//从点到底的标识线
				//从点到底的标识线
				AnchorDataPoint an4 = new AnchorDataPoint(0,2,XEnum.AnchorStyle.TOBOTTOM);
				an4.setBgColor(Color.WHITE);
				an4.setLineWidth(15);
				an4.setLineStyle(XEnum.LineStyle.DASH);
				an4.setTextColor(Color.WHITE);
				an4.setTextSize(55);
				an4.setAnchor(temps.get(2));
				
				AnchorDataPoint an5 = new AnchorDataPoint(0,3,XEnum.AnchorStyle.TOBOTTOM);
				an5.setBgColor(Color.WHITE);
				an5.setLineWidth(15);
				an5.setLineStyle(XEnum.LineStyle.DASH);
				an5.setTextColor(Color.WHITE);
				an5.setTextSize(55);
				an5.setAnchor(temps.get(3));
				
				AnchorDataPoint an6 = new AnchorDataPoint(0,4,XEnum.AnchorStyle.TOBOTTOM);
				an6.setBgColor(Color.WHITE);
				an6.setLineWidth(15);
				an6.setLineStyle(XEnum.LineStyle.DASH);
				an6.setTextColor(Color.WHITE);
				an6.setTextSize(55);
				an6.setAnchor(temps.get(4));
				
				AnchorDataPoint an7 = new AnchorDataPoint(0,5,XEnum.AnchorStyle.TOBOTTOM);
				an7.setBgColor(Color.WHITE);
				an7.setLineWidth(15);
				an7.setLineStyle(XEnum.LineStyle.DASH);
				an7.setTextColor(Color.WHITE);
				an7.setTextSize(55);
				an7.setAnchor(temps.get(5));
			
				mAnchorSet.add(an2);
				mAnchorSet.add(an3);
				mAnchorSet.add(an4);
				mAnchorSet.add(an5);
				mAnchorSet.add(an6);
				mAnchorSet.add(an7);
				chart.setAnchorDataPoint(mAnchorSet);		
				
				this.invalidate();
	}
	


	@Override
    public void render(Canvas canvas) {
        try{
        	canvas.drawColor(Color.TRANSPARENT);
            chart.render(canvas);
        } catch (Exception e){
        	Log.e("tag", e.toString());
        }
    }

	
	
	
	
	
	
	
}
