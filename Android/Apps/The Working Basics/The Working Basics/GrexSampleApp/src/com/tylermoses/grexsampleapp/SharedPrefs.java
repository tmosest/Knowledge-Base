package com.tylermoses.grexsampleapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener{
	
	//Declare Variables
	EditText input;
	Button save,load;
	TextView data;
	public static String prefsFileName = "MySharedString";
	SharedPreferences someData;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
		someData = getSharedPreferences(prefsFileName, 0);
	}

	private void setupVariables() {
		// TODO Auto-generated method stub
		save = (Button)findViewById(R.id.bSave);
		load = (Button)findViewById(R.id.bLoad);
		input = (EditText)findViewById(R.id.etSharedPreferences);
		data = (TextView)findViewById(R.id.tvDataLoad);
		
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSave:
			String stringData = input.getText().toString();
			editor = someData.edit();
			editor.putString("sharedString", stringData);
			editor.commit();
			break;
		case R.id.bLoad:
			someData = getSharedPreferences(prefsFileName, 0);
			String dataReturned = someData.getString("sharedString", "Couldn't Load Data");
			data.setText(dataReturned);
			break;
		}
	}
	
}
