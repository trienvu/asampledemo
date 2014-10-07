package com.example.drawdemo.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class BubbeThreadSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private Context mContext;
	private BubbeThread bubbeThread;

	public BubbeThreadSurfaceView(Context context) {
		super(context);
		mContext = context;
		setFocusable(true);
	}

	public BubbeThread getThread() {
		return bubbeThread;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		bubbeThread = new BubbeThread(getHolder(), mContext, new Handler());
		bubbeThread.setRunning(true);
		bubbeThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		bubbeThread.setSurfaceSize(width, height);
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
		private boolean running = false;

		private float bubbleX;
		private float bubbleY;
		private float headingX;
		private float headingY;
		private SurfaceHolder mSurfaceHolder;

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

		@Override
		public void run() {
			// super.run();
			while (running) {
				Canvas canvas = null;
				try {
					canvas = mSurfaceHolder.lockCanvas();
					synchronized (mSurfaceHolder) {
						doDraw(canvas);
					}
				} catch (Exception e) {
				}

				finally {
					if (canvas != null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
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
			canvas.restore();
			canvas.drawColor(Color.RED);
			mPaint.setColor(Color.RED);
			canvas.drawCircle(50, 50, 3, mPaint);

		}
	}

}
