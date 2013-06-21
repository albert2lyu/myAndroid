package com.guan;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class TextViewActivity extends Activity {

	TextView txtView;
	
	CheckBox checkBox1;
	CheckBox checkBox2;
	CheckBox checkBox3;
	
	RadioGroup radioGroup;
	RadioButton radioButton;
	
	Spinner spinner;
	ArrayAdapter<CharSequence> adapter;
	
	private static final String[] autoCompletes = new String[]{"myAndroid", "youText", "sheEdit", "heView"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_view);
		txtView = (TextView)findViewById(R.id.textview_textview_changetext);
		txtView.setOnClickListener(click_Listener);
		
		EditText edt = (EditText)findViewById(R.id.textview_edittext_inputtext);
		edt.addTextChangedListener(txtWatcher);
		
		checkBox1 = (CheckBox)findViewById(R.id.textview_checkbox_one);
		checkBox2 = (CheckBox)findViewById(R.id.textview_checkbox_two);
		checkBox3 = (CheckBox)findViewById(R.id.textview_checkbox_three);
		
		radioGroup = (RadioGroup)findViewById(R.id.textview_radioGroup_radios);
		radioButton = (RadioButton)findViewById(R.id.textview_radioGroup_one);
		
		spinner = (Spinner)findViewById(R.id.textview_spinner_countries);
		adapter = ArrayAdapter.createFromResource(this, R.array.countries, 
				android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setSelection(0, true);
		spinner.setOnItemSelectedListener(itemSelect);
		
		AutoCompleteTextView autoComplete = (AutoCompleteTextView)findViewById(R.id.textview_autocomplete_text);
		ArrayAdapter<CharSequence> autoAdapter = new ArrayAdapter<CharSequence>(this, 
				android.R.layout.simple_list_item_1, autoCompletes);
		autoComplete.setAdapter(autoAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_view, menu);
		return true;
	}
	
	private OnClickListener click_Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			CharSequence txt = txtView.getText();
			txtView.setText(txt + getString(R.string.textview_textview_newtvalue));
		}
	};
	
	private TextWatcher txtWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			txtView.setText(s);
			if(checkBox1.isChecked())
				txtView.append(checkBox1.getText());
			if(checkBox2.isChecked())
				txtView.append(checkBox2.getText());
			if(checkBox3.isChecked())
				txtView.append(checkBox3.getText());
			radioGroup.clearCheck();
			radioButton.setChecked(true);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			
		}
	};
	
	private OnItemSelectedListener itemSelect = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			txtView.setText(adapter.getItem(position));
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};

}
