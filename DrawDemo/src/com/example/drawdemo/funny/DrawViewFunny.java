package com.example.drawdemo.funny;

import com.example.drawdemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class DrawViewFunny extends Activity implements OnTouchListener {
	private float x, y;
	private Context mContext = this;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas);

		MyCustomPannel view = new MyCustomPannel(mContext);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		addContentView(view, layoutParams);
		view.setOnTouchListener(this);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		x = event.getX();
		y = event.getY();

		v.invalidate();

		return true;
	}

	private class MyCustomPannel extends View {
		Paint mPaint;

		public MyCustomPannel(Context context) {
			super(context);
			mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			mPaint.setColor(Color.GREEN);
			mPaint.setStrokeWidth(6);
			canvas.drawLine(10, 10, 50, 50, mPaint);

			mPaint.setColor(Color.RED);
			canvas.drawLine(50, 50, 90, 10, mPaint);

			canvas.drawCircle(50, 50, 3, mPaint);

			canvas.drawCircle(x, y, 3, mPaint);
		}
	}
}
