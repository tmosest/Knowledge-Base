package com.tylermoses.grexsampleapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLLiteExample extends Activity implements OnClickListener{
	//declare variables
	Button sqlUpdate, sqlView, getInfo, edit, delete;
	EditText name, hotness, rowIDEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// set content view
		setContentView(R.layout.sqliteexample);
		// set view items
		sqlUpdate = (Button)findViewById(R.id.bSQLUpdate);
		sqlView = (Button)findViewById(R.id.bSQLopenView);
		edit = (Button)findViewById(R.id.bSQLEdit);
		delete = (Button)findViewById(R.id.bSQLDelete);
		getInfo = (Button)findViewById(R.id.bSQLGetInfo);
		name = (EditText)findViewById(R.id.etSQLName);
		hotness = (EditText)findViewById(R.id.etSQLHotness);
		rowIDEditText = (EditText)findViewById(R.id.etSQLRowID);
		// onClick Listeners
		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		edit.setOnClickListener(this);
		delete.setOnClickListener(this);
		getInfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSQLUpdate:
			// Update db
			boolean didItWork = true;
			try{
			String hername = name.getText().toString() ;
			String herHot = hotness.getText().toString();
			
			HotOrNot entry = new HotOrNot(SQLLiteExample.this);
			entry.open();
			entry.createEntry(hername, herHot);
			entry.close();
			} catch (Exception e) {
				didItWork = false;
				Dialog d = new Dialog(this);
				String errorString = e.toString();
				d.setTitle("Heck Yeah!");
				TextView tv = new TextView(this);
				tv.setText(errorString);
				d.setContentView(tv);
				d.show();
			} finally {
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yeah!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
			
		case R.id.bSQLopenView:
			// View db
			Intent i = new Intent("com.example.HelloWorld.SQLView");
			startActivity(i);
			break;
			
		case R.id.bSQLEdit:
			try {
			String hername = name.getText().toString() ;
			String herHot = hotness.getText().toString();
			String sRow = rowIDEditText.getText().toString();
			long lRow = Long.parseLong(sRow);
			
			HotOrNot ex = new HotOrNot(this);
			ex.open();
			ex.editEntry(lRow, hername, herHot);
			ex.close();
			
			} catch (Exception e) {
				didItWork = false;
				Dialog d = new Dialog(this);
				String errorString = e.toString();
				d.setTitle("Heck Yeah!");
				TextView tv = new TextView(this);
				tv.setText(errorString);
				d.setContentView(tv);
				d.show();
			}
			
			break;
		
		case R.id.bSQLDelete:
			try{
				String sRow1 = rowIDEditText.getText().toString();
				long lRow1 = Long.parseLong(sRow1);
				HotOrNot ex1 = new HotOrNot(this);
				ex1.open();
				ex1.deleteEntry(lRow1);
				ex1.close();
			} catch (Exception e) {
				didItWork = false;
				Dialog d = new Dialog(this);
				String errorString = e.toString();
				d.setTitle("Heck Yeah!");
				TextView tv = new TextView(this);
				tv.setText(errorString);
				d.setContentView(tv);
				d.show();
			}
			break;
			
		case R.id.bSQLGetInfo:
			try{
				// input string
				String s = rowIDEditText.getText().toString();
				// convert to long
				long l = Long.parseLong(s);
				HotOrNot hon = new HotOrNot(this);
				hon.open();
				String returnedName = hon.getNAme(l);
				String returnedHotness = hon.getHotness(l);
				hon.close();
				
				name.setText(returnedName);
				hotness.setText(returnedHotness);
			} catch (Exception e) {
				didItWork = false;
				Dialog d = new Dialog(this);
				String errorString = e.toString();
				d.setTitle("Heck Yeah!");
				TextView tv = new TextView(this);
				tv.setText(errorString);
				d.setContentView(tv);
				d.show();
			}
			break;
		}
	}
	
	
}
