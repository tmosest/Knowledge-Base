package com.tylermoses.grexapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	WebView ourBrow;
	Button go, back, forward, refrsh, clear;
	EditText url;
	InputMethodManager imm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Set View
		setContentView(R.layout.simplebrowser);
		// Get Browsers
		ourBrow = (WebView)findViewById(R.id.wvBrowser);
		ourBrow.setWebViewClient(new OurViewClient());
		// Browser settings
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		//try url
		try {
		ourBrow.loadUrl("http://mybringback.com");
		} catch (Exception e){
			e.printStackTrace();
		}
		go = (Button)findViewById(R.id.bGo);
		back = (Button)findViewById(R.id.bBack);
		forward = (Button)findViewById(R.id.bForward);
		refrsh = (Button)findViewById(R.id.bRefresh);
		clear = (Button)findViewById(R.id.bClearHis);
		url = (EditText)findViewById(R.id.etURL);
		
		
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refrsh.setOnClickListener(this);
		clear.setOnClickListener(this);
		forward.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bForward:
			if(ourBrow.canGoForward()){
				ourBrow.goForward();
			}
			break;
		case R.id.bRefresh:
			ourBrow.reload();
			break;
		case R.id.bClearHis:
			ourBrow.clearHistory();
			break;
		case R.id.bBack:
			if(ourBrow.canGoBack()){
				ourBrow.goBack();
			}
			break;
		case R.id.bGo:
			String theWebsite = url.getText().toString();
			ourBrow.loadUrl(theWebsite);
			// Hiding the keyboard after ussing an EditText
			imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromInputMethod(url.getWindowToken(), 0); // Hide keyboard after url entered
			break;
		}
	}
	
	

}
