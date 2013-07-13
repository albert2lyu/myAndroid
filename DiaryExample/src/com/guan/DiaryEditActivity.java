package com.guan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DiaryEditActivity extends Activity {

	private DiaryDbHelper _dbHelper;
	private EditText edtTitle;
	private EditText edtBody;
	private Long id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary_edit);
		_dbHelper = new DiaryDbHelper(this);
		_dbHelper.open();
		
		edtTitle = (EditText)findViewById(R.id.title);
		edtBody = (EditText)findViewById(R.id.body);
		
		Button confirm = (Button)findViewById(R.id.confirm);
		
		Bundle b = getIntent().getExtras();
		id = null;
		if(b != null){
			id = b.getLong(DiaryDbHelper.TABLE_COL_ID);
			String title = b.getString(DiaryDbHelper.TABLE_COL_TITLE);
			String body = b.getString(DiaryDbHelper.TABLE_COL_BODY);
			if(title != null) edtTitle.setText(title);
			if(body != null) edtBody.setText(body);
		}
		
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String title = edtTitle.getText().toString();
				String body = edtBody.getText().toString();
				if(id != null){
					_dbHelper.updateDiary(id, title, body);
				} else {
					_dbHelper.createDiary(title, body);
				}
				Intent i = new Intent();
				setResult(RESULT_OK, i);
				finish();
			}
		});
	}

}
