package com.guan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnNotification = (Button)findViewById(R.id.button1);
		btnNotification.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, NotificationActivity.class);
				startActivity(i);
			}
		});
		
		Button btnToast = (Button)findViewById(R.id.button2);
		btnToast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ToastActivity.class);
				startActivity(i);
			}
		});
	}
}
