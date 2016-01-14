package com.tylermoses.grexapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ApproximatePi extends Activity implements OnClickListener{

	// Declare Variables
	TextView tvTitle , tvResults; // Text Views
	EditText etCases;
	Button bSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.approximatepi);
		// Find Views
		etCases = (EditText)findViewById(R.id.etPiCases);
		bSubmit = (Button)findViewById(R.id.bPi);
		tvTitle = (TextView) findViewById(R.id.tvpititle);
		// Set On clicks
		//etCases.setOnClickListener(this);
		bSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		// Submit Button
		case R.id.bPi:
			String cases = etCases.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("cases", cases);
			Intent a = new Intent(ApproximatePi.this, ApproximatePiAnimation.class);
			a.putExtras(basket);
			startActivityForResult(a, 0);
			//startActivity(a);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle basket = data.getExtras();
			String s = basket.getString("answer");
			//Results
			
			tvTitle.setText(s);
		}
	}
	
}
