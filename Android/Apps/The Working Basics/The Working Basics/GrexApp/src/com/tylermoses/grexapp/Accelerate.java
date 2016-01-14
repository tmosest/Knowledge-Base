package com.tylermoses.grexapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener{

	float x, y, sensorX, sensorY;
	Bitmap ball;
	SensorManager sm ;
	MyBringBackSurface ourSurfaceView;
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sm.unregisterListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		 sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() !=0){
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.aquaball75);
		x = y = sensorX = sensorY = 0;
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.resume();
		setContentView(ourSurfaceView);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
		sensorX = e.values[0];
		sensorY = e.values[1];
		try {
			Thread.sleep(16);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public class MyBringBackSurface extends SurfaceView implements Runnable{

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;
		
		public MyBringBackSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder();	
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
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				if(!ourHolder.getSurface().isValid()){
					continue;
				}
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB( 230, 230, 230);
				float centerX = canvas.getWidth()/2 - ball.getWidth()/2;
				float centerY = canvas.getHeight()/2 - ball.getHeight()/2;
				canvas.drawBitmap(ball, centerX - sensorX*20, centerY - sensorY*20, null);
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

}
