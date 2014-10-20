package com.example.drawdemo.surface;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.drawdemo.R;

public class BubbeThreadSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private Context mContext;
	private BubbeThread bubbeThread;
	private SurfaceHolder mSurfaceHolder;
	private float x = 50, y = 50;
	private Bitmap mBitmap;

	public BubbeThreadSurfaceView(Context context) {
		super(context);
		mContext = context;
		setFocusable(true);

		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);

	}

	public BubbeThread getThread() {
		return bubbeThread;
	}

	@SuppressLint("WrongCall")
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);

		setWillNotDraw(false); // Allows us to use invalidate() to call onDraw()

		bubbeThread = new BubbeThread(holder, mContext, new Handler());
		bubbeThread.setRunning(true);
		bubbeThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// bubbeThread.setSurfaceSize(width, height);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		bubbeThread.setRunning(false);
		while (retry) {
			try {
				bubbeThread.join();
				retry = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private class BubbeThread extends Thread {

		private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		private int canvasWidth = 200;
		private int canvasHeight = 400;
		private static final int SPEED = 2;
		private boolean running = true;

		private float bubbleX;
		private float bubbleY;
		private float headingX;
		private float headingY;
		private SurfaceHolder mSurfaceHolder;
		private List<Rect> lstDirtyRect;
		private float top, bottom, left, right;
		Rect mDirtRect;

		public BubbeThread(SurfaceHolder surfaceHolder, Context context,
				Handler handler) {
			mSurfaceHolder = surfaceHolder;

			mPaint.setColor(Color.RED);
			mPaint.setStyle(Style.FILL);
		}

		public void doStart() {
			synchronized (mSurfaceHolder) {
				// Start bubble in centre and create some random motion
				bubbleX = canvasWidth / 2;
				bubbleY = canvasHeight / 2;
				headingX = (float) (-1 + (Math.random() * 2));
				headingY = (float) (-1 + (Math.random() * 2));
			}
		}

		@SuppressLint("WrongCall")
		@Override
		public void run() {
			super.run();
			while (running) {
				Canvas canvas = null;
				try {
					canvas = mSurfaceHolder.lockCanvas(mDirtRect);
					synchronized (mSurfaceHolder) {
						doDraw(canvas);
					}
				} catch (Exception e) {
				}

				finally {
					if (canvas != null) {
						mSurfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
			}

		}

		public void setRunning(boolean b) {
			running = b;
		}

		public void setSurfaceSize(int width, int height) {
			synchronized (mSurfaceHolder) {
				canvasWidth = width;
				canvasHeight = height;
				doStart();
			}
		}

		public void doDraw(Canvas canvas) {
			bubbleX = bubbleX + (headingX * SPEED);
			bubbleY = bubbleY + (headingY * SPEED);

			// canvas.drawCircle(bubbleX, bubbleX, 20, mPaint);
			// canvas.drawColor(Color.WHITE, Mode.CLEAR);
			// canvas.drawColor(Color.WHITE);
			canvas.drawBitmap(mBitmap, x, y, mPaint);
			mDirtRect = new Rect((int) x, (int) y,
					(int) (x + mBitmap.getWidth()),
					(int) (y + mBitmap.getHeight()));
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			x = event.getX();
			y = event.getY();

		}
		return true;
	}

}
