package com.ckh5829.user.myfragment3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TailFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tail, container, false);
	}

	public void setText(String text){
		TextView tv = (TextView)getView().findViewById(R.id.textView);
		tv.setText(text);
	}
}
