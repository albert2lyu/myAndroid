package com.guan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnShow = (Button)findViewById(R.id.main_btn_show);
		btnShow.setOnClickListener(btn_listener);
		Button btnTextView = (Button)findViewById(R.id.main_btn_TextView);
		btnTextView.setOnClickListener(btn_listener);
		
		DatePicker date = (DatePicker)findViewById(R.id.main_datepicker);
		date.init(1989, 7, 9, null);
		TimePicker time = (TimePicker)findViewById(R.id.main_timepicker);
		time.setIs24HourView(true);
		
		ImageButton imgButton = (ImageButton)findViewById(R.id.main_imagebutton);
		imgButton.setOnClickListener(btn_listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private OnClickListener btn_listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.main_btn_show)
				setTitle(R.string.main_title_btn_show_click);
			else if(v.getId() == R.id.main_btn_TextView){
				Intent intent = new Intent(MainActivity.this, TextViewActivity.class);
				startActivity(intent);
			} else if(v.getId() == R.id.main_imagebutton){
				setTitle(R.string.main_title_imgbtn_click);
			}
		}
	};
}
