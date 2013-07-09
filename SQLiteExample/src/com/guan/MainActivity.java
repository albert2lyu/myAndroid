package com.guan;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String DATABASE_NAME = "SQLiteExampleDB";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "SQLiteExample";
	private static final String CREATE_SQL = "create table SQLiteExample (t_name nchar(20), t_info nvarchar(200));";
	private static final String DROP_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
	private static final String INSERT_SQL = "insert into " + TABLE_NAME + " (t_name, t_info) values('%s', '%s');";
	
	private DataBaseHelper dbHelp;

	private static class DataBaseHelper extends SQLiteOpenHelper {

		public DataBaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i("guan:table", "create table");
			db.execSQL(CREATE_SQL);
			Log.i("guan:table", "create table success");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelp = new DataBaseHelper(this, DATABASE_NAME, null, DATABASE_VERSION);
		Button btn;
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createTable();
			}
		});
		
		btn = (Button)findViewById(R.id.button2);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dropTable();
			}
		});
		
		btn = (Button)findViewById(R.id.button3);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				insertItem();
			}
		});
		
		btn = (Button)findViewById(R.id.button4);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteItem();
			}
		});
		
		btn = (Button)findViewById(R.id.button5);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showItems();
			}
		});
	}

	protected void showItems() {
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		String[] cols = {"t_name", "t_info"};
		Cursor cursor = db.query(TABLE_NAME, cols, null, null, null, null, null);
		int counts = cursor.getCount();
		cursor.close();
		showToast(counts + " rows");
	}

	protected void deleteItem() {
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		Log.i("guan:table", "delete table");
		db.delete(TABLE_NAME, "t_name='guanguan241'", null);
		showToast("delete table success");
		Log.i("guan:table", "delete table success");
	}

	protected void insertItem() {
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		String sql1 = String.format(INSERT_SQL, new Object[]{"guanguan", "android真是好东东"});
		String sql2 = String.format(INSERT_SQL, new Object[]{"guanguan241", "android真不错啊"});
		Log.i("guan:table", "insert table");
		db.execSQL(sql1);
		db.execSQL(sql2);
		showToast("insert table success");
		Log.i("guan:table", "insert table success");
	}

	protected void dropTable() {
		Log.i("guan:table", "drop table");
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		db.execSQL(DROP_SQL);
		showToast("drop table success");
		Log.i("guan:table", "drop table success");
	}

	protected void createTable() {
		Log.i("guan:table", "create table");
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		try {
			db.execSQL(DROP_SQL);
			db.execSQL(CREATE_SQL);
			showToast("repeat create table success");
			Log.i("guan:table", "repeat create table success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
