package com.tylermoses.grexsampleapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{

	String classes[] = {"SimpleAdder", "TextPlay", "Email", "Camera", 
			"Data", "GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs", "SimpleBrowser", "Flipper", "SharedPrefs" ,"InternalData"
			, "ExternalData", "SQLLiteExample", "Accelerate"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String local = classes[position];
		try {
			Class ourClass = Class.forName("com.tylermoses.grexsampleapp." + local);
			Intent ourIntent = new Intent(Menu.this, ourClass);
			startActivity(ourIntent);	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		
		}	
	}



	
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		
		case R.id.aboutUs:
			Intent about = new Intent(Menu.this, AboutUs.class);
			startActivity(about);
			break;
			
		case R.id.preferences:
			Intent prefs = new Intent(Menu.this, Prefs.class);
			startActivity(prefs);
			break;
			
		case R.id.exit:
			break;
		}
		return false;
	}

	
	
}
