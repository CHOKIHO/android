package com.ckh5829.user.mypuzzle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ckh5829.user.mypuzzle.R;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by USER on 2016-12-22.
 */

public class PicGridAdapter extends BaseAdapter {
	private Context context;
	private LinkedList<Map> list;
	private Integer[] mThumbIds = { R.drawable.photo1, R.drawable.photo2 };

	public PicGridAdapter(Context context) {

		this.context = context;
		//this.list = list;
	}

	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		//int rowWidth = (mMetrics.widthPixels) / 3;

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.grid_item, null);
		ImageView imgView = (ImageView)convertView.findViewById(R.id.grid_item_imageView);
		imgView.setImageResource(mThumbIds[position]);
		return convertView;

	}

}
