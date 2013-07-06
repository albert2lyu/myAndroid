package com.guan;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationActivity extends Activity {

	private NotificationManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
		Button btn;
		manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
		btn = (Button)findViewById(R.id.sun_1);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setWeather("晴空万里", "天气预报", "晴空万里1", R.drawable.sun);
			}
		});
		
		btn = (Button)findViewById(R.id.defaultSound);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDefault(Notification.DEFAULT_SOUND);
			}
		});
		
		btn = (Button)findViewById(R.id.clear);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				manager.cancel(0);
			}
		});
	}
	
	private void setWeather(String tickertext, String title, String content, int drawable){
		Notification notification = new Notification(drawable, tickertext, System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		Intent i = new Intent(this, MainActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
		notification.setLatestEventInfo(this, title, content, pi);
		manager.notify(0, notification);
	}
	
	private void setDefault(int defaults){
		Intent i = new Intent(this, MainActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
		Notification notice = new Notification(R.drawable.cloudy, "乌云密布", System.currentTimeMillis());
		notice.flags = Notification.FLAG_AUTO_CANCEL;
//		notice.defaults = Notification.DEFAULT_SOUND;
		notice.defaults = defaults;
		PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
		notice.setLatestEventInfo(this, "天气预报", "乌云密布", pi);
		manager.notify(1, notice);
	}
}
