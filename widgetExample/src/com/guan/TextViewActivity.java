package com.guan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TextViewActivity extends Activity {

	TextView txtView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_view);
		txtView = (TextView)findViewById(R.id.textview_textview_changetext);
		txtView.setOnClickListener(click_Listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_view, menu);
		return true;
	}
	
	private OnClickListener click_Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			CharSequence txt = txtView.getText();
			txtView.setText(txt + getString(R.string.textview_textview_newtvalue));
		}
	};

}
