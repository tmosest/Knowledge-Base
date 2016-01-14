package com.tylermoses.grexapp;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class ApproximatePiAnimation extends Activity{

	ApproxPiSurface ourView;
	String result = "3.14";
	String cases = "0";
	float hits = 0;
	float total = 0;
	int shotsFired = 0;
	WakeLock wL;
	int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
		
		Bundle gotBasket = getIntent().getExtras();
		cases = gotBasket.getString("cases");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ourView = new ApproxPiSurface(this);
		setContentView(ourView);
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourView.pause();
		
		wL.release();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourView.resume();
		wL.acquire();
	}
	
	public class ApproxPiSurface extends SurfaceView implements Runnable {

		
		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;
		float[] pts = null;
		
		public ApproxPiSurface(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			ourHolder = getHolder();	
		}
		

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				if(!ourHolder.getSurface().isValid()){
					continue;
				}
				Canvas canvas = ourHolder.lockCanvas();
				
				//wake-lock
				
				
				canvas.drawColor(Color.BLACK); //set canvas black
				
				//Draw center square
				
				Rect square = new Rect();
				
				float radius = (int) (canvas.getWidth()*0.8*0.5);
				int ra = (int) radius;
				//Draw background square
				square.set(canvas.getWidth()/2 - ra , canvas.getHeight()/2 - ra, canvas.getWidth()/2 + ra, canvas.getHeight()/2 + ra);
				Paint ourBlue = new Paint();
				ourBlue.setColor(Color.MAGENTA);
				canvas.drawRect(square, ourBlue);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Draw circle
				float cx = canvas.getWidth()/2; // center it 
				float cy = canvas.getHeight()/2;
				float r = (float) radius;
				Paint ourGreen = new Paint();
				ourGreen.setColor(Color.CYAN);
				canvas.drawCircle(cx, cy, r, ourGreen);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Write text to the screen
				String text;
				
				 text = shotsFired + " out of " + cases + " fired";
				
				Paint textPaint = new Paint();
				textPaint.setColor(Color.WHITE);
				textPaint.setTextAlign(Align.CENTER);
				textPaint.setTextSize(75);
				canvas.drawText(text, canvas.getWidth()/2, 100, textPaint);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Get random coordinate
				
				Random crazy = new Random();
				
				
				// Must be within the square therefore n=0 = left and n=1000 =right
				
				//float x = (canvas.getWidth()/2 - radius) + ( canvas.getWidth()/2 + radius - (canvas.getWidth()/2 - radius) )* crazy.nextInt(1000);
				//float y = (canvas.getHeight()/2 - radius) + ( canvas.getHeight()/2 + radius - (canvas.getHeight()/2 - radius) )* crazy.nextInt(1000);
				float x = (  ( canvas.getWidth()/2 + radius ) - ( canvas.getWidth()/2 - radius ) )*crazy.nextFloat() + (canvas.getWidth()/2 - radius);//canvas.getWidth()/2;
				float y = ( ( canvas.getHeight()/2 + radius ) - ( canvas.getWidth()/2 - radius ) )*crazy.nextFloat() + (canvas.getHeight()/2 - radius);// canvas.getHeight()/2;
				
				Paint pointColor = new Paint();
				pointColor.setColor(Color.YELLOW);
				canvas.drawPoint(canvas.getWidth()/2, canvas.getHeight()/2, textPaint);
				
				float x1 = x - canvas.getWidth()/2;
				float y1 = y - canvas.getHeight()/2;
				
				boolean inCircle = (x1*x1) + (y1*y1) <= radius*radius;
				
				int misses = (int) (total - count);
				
				if( inCircle ) {
					textPaint.setColor(Color.GREEN);
					textPaint.setTextAlign(Align.CENTER);
					textPaint.setTextSize(75);
					canvas.drawText("Hit! " + count + " hits " + misses + " misses", canvas.getWidth()/2, 200, textPaint);
					count += 1;
					total +=1;
				} else {
					textPaint.setColor(Color.RED);
					textPaint.setTextAlign(Align.CENTER);
					textPaint.setTextSize(75);
					canvas.drawText("Miss! " + count + " hits " + misses + " misses", canvas.getWidth()/2, 200, textPaint);
					total +=1;
				}
				
				double pi; 
				
				if (count != 0){
					pi = (double)count / (double)total ;
					pi = pi * 4;
				} else {
					pi = 3.141;
				}
				textPaint.setColor(Color.RED);
				textPaint.setTextAlign(Align.CENTER);
				textPaint.setTextSize(75);
				canvas.drawText("Pi is " + pi , canvas.getWidth()/2, 300, textPaint);
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(shotsFired == Integer.parseInt(cases)){
					// On completion send back result
					Intent person = new Intent();
					Bundle backpack = new Bundle();
					result = Double.toString(pi);
					backpack.putString("answer", result);
					person.putExtras(backpack);
					setResult(RESULT_OK, person);
					//wL.release();
					finish();
				} else {
					shotsFired += 1;
				}
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
		
		public void pause(){
			isRunning = false;
			while(true){
				try {
					ourThread.join();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}
		
		public void resume(){
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}
		
		
		

		
	}

	
}
