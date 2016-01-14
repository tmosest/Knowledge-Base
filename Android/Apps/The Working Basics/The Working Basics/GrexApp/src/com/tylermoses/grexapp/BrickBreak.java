package com.tylermoses.grexapp;

import com.tylermoses.grexapp.ApproximatePiAnimation.ApproxPiSurface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class BrickBreak extends Activity implements OnTouchListener{
	OurGame ourGameView;
	int lives, score;
	float centerX, centerY, x, y;
	float circX, circY, circR, circDx, circDy = 0;
	float brickX, brickY, brickR, brickDx, brickDy = 0;
	int brickbarW ,  brickbarH;
	//Canvas canvas;
	PowerManager pM;
	WakeLock wL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//start variables
		ourGameView = new OurGame(this);
		lives = 3;
		score = 0;
		
		ourGameView.setOnTouchListener(this);
		setContentView(ourGameView);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourGameView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourGameView.resume();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		//FPS tutorial
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//end FPS tutorial
				x = event.getX();
				y = event.getY();
				
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					if(circDx == 0 && circDy == 0){
						//circDx = 10;
						circDy = -10;
					} 
					
					brickX = event.getX();
					//brickY = event.getX();
					break;
					
				
				}
				return false;
	}
	

	public class OurGame extends SurfaceView implements Runnable {
		
		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;
		
		public OurGame(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			ourHolder = getHolder();	
			circR = 15;
		}

		public void resume() {
			// TODO Auto-generated method stub
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}

		public void pause() {
			// TODO Auto-generated method stub
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

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				if(!ourHolder.getSurface().isValid()){
					continue;
				}
				Canvas canvas = ourHolder.lockCanvas();
				
				if(circDx == 0 && circDy == 0){
					circX = canvas.getWidth()/2 - circR;
					circY = canvas.getHeight()/2 - circR;
					 brickbarW = canvas.getWidth()/8;
					 brickbarH = 30;
					brickX = canvas.getWidth()/2 - brickbarW;
					brickY = canvas.getHeight() - brickbarH - 100;
				} else {
					circX += circDx;
					circY += circDy;
				}
				
				// draw background
				
				canvas.drawRGB( 0, 0, 0);
				
				// Draw Lives
				String text;
				
				 text = lives + " Lives : Score " + score;
				
				Paint textPaint = new Paint();
				textPaint.setColor(Color.WHITE);
				textPaint.setTextAlign(Align.CENTER);
				textPaint.setTextSize(75);
				canvas.drawText(text, canvas.getWidth()/2, 100, textPaint);
				
				//draw brick bar
				Rect brickBar = new Rect();
				
				
				
				int brickXint = (int) brickX;
				int brickYint = (int) brickY;
				int brickBarWint = (int)brickbarW;
				
				brickBar.set(brickXint , brickYint , brickXint + 2*brickbarW, brickYint - 20);
				
				Paint ourBlue = new Paint();
				ourBlue.setColor(Color.BLUE);
				
				canvas.drawRect(brickBar, ourBlue);
				
				Paint circP = new Paint();
				circP.setColor(Color.RED);
				canvas.drawCircle(circX, circY, circR, circP);
				
				//check for death 
				if(circY + circR >= canvas.getHeight()){
					lives -= 1;
					circDy = 0;
					circDx = 0;
				}
				
				// check for collision with top
				if(circY - circR <= 0){
					circDy = -circDy;
				}
				
				// with sides
				
				if(circX - circR <= 0){
					circDx = -circDx;
				}
				if(circX + circR <= canvas.getWidth()){
					circDx = -circDx;
				}
				
				if((circX  >= brickX)){
					Rect tst = new Rect();
					tst.set(brickXint, 0, brickXint+2*brickbarW, canvas.getHeight());
					Paint ourTstPaint = new Paint();
					ourTstPaint.setARGB(25, 255, 0, 255);
					canvas.drawRect(tst, ourTstPaint);
				}
				
				if(circX <= brickX + 10*brickbarH){
					Rect tst2 = new Rect();
					tst2.set(brickXint, 0, brickXint+2*brickbarW, canvas.getHeight());
					Paint ourTstPaint2 = new Paint();
					ourTstPaint2.setARGB(25, 0, 255, 255);
					canvas.drawRect(tst2, ourTstPaint2);
				}
				
				// basic collision with top of bar
				if ( (circY + circR >= brickY - 20) && (circX  >= brickX) && circX <= brickX + 10*brickbarH)
				{
					//circX - circR <= brickX + 2*brickbarH && 
					//circX + circR >= brickX 
					circDx = - circDx;
					circDy = -circDy;
				}
				//end game when dead
				if(lives == 0){
				finish();
				}
				// unlock canvas
				ourHolder.unlockCanvasAndPost(canvas);
			}// end while	
		} //end run
		
		
	}

}
