package com.ckh5829.user.myfragment2;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlankFragment extends Fragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;


	public static BlankFragment newInstance(String param1, String param2) {

		BlankFragment fragment = new BlankFragment();

		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);

		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		Log.d("Test","Param1 : "+mParam1 + " Param2 :"+mParam2);

		View view = inflater.inflate(R.layout.fragment_blank, null);

		TextView textView = (TextView) view.findViewById(R.id.fragment1);
		textView.setText("Param1 : "+mParam1 + " Param2 :"+mParam2);


		// Inflate the layout for this fragment
		return view;
	}



}
