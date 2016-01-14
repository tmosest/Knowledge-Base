package com.tylermoses.grexsampleapp;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SimpleAdder extends ActionBarActivity {
	
	static int counter;
	Button add, sub;
	TextView display;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adder);
        if (savedInstanceState != null){
        	savedInstanceState.getInt("counter");
          } else {counter = 0;}
        add = (Button) findViewById(R.id.bAdd);
        sub = (Button) findViewById(R.id.bSub);
        display = (TextView) findViewById(R.id.tvDisplay);
        display.setText("Your total is "+ counter);
        add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter ++;
				display.setText("Your total is "+ counter);
			}
		});
        
        sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter --;
				display.setText("Your total is "+ counter);
			}
		});
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
    	  super.onSaveInstanceState(savedInstanceState);
    	  int value = counter;
    	  savedInstanceState.putInt("counter", value);
    	}

}
