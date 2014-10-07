package com.example.drawdemo.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BubbleSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private Context mContext;
	private SurfaceHolder mSurfaceHolder;
	private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public BubbleSurfaceView(Context context) {
		super(context);
		mContext = context;
		mSurfaceHolder = getHolder();
		setFocusable(true);
		init();
	}

	private void init() {
		mSurfaceHolder.addCallback(this);
		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Style.FILL);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas = mSurfaceHolder.lockCanvas();
		canvas.drawColor(Color.BLACK);
		canvas.drawCircle(50, 100, 3, mPaint);
		mSurfaceHolder.unlockCanvasAndPost(canvas);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

}
