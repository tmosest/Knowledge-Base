package com.tylermoses.grexsampleapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener{
	
	private TextView canWrite, canRead;
	private String state;
	boolean canW, canR;
	Spinner spinner;
	String[] paths = {"Music", "Pictures", "Downloads"};
	File path = null;
	File filename = null;
	Button save, confirm;
	EditText saveFile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite = (TextView)findViewById(R.id.tvCanWrite);
		canRead = (TextView)findViewById(R.id.tvCanRead);
		save = (Button)findViewById(R.id.bSaveFile);
		confirm = (Button)findViewById(R.id.bConfirmSaveAs);
		saveFile = (EditText)findViewById(R.id.etSaveAs);
		
		save.setOnClickListener(this);
		confirm.setOnClickListener(this);
		canWrite.setText("Write");
		canRead.setText("Read");
		checkState();
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
		spinner = (Spinner)findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	private void checkState() {
		// TODO Auto-generated method stub
		state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			// can read and write
			canWrite.setText("Write : true");
			canRead.setText("Read : true");
			canW = canR = true;
		} else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			// read no write
			canWrite.setText("Write : false");
			canRead.setText("Read :  true");
			canW  = false;
			canR = true;
		} else {
			canWrite.setText("Write : false");
			canRead.setText("Read : false");
			canW = canR = false;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinner.getSelectedItemPosition();
		switch(position){
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
			
		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
			
		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
			
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSaveFile:
			String f = saveFile.getText().toString();
			filename = new File(path, f + ".png");
			checkState();
			if(canW == canR == true){
				
				path.mkdir();
				
				try {
					InputStream is = getResources().openRawResource(R.drawable.splashbackground);
					OutputStream os = new FileOutputStream (filename);
					byte[] data =  new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					
					Toast t = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_LONG);
					t.show();
					
					MediaScannerConnection.scanFile(ExternalData.this, 
							new String[] {filename.toString()}, 
							null, 
							new MediaScannerConnection.OnScanCompletedListener() {
								
								@Override
								public void onScanCompleted(String path, Uri uri) {
									// TODO Auto-generated method stub
									Toast t = Toast.makeText(ExternalData.this, "scan complete", Toast.LENGTH_SHORT);
									t.show();
								}
							});
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
			
		case R.id.bConfirmSaveAs:
			save.setVisibility(View.VISIBLE);
			break;
		}
	}
	
	

}
