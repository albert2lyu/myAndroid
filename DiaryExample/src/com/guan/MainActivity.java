package com.guan;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private static final int DIARY_CREATE = 0;
	private static final int DIARY_EDIT = 1;
	
	private DiaryDbHelper _dbHelper;
	private Cursor _cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_dbHelper = new DiaryDbHelper(this);
		_dbHelper.open();
		initListView();
	}

	private void initListView(){
		_cursor = _dbHelper.getAllDiarys();
		startManagingCursor(_cursor);
		String[] from = new String[]{DiaryDbHelper.TABLE_COL_TITLE, DiaryDbHelper.TABLE_COL_CREATEDATE};
		int[] to = {R.id.text1, R.id.created};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.diary_row, _cursor, from, to);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item.getItemId() == R.id.diary_add){
			createDiary();
		} else if(item.getItemId() == R.id.diary_delete){
			_dbHelper.deleteDiary(getListView().getSelectedItemId());
			initListView();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void createDiary() {
		Intent i = new Intent(this, DiaryEditActivity.class);
		startActivityForResult(i, DIARY_CREATE);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Cursor c = _cursor;
		c.moveToPosition(position);
		Bundle b = new Bundle();
		b.putLong(DiaryDbHelper.TABLE_COL_ID, id);
		b.putString(DiaryDbHelper.TABLE_COL_TITLE, c.getString(c.getColumnIndexOrThrow(DiaryDbHelper.TABLE_COL_TITLE)));
		b.putString(DiaryDbHelper.TABLE_COL_BODY, c.getString(c.getColumnIndexOrThrow(DiaryDbHelper.TABLE_COL_BODY)));
		Intent i = new Intent(this, DiaryEditActivity.class);
		i.putExtras(b);
		startActivityForResult(i, DIARY_EDIT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		initListView();
		if(requestCode == DIARY_CREATE){
			showToast("create diary success");
		} else if(requestCode == DIARY_EDIT){
			showToast("edit diary success");
		}
	}

	private void showToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}
	
}
