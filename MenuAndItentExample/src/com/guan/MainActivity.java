package com.guan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == R.id.menu_show_textview){
			Intent i = new Intent(this, TextViewActivity.class);
			i.putExtra("txtViewShowText", getString(R.string.textview_textview_showtext));
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

}
