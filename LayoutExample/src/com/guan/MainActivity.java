package com.guan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	OnClickListener listener4;
	OnClickListener listener1;
	OnClickListener listener2;
	OnClickListener listener3;
	
	Button btn4;
	Button btn1;
	Button btn2;
	Button btn3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listener1 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(MainActivity.this, FrameLayoutActivity.class);
				setTitle("FrameLayout");
				startActivity(intent1);
			}
		};
		listener2 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(MainActivity.this, RelativeActivity.class);
				setTitle("RelativeLayout");
				startActivity(intent2);
			}
		};
		listener3 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent3 = new Intent(MainActivity.this, TableActivity.class);
				setTitle("TableActivity");
				startActivity(intent3);
			}
		};
		listener4 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent4 = new Intent(MainActivity.this, MixActivity.class);
				setTitle("MixActivity");
				startActivity(intent4);
			}
		};
		
		setContentView(R.layout.activity_main);
		
		btn1 = (Button)findViewById(R.id.btn1);
		btn1.setOnClickListener(listener1);
		btn2 = (Button)findViewById(R.id.btn2);
		btn2.setOnClickListener(listener2);
		btn3 = (Button)findViewById(R.id.btn3);
		btn3.setOnClickListener(listener3);
		btn4 = (Button)findViewById(R.id.btn4);
		btn4.setOnClickListener(listener4);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
