package com.guan;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		String[] projs = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, 
				ContactsContract.Contacts.HAS_PHONE_NUMBER, ContactsContract.Contacts.LOOKUP_KEY};
		Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projs, null, null, null);
		startManagingCursor(c);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, 
				new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.LOOKUP_KEY},
				new int[]{android.R.id.text1, android.R.id.text2});
		setListAdapter(adapter);
	}
}
