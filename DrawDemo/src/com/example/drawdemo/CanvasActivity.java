package com.example.drawdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CanvasActivity extends Activity {
	private Context mContext = this;
	private LinearLayout mLlRect;
	private Paint mPaint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_canvas);
		// find view
		mLlRect = (LinearLayout) this.findViewById(R.id.llRect);

		init();
	}

	@SuppressWarnings("deprecation")
	private void init() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.BLACK);
		
		Bitmap bitmap = Bitmap.createBitmap(400, 500, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawRect(50, 50, 200, 200, mPaint);
		
		mLlRect.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), bitmap));
	}
}
