package com.guan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TextViewActivity extends Activity {

	private static final int RESULT_FAILED = 2;
	
	private TextView txtView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_view);
		txtView = (TextView)findViewById(R.id.textview_textview);
		Intent i = getIntent();
		txtView.setText(i.getStringExtra("txtViewShowText"));
		
		Button btn = (Button)findViewById(R.id.textview_btn_toLogin);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(TextViewActivity.this, LoginActivity.class);
				startActivityForResult(i, RESULT_FIRST_USER);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == RESULT_FIRST_USER){
			Bundle b = data.getExtras();
			switch (resultCode) {
			case RESULT_OK:
				txtView.setText(b.getString("ok"));
				break;
			case RESULT_FAILED:
				txtView.setText(b.getString("failed"));
				break;
			case RESULT_CANCELED:	
				txtView.setText(b.getString("cancel"));
			default:
				break;
			}
		}
	}

	

}
