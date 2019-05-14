package com.mooq.scrollviewtest.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/**
 * Created by moq.
 * on 2019/5/14
 */
public class GradationScrollView extends ScrollView {


	public interface ScrollViewListener {

		void onScrollChanged(GradationScrollView scrollView, int x, int y,
												 int oldx, int oldy);

	}

	private ScrollViewListener scrollViewListener = null;

	public GradationScrollView(Context context) {
		super(context);

	}

	public GradationScrollView(Context context, AttributeSet attrs,
														 int defStyle) {
		super(context, attrs, defStyle);

	}

	public GradationScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}

	}

	//首页  搜索框渐变效果
	public void resetHOMEHeight(final View headView, final View nestedContent,final View topView) {
		this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				GradationScrollView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				nestedContent.getLayoutParams().height = GradationScrollView.this.getHeight() - headView.getHeight()-topView.getHeight()-4;
				nestedContent.requestLayout();

			}
		});
	}


	public void resetHeight(final View headView, final View nestedContent) {
		this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				GradationScrollView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				nestedContent.getLayoutParams().height = GradationScrollView.this.getHeight() - headView.getHeight();
				nestedContent.requestLayout();

			}
		});
	}

	@Override
	public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
		return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
	}

	@Override
	public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
		//判断是否需要优先嵌套滑动,解决触摸recyclerview 无法关闭swiperefreshlayout的bug
		if (!dispatchNestedPreScroll(dx, dy, consumed, null)) {
			//嵌套滑动代码
			boolean hiddenTop = dy > 0 && getScrollY() < getChildAt(0).getHeight() - getHeight();
			boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

			if (hiddenTop || showTop) {
				scrollBy(0, dy);
				consumed[1] = dy;
			}
		}
	}

	@Override
	public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
		if (getScrollY() >= getChildAt(0).getHeight() - getHeight())
			return false;
		fling((int) velocityY);
		return true;
	}


}