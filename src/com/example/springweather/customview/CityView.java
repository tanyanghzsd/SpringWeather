package com.example.springweather.customview;

import com.example.springweather.R;
import com.example.springweather.model.DatabaseUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class CityView extends LinearLayout implements OnClickListener,OnItemLongClickListener{
	private ListView lv;
	private Button btnAdd;
	private EditText etSearch;
	private ArrayAdapter<String> adapter;
	private SharedPreferences.Editor editor;

	public CityView(Context context) {
		super(context);
	}

	public CityView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();

		adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
		lv.setAdapter(adapter);
		lv.setOnItemLongClickListener(this);

		lv.setItemChecked(0, true);
		btnAdd.setOnClickListener(this);


		readSaved();
	}



	private void readSaved(){
		SharedPreferences pref=getContext().getSharedPreferences("CityView", Context.MODE_PRIVATE);
		String content=pref.getString("city", "");
		if(content.length()!=0){
			for(String str:content.split(",")){
				adapter.add(str);
			}
		}
	}
	private void initView(){
		lv=(ListView) findViewById(android.R.id.list);
		btnAdd=(Button) findViewById(R.id.btn_add);
		etSearch=(EditText) findViewById(R.id.et_search);
	}

	@Override
	public void onClick(View view) {
		boolean isValid=true;
		if(!TextUtils.isEmpty(etSearch.getText().toString())){
			String cityName = etSearch.getText().toString().trim();
			if(DatabaseUtil.isCityValid(getContext(), cityName)){
				for(int i=0;i<adapter.getCount();i++){
					if(cityName.equals(adapter.getItem(i))){
						Toast.makeText(getContext(), "不能重复添加！", 1).show();
						isValid=false;
						break;
					}
				}
				if(isValid){
					adapter.add(cityName);
					refreshSharedPreferences();
					adapter.notifyDataSetChanged();
					Toast.makeText(getContext(), "添加成功！", 1).show();
					etSearch.setText("");
				}

			}else{
				Toast.makeText(getContext(), "没找到您输入的城市！", 1).show();
			}
		}else{
			Toast.makeText(getContext(), "没找到您输入的城市！", 1).show();
		}
	}



	private void refreshSharedPreferences(){
		editor=getContext().getSharedPreferences("CityView", Context.MODE_PRIVATE).edit();
		editor.clear().commit();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<adapter.getCount();i++){
			sb.append(adapter.getItem(i)).append(",");
		}
		if(sb.length()!=0){
			String content=sb.toString().substring(0,sb.length()-1);
			editor.putString("city",content);
			editor.commit();
		}

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long arg3) {
		final CustomDialog dialog=new CustomDialog(getContext(),R.style.MyDialog);
		dialog.show();

		Button btnDelete=(Button)dialog.findViewById(R.id.dialog_btn_delete);
		Button btnSelected=(Button)dialog.findViewById(R.id.dialog_btn_cancle);

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String text=adapter.getItem(position);
				adapter.remove(adapter.getItem(position));
				adapter.notifyDataSetChanged();
				refreshSharedPreferences();
				dialog.dismiss();
			}
		});

		btnSelected.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(position!=0){
					String temp=adapter.getItem(position);
					adapter.remove(adapter.getItem(position));
					adapter.insert(temp, 0);
					refreshSharedPreferences();
				}
				dialog.dismiss();
			}
		});
		return false;
	}
}