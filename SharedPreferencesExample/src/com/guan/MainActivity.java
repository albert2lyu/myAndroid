package com.guan;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.EditText;

public class MainActivity extends Activity {

	private static final String LOGIN_SETTINGS = "LOGIN_SETTINGS";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	private EditText edtName;
	private EditText edtPwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edtName = (EditText)findViewById(R.id.name);
		edtPwd = (EditText)findViewById(R.id.password);
		
		SharedPreferences sp = getSharedPreferences(LOGIN_SETTINGS, 0);
		edtName.setText(sp.getString(USERNAME, ""));
		edtPwd.setText(sp.getString(PASSWORD, ""));
	}

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences sp = getSharedPreferences(LOGIN_SETTINGS, 0);
		sp.edit().putString(USERNAME, edtName.getText().toString())
			.putString(PASSWORD, edtPwd.getText().toString()).commit();
	}
}
