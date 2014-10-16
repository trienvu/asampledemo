package com.example.drawdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.drawdemo.surface.DrawCanvas;

public class DrawImageActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas1);

		DrawCanvas mycanvas = (DrawCanvas) findViewById(R.id.SurfaceView01);
		mycanvas.setOnClickListener(clickListener);
	}

	private OnClickListener clickListener = new OnClickListener() {
		private boolean isDrawn = false;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (!isDrawn) {
				DrawCanvas mycanvas = (DrawCanvas) findViewById(R.id.SurfaceView01);
				mycanvas.startDrawImage();
				isDrawn = true;
			}
		}
	};
}
