package com.guan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	public static final int RESULT_FAILED = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Button btnOk = (Button)findViewById(R.id.login_btn_ok);
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText edtName = (EditText)findViewById(R.id.login_edt_name);
				EditText edtPass = (EditText)findViewById(R.id.login_edt_pwd);
				Bundle bundle;
				if("1".equals(edtName.getText().toString()) && "1".equals(edtPass.getText().toString())){
					bundle = new Bundle();
					bundle.putString("ok", "登录成功");
					Intent i = new Intent();
					i.putExtras(bundle);
					setResult(RESULT_OK, i);
				} else {
					bundle = new Bundle();
					bundle.putString("failed", "登录失败");
					Intent i = new Intent();
					i.putExtras(bundle);
					setResult(RESULT_FAILED, i);
				}
				finish();
			}
		});
		
		Button btnCanel = (Button)findViewById(R.id.login_btn_canel);
		btnCanel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putString("cancel", "取消登录");
				Intent i = new Intent();
				i.putExtras(bundle);
				setResult(RESULT_CANCELED, i);
				finish();
			}
		});
	}

	

}
