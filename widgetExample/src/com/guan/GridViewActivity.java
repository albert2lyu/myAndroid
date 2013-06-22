package com.guan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewActivity extends Activity {

	private int[] imgs = {R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);
		GridView grid = (GridView)findViewById(R.id.gridview);
		grid.setAdapter(new GridViewAdapter());
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(GridViewActivity.this, ImageViewActivity.class);
				intent.putExtra("resId", imgs[position]);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid_view, menu);
		return true;
	}
	
	class GridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return imgs.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imgView;
			if(convertView == null){
				imgView = new ImageView(GridViewActivity.this);
				imgView.setLayoutParams(new android.widget.AbsListView.LayoutParams(85, 85));
				imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imgView.setPadding(8, 8, 8, 8);
			} else {
				imgView = (ImageView)convertView;
			}
			imgView.setImageResource(imgs[position]);
			return imgView;
		}
	}
}
