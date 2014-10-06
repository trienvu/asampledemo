package com.example.drawdemo.funny;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;

import com.example.drawdemo.R;

public class DrawCanvasFunny extends Activity implements OnTouchListener {
	private Context mContext = this;
	private float x, y;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		MyCustomPanel view = new MyCustomPanel(mContext);
		view.setOnTouchListener(this);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		addContentView(view, params);
		view.setDrawingCacheEnabled(true);
		bitmap = view.getDrawingCache();
	}

	private class MyCustomPanel extends View {
		Paint mPaint;

		public MyCustomPanel(Context context) {
			super(context);
			mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}

		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);

			mPaint.setColor(Color.GREEN);
			mPaint.setStrokeWidth(6);

			if (bitmap != null) {
				bitmap.setPixel((int) x, (int) y, Color.RED);
				canvas.drawBitmap(bitmap, x, y, mPaint);
			}

		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		x = event.getX();
		y = event.getY();

		bitmap = v.getDrawingCache();
		v.invalidate();
		
		return true;
	}
}
