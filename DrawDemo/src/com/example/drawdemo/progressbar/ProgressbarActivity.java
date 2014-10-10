package com.example.drawdemo.progressbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawdemo.R;

public class ProgressbarActivity extends Activity {
	private Context mContext;
	private ProgressBar mProgressBar;
	private TextView mTvValue;
	private int mProgressStatus = 0;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		mContext = this;
		mProgressBar = (ProgressBar) this.findViewById(R.id.progressBar1);
		mTvValue = (TextView) this.findViewById(R.id.tvValue);

		init();
	}

	private void init() {
		mProgressBar.setProgress(0);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (mProgressStatus < 100) {
					mProgressStatus++;
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mProgressBar.setProgress(mProgressStatus);
							ViewGroup.MarginLayoutParams prgLayoutParams = (ViewGroup.MarginLayoutParams) mProgressBar
									.getLayoutParams();
							int progressMarginLeft = prgLayoutParams.leftMargin;
							int progressWidth = mProgressBar.getWidth();

							int margin_left = (int) (progressWidth / 100)
									* mProgressStatus + progressMarginLeft;
							setTvPosition(mProgressStatus, margin_left);
						}

					});

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void setTvPosition(int value, int margin_left) {
		mTvValue.setText("" + value);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int width = mTvValue.getWidth();
		int y_axis = (int) mTvValue.getTranslationY();
		lp.setMargins(margin_left - (int) width / 2, y_axis, 0, 0);

		mTvValue.setLayoutParams(lp);
	}
}
