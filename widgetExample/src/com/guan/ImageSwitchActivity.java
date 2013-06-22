package com.guan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class ImageSwitchActivity extends Activity {

	private ImageSwitcher imgSwitch;
	private static int[] imgs = {R.drawable.grid_view_01, R.drawable.grid_view_02, R.drawable.grid_view_03,
			R.drawable.grid_view_04, R.drawable.grid_view_05};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_image_switch);
		
		imgSwitch = (ImageSwitcher)findViewById(R.id.imgswitch_switch);
		imgSwitch.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				ImageView imgView = new ImageView(ImageSwitchActivity.this);
				imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imgView.setBackgroundColor(0xFF000000);
				imgView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
						android.widget.FrameLayout.LayoutParams.MATCH_PARENT));
				return imgView;
			}
		});
		
		Gallery gallery = (Gallery)findViewById(R.id.imgswitch_gallery);
		gallery.setAdapter(new ImageAdapter());
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imgSwitch.setImageResource(imgs[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_switch, menu);
		return true;
	}
	
	class ImageAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return imgs.length;
		}

		@Override
		public Object getItem(int position) {
			return imgs[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imgView = new ImageView(ImageSwitchActivity.this);
			imgView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			imgView.setImageResource(imgs[position]);
			imgView.setAdjustViewBounds(true);
			imgView.setBackgroundResource(R.drawable.picture_frame);
			return imgView;
		}
		
	}

}
