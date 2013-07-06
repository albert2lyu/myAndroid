package com.guan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final int DIALOG1 = 1;
	private static final int DIALOG2 = 2;
	private static final int DIALOG4 = 4;
	private static final int DIALOG3 = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG1);
			}
		});
		
		Button btn2 = (Button) findViewById(R.id.buttons2);
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG2);
			}
		});

		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG3);
			}
		});
		
		Button btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG4);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG1:
			return buildDialog1(MainActivity.this);
		case DIALOG2:
			return buildDialog2(MainActivity.this);
		case DIALOG3:
			return buildDialog3(MainActivity.this);
		case DIALOG4:
			return buildDialog4(MainActivity.this);
		default:
			return super.onCreateDialog(id);
		}
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		if(id == DIALOG1)
			setTitle("hello world");
	}
	
	private Dialog buildDialog1(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle("两个按钮");
		builder.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("点击了OK");
			}
		});
		builder.setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("点击了cancel");
			}
		});
		return builder.create();
	}

	private Dialog buildDialog2(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle("三个按钮");
		builder.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("ok");
			}
		});
		builder.setNeutralButton(R.string.alert_dialog_info, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("info");
			}
		});
		builder.setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("cancel");
			}
		});
		return builder.create();
	}
	
	private Dialog buildDialog3(Context context){
		LayoutInflater flater = LayoutInflater.from(this);
		final View dialogView = flater.inflate(R.layout.alertdialog_view, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("布局");
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("登录成功");
			}
		});
		builder.setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("取消登录");
			}
		});
		builder.setView(dialogView);
		return builder.create();
	}
	
	private Dialog buildDialog4(Context context){
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle("正在下载");
		dialog.setMessage("请稍后");
		return dialog;
	}
}
