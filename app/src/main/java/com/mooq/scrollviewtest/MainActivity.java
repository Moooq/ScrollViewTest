package com.mooq.scrollviewtest;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.mooq.scrollviewtest.view.MNestedScrollView;
import com.mooq.scrollviewtest.view.RvAdapter;

public class MainActivity extends AppCompatActivity {

	RecyclerView rv;
	MNestedScrollView nsv;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nested);

		rv = findViewById(R.id.rv);
		nsv = findViewById(R.id.nsv);


		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setAdapter(new RvAdapter());



		final View rootView = findViewById(android.R.id.content);

		rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

				View topView1 = findViewById(R.id.top_1);
				View topView2 = findViewById(R.id.top_2);


				nsv.setMyScrollHeight(topView1.getHeight());
				nsv.scrollTo(0,topView1.getHeight());
				int rvNewHeight = rootView.getHeight() - topView2.getHeight();

				rv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,rvNewHeight));

			}
		});

	}
}
