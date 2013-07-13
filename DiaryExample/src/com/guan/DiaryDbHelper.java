package com.guan;

import java.util.Calendar;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDbHelper {

	private static final String DATABASE_NAME = "diary_database";
	private static final int DATABASE_VERSION = 3;
	private static final String TABLE_NAME = "t_diary";
	public static final String TABLE_COL_ID = "_id";
	public static final String TABLE_COL_TITLE = "d_title";
	public static final String TABLE_COL_BODY = "d_body";
	public static final String TABLE_COL_CREATEDATE = "d_createdate";
	private static final String CREATE_TABLE_SQL = "create table t_diary (_id integer primary key autoincrement," +
			"d_title nvarchar(500), d_body text, d_createdate nchar(10));";
	
	private final Context _context;
	private DbHelper _dbHelper;
	private SQLiteDatabase _db;
	
	public DiaryDbHelper(Context context){
		_context = context;
	}
	
	private static class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_SQL);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + TABLE_NAME);
			onCreate(db);
		}
	}
	
	public DiaryDbHelper open(){
		_dbHelper = new DbHelper(_context);
		_db = _dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		_dbHelper.close();
	}
	
	public long createDiary(String title, String body){
		ContentValues initValues = new ContentValues();
		initValues.put(TABLE_COL_TITLE, title);
		initValues.put(TABLE_COL_BODY, body);	  
		initValues.put(TABLE_COL_CREATEDATE, getCreateDate());
		return _db.insert(TABLE_NAME, null, initValues);
	}
	
	public boolean deleteDiary(long id){
		return _db.delete(TABLE_NAME, TABLE_COL_ID + "=" + id, null) > 0;
	}
	
	public Cursor getAllDiarys(){
		return _db.query(TABLE_NAME, new String[]{TABLE_COL_ID, TABLE_COL_TITLE, 
				TABLE_COL_BODY, TABLE_COL_CREATEDATE}, null, null, null, null, null);
	}
	
	public Cursor getDiary(long id){
		Cursor cursor = _db.query(TABLE_NAME, new String[]{TABLE_COL_ID, TABLE_COL_TITLE, 
				TABLE_COL_BODY, TABLE_COL_CREATEDATE}, TABLE_COL_ID + "=" + id, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		return cursor;
	}
	
	public boolean updateDiary(long id, String title, String body){
		ContentValues initValues = new ContentValues();
		initValues.put(TABLE_COL_TITLE, title);
		initValues.put(TABLE_COL_BODY, body);
		initValues.put(TABLE_COL_CREATEDATE, getCreateDate());
		return _db.update(TABLE_NAME, initValues, TABLE_COL_ID + "=" + id, null) > 0;
	}
	
	private String getCreateDate(){
		Calendar cal = Calendar.getInstance();
		return String.format(Locale.getDefault(), "%d-%d-%d", new Object[]{cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)});
	}
}
