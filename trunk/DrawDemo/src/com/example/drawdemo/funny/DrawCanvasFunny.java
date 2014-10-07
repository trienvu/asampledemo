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
import android.widget.Toast;

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
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		addContentView(view, params);
		view.setDrawingCacheEnabled(true);
		view.setOnTouchListener(this);
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

			mPaint.setColor(Color.RED);
			canvas.drawCircle(50 / 2, 50 / 2, 3, mPaint);

			if (bitmap != null) {
				bitmap.setPixel((int) x, (int) y, Color.RED);

				/*
				 * Toast.makeText(mContext, "x:" + x + " - y:" + y,
				 * Toast.LENGTH_SHORT).show();
				 */

				canvas.drawBitmap(bitmap, x, y, mPaint);
			}

		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int eventaction = event.getAction();
		if (eventaction == MotionEvent.ACTION_DOWN) {
			x = event.getX();
			y = event.getY();

			bitmap = v.getDrawingCache();
			v.invalidate();
			v.requestLayout();

		}

		return true;
	}
}
