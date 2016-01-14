package com.tylermoses.grexapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.os.PowerManager.WakeLock;
import android.view.View;

public class MyBringBack extends View {
	
	Bitmap plushSign, plushSignHover, plushSignClicked;
	float changingY = 0;
	Typeface font;
	
	
	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		plushSign = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		plushSignHover = BitmapFactory.decodeResource(getResources(), R.drawable.plushover);
		plushSignClicked = BitmapFactory.decodeResource(getResources(), R.drawable.plusclicked);
		//font = Typeface.createFromAsset(context.getAssets(), "african.tff");
		//font = Typeface.createFromAsset(context.getAssets(), "african");
		font = Typeface.createFromAsset(context.getAssets(), "african.ttf");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE); //set canvas white
		// draw text
		Paint textPaint = new Paint();
		//change color based on location of cross
		if(changingY < canvas.getHeight()/2 - 100){
			textPaint.setARGB(50, 255, 100, 100);
		}
		else if(changingY <= canvas.getHeight()/2 + 100 && changingY >= canvas.getHeight()/2 - 100 - plushSign.getHeight()){
			textPaint.setARGB(100, 100, 255, 100);
		} else {
			textPaint.setARGB(50, 100, 100, 255);
		}
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(75);
		textPaint.setTypeface(font);
		canvas.drawText("G-REX Media", canvas.getWidth()/2, 300, textPaint);
		// draw rec
		Rect middleRect = new Rect();
		middleRect.set(0, canvas.getHeight()/2 - 100, canvas.getWidth(), canvas.getHeight()/2 + 100);
		Paint ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
		canvas.drawRect(middleRect, ourBlue);
		
		//change between images
		if(changingY < canvas.getHeight()/2 - 100){
			canvas.drawBitmap(plushSign, (canvas.getWidth()/2-plushSign.getWidth()/2), changingY, null);
		}
		else if(changingY <= canvas.getHeight()/2 + 100 && changingY >= canvas.getHeight()/2 - 100 - plushSign.getHeight()){
			canvas.drawBitmap(plushSignClicked, (canvas.getWidth()/2-plushSign.getWidth()/2), changingY, null);
		} else {
			canvas.drawBitmap(plushSignHover, (canvas.getWidth()/2-plushSign.getWidth()/2), changingY, null);
		}
		
		// move images
		if(changingY < canvas.getHeight()-plushSign.getHeight()){
			changingY += 10;
		} else {
			changingY = 0;
		}
		
		invalidate();
	}
	
	
	
}
