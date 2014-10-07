package com.example.drawdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.drawdemo.surface.BubbeThreadSurfaceView;

public class MainActivity extends Activity {
	private Context mContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new BubbeThreadSurfaceView(mContext));
	}
}
