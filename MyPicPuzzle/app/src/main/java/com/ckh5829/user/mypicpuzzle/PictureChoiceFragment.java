package com.ckh5829.user.mypicpuzzle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class PictureChoiceFragment extends Fragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	GridView gridView;

	public PictureChoiceFragment() {

	}

	public static PictureChoiceFragment newInstance(String param1, String param2) {
		PictureChoiceFragment fragment = new PictureChoiceFragment();
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
		// Inflate the layout for this fragment

		//레이아웃 전개
		View view = inflater.inflate(R.layout.fragment_picture_choice, null);

		//GridView
		gridView = (GridView) view.findViewById(R.id.gridView);


		gridView.setAdapter(new PictureChoiceAdapter(getActivity().getApplicationContext()));
		gridView.setOnItemClickListener(gridviewOnItemClickListener);


		return view;
	}

	private GridView.OnItemClickListener gridviewOnItemClickListener
			= new GridView.OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		                        long arg3) {

			Toast.makeText(getActivity().getApplicationContext(),
					arg0.getAdapter().getItem(arg2).toString(),
					Toast.LENGTH_LONG).show();

			//Activity로 이벤트호출
			customListener.onClicked(1);


		}
	};


	// Activity 로 데이터를 전달할 커스텀 리스너의 인터페이스
	public interface CustomOnClickListener{
		public void onClicked(int id);
	}

	// Activity 로 데이터를 전달할 커스텀 리스너 선언
	private CustomOnClickListener customListener;



	View.OnClickListener onClickListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			// 버튼이 클릭 될 때마다 Activity의 커스텀 리스너를 호출함
			customListener.onClicked(v.getId());
		}
	};

	// Activity 로 데이터를 전달할 커스텀 리스너를 연결
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		customListener = (CustomOnClickListener)activity;
	}
}
