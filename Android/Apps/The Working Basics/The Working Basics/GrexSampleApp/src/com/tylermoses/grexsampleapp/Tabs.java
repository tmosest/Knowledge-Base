package com.tylermoses.grexsampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener{
	// Declare variables
	TabHost th; // new object the tabhost
	TabSpec specs; // Tab specs
	Button newTab, start, stop; // buttons
	TextView showResults; // Text Views
	long startTime, stopTime; // used to store time variables for simple stop watch
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		th = (TabHost) findViewById(R.id.tabhost);
		newTab = (Button)findViewById(R.id.bNewTab);
		start = (Button)findViewById(R.id.bStartWatch);
		stop = (Button)findViewById(R.id.bStopWatch);
		showResults = (TextView)findViewById(R.id.tvWatchResults);
		// Declare onClickListeners
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		newTab.setOnClickListener(this);
		
		th.setup(); // set up the tab host
		specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab2");
		th.addTab(specs);
		
		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Tab3");
		th.addTab(specs);	
		startTime = 0;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bNewTab:
			TabSpec ourSpec = th.newTabSpec("newtag");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text = new TextView(Tabs.this);
					text.setText("You've created a new tab!");
					return (text);
				}
			});
			ourSpec.setIndicator("New");
			th.addTab(ourSpec);
			break;
			
		case R.id.bStartWatch:
			startTime = System.currentTimeMillis(); // Get System Time
			break;
			
		case R.id.bStopWatch:
			stopTime = System.currentTimeMillis(); // Get System Time
			if(startTime !=0 ){
				long result = stopTime - startTime; // set result to end minus initial
				// calculate times
				int millis = (int) result;
				int seconds = millis / 1000;
				int miniutes = seconds/60;
				int hours = miniutes/60;
				millis = millis % 1000;
				seconds = seconds % 60; // base 60
				miniutes = miniutes % 60; // base 60
				showResults.setText(String.format("%d Hrs:%d Mins:%d Secs:%d Thousandsths", hours,miniutes,seconds,millis)); // Convert Long result to Text View String
			}
			startTime = 0; // thank you Jonas Purtschert
			break;
		}
	}
	
}
