package com.guan;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;

public class TabDemoActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.activity_tab_demo, tabHost.getTabContentView(), true);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1").setContent(R.id.tabview_view1));
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1").setContent(R.id.tabview_view2));
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1").setContent(R.id.tabview_view3));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_demo, menu);
		return true;
	}
}
