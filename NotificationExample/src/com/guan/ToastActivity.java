package com.guan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast);
		Button btn;
		btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast(Toast.LENGTH_SHORT);
			}
		});
		
		btn = (Button)findViewById(R.id.button2);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast(Toast.LENGTH_LONG);
			}
		});
	}
	
	private void showToast(int type){
		View view = inflateView(R.layout.toast);
		TextView textView = (TextView)view.findViewById(R.id.content);
		textView.setText("wakaka");
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
	}
	
	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}
}
