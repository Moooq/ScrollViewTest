package com.mooq.scrollviewtest.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by moq.
 * on 2019/5/14
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.TextViewHolder> {

	ArrayList<String> data;

	public RvAdapter() {}

	public RvAdapter(ArrayList<String> data){
		this.data = data;
	}

	@NonNull
	@Override
	public TextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		return new TextViewHolder(new TextView(viewGroup.getContext()));
	}

	@Override
	public void onBindViewHolder(@NonNull TextViewHolder textViewHolder, int i) {
		TextView tv = (TextView)textViewHolder.itemView;
		tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
		String val = data == null? "TextView " + i : data.get(i);
		tv.setText(val);
	}

	@Override
	public int getItemCount() {
		return data==null?300:data.size();
	}

	public static class TextViewHolder extends RecyclerView.ViewHolder{

		public TextViewHolder(View itemView) {
			super(itemView);
		}
	}
}
