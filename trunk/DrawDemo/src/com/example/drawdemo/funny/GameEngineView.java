package com.example.drawdemo.funny;

import java.util.Random;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameEngineView extends View {

	private static final String TAG = GameEngineView.class.getSimpleName();

	private int px;
	private int py;
	private int cx;
	private int cy;

	private boolean players_move;
	private int clickx;
	private int clicky;

	Random rgen;

	/**
	 * Create a simple handler that we can use to cause animation to happen. We
	 * set ourselves as a target and we can use the sleep() function to cause an
	 * update/invalidate to occur at a later date.
	 */
	private RefreshHandler mRedrawHandler = new RefreshHandler();

	@SuppressLint("HandlerLeak")
	class RefreshHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			GameEngineView.this.update();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};

	public GameEngineView(Context context) {
		super(context);
		setFocusable(true);
		players_move = true;
		rgen = new Random();
	}

	public void update() {
		if (players_move) {
			Log.d(TAG, "new player x");
			px = clickx;
			py = clicky;
			GameEngineView.this.invalidate();
			switchMove();
			mRedrawHandler.sleep(100);
		} else {
			Log.d(TAG, "new ai x");
			calcAIMove();
			GameEngineView.this.invalidate();
			switchMove();
		}

		Log.d(TAG, "update -> sleep handler");
	}

	public void switchMove() {
		players_move = !players_move;
	}

	public void calcAIMove() {
		for (int i = 0; i < 100000; i++) {
			cx = rgen.nextInt(getWidth());
			cy = rgen.nextInt(getHeight());
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "event");
		int eventaction = event.getAction();
		if (players_move && eventaction == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "action_down");
			clickx = (int) event.getX();
			clicky = (int) event.getY();
			update();
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		Paint green = new Paint();
		green.setColor(Color.GREEN);
		Paint red = new Paint();
		red.setColor(Color.RED);

		canvas.drawCircle(px, py, 25, green);
		canvas.drawCircle(cx, cy, 25, red);
	}
}