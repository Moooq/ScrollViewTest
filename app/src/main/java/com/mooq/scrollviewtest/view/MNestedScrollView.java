package com.mooq.scrollviewtest.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by moq.
 * on 2019/5/14
 */
public class MNestedScrollView extends NestedScrollView {

	private String TAG = MNestedScrollView.class.getSimpleName();

	int mParentScrollHeight ;
	int mScrollY ;

	public MNestedScrollView(Context context) {
		super(context);
	}

	public MNestedScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setMyScrollHeight(int scrollLength){
		this.mParentScrollHeight = scrollLength;
	}

	@Override
	public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
		super.onNestedPreScroll(target, dx, dy, consumed);

		if (mScrollY < mParentScrollHeight) {
			consumed[0] = dx;
			consumed[1] = dy;
			scrollBy(0, dy);
		}

		Log.d(TAG,"dx " + dx + " dy "+ dy +  " " + consumed[0]  + " " + consumed[1] + " scrollY " + mScrollY);
	}


	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		mScrollY = t;
	}
}
