package com.guan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Integer[] imgs = {R.drawable.image01, R.drawable.image02, R.drawable.image03, 
				R.drawable.image04, R.drawable.image05};
		ImageAdapter adapter = new ImageAdapter(this, imgs);
		adapter.createReflectedImages();
		GalleryFlow galleryfolw = (GalleryFlow)findViewById(R.id.gallery_demo);
		galleryfolw.setFadingEdgeLength(0);
		galleryfolw.setSpacing(0);
		galleryfolw.setAdapter(adapter);
		galleryfolw.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
			}
		});
		galleryfolw.setSelection(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
