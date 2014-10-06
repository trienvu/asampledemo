package com.example.drawdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
	Paint mPaint;

	public MyView(Context context) {
		super(context);

		init();
	}

	private void init() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(Color.WHITE);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// Account for padding
		/*
		 * float xpad = (float) (getPaddingLeft() + getPaddingRight()); float
		 * ypad = (float) (getPaddingTop() + getPaddingBottom());
		 * 
		 * // Account for the label if (mShowText) xpad += mTextWidth;
		 * 
		 * float ww = (float) w - xpad; float hh = (float) h - ypad;
		 * 
		 * // Figure out how big we can make the pie. float diameter =
		 * Math.min(ww, hh);
		 */

		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int w = getWidth();
		int h = getHeight();
		int radius;
		radius = 100;

		// Draw background of view
		canvas.drawPaint(mPaint);

		int paintColor = Color.parseColor("#343433");
		mPaint.setColor(paintColor);
		canvas.drawCircle(w / 2, h / 2, radius, mPaint);
	}
}
