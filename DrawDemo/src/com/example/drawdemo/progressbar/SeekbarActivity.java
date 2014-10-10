package com.example.drawdemo.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.drawdemo.R;

public class SeekbarActivity extends Activity implements
		OnSeekBarChangeListener {
	/** Called when the activity is first created. */
	private TextView mTv;
	private SeekBar mSeekbar;
	private int mSeekbar_width;
	private LinearLayout mLlSeekBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seekbar);

		mLlSeekBar = (LinearLayout) findViewById(R.id.llSeekbar);
		mTv = (TextView) findViewById(R.id.percent);
		mSeekbar = (SeekBar) findViewById(R.id.slider);

		mSeekbar.setMax(100);
		mSeekbar.setProgress(50);
		mSeekbar.setOnSeekBarChangeListener(this);

	}

	@Override
	public void onProgressChanged(SeekBar v, int progress, boolean isUser) {

		LinearLayout.MarginLayoutParams prgLayoutParams = (LinearLayout.MarginLayoutParams) mLlSeekBar
				.getLayoutParams();

		int progressMarginLeft = prgLayoutParams.leftMargin;
		mSeekbar_width = mSeekbar.getWidth();

		int margin_left = (int) (mSeekbar_width / 100) * progress
				+ progressMarginLeft;
		setTvPosition(progress, margin_left);

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	private void setTvPosition(int value, int margin_left) {
		mTv.setText(value + "%");
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		int width = mTv.getWidth();
		int y_axis = (int) mTv.getTranslationY();
		lp.setMargins(margin_left, y_axis, 0, 0);

		mTv.setLayoutParams(lp);
	}
}