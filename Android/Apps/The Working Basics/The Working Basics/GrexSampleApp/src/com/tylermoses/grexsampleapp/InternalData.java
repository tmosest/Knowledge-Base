package com.tylermoses.grexsampleapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener{

	//Declare Variables
		EditText input;
		Button save,load;
		TextView data;
		FileOutputStream fos;
		String filename = "InterString";
		
		public class LoadSomeStuff extends AsyncTask<String, Integer, String>{
			
			ProgressDialog dialog;
			
			protected void onPreExecute(){
				//example of setting up something
				//f = "whatever";
				dialog = new ProgressDialog(InternalData.this);
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setMax(100);
				dialog.show();
			}
			
			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				String collected = null;
				
				for(int i=0; i < 20; i++){
					publishProgress(5);
					try {
						Thread.sleep(88);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				dialog.dismiss();
				try {
					FileInputStream fis = openFileInput(filename);
					byte[] dataArray = new byte[fis.available()];
					while(fis.read(dataArray) != -1){
						collected = new String(dataArray);
					}
					fis.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return collected;
				
			}
			
			protected void onProgressUpdate(Integer...progress){
				dialog.incrementProgressBy(progress[0]);
			}
			
			protected void onPostExecute(String result){
				data.setText(result);
			}
		}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
	}

	private void setupVariables() {
		// TODO Auto-generated method stub
		// Find ViewByID
		save = (Button)findViewById(R.id.bSave);
		load = (Button)findViewById(R.id.bLoad);
		input = (EditText)findViewById(R.id.etSharedPreferences);
		data = (TextView)findViewById(R.id.tvDataLoad);
		// set onClicks
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		// try to openFile
		try {
			fos = openFileOutput(filename, Context.MODE_PRIVATE); //open stream
			fos.close(); //close stream
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSave:
			String someData = input.getText().toString();
			
			// Saving data via code
			/*File f = new File(filename);
			try {
				fos = new FileOutputStream(f);
				//write some data
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				fos = openFileOutput(filename, Context.MODE_PRIVATE);
				fos.write(someData.getBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bLoad:
			new LoadSomeStuff().execute(filename);
			break;
		}
		
		
	}

}
