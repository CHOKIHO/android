package com.ckh5829.user.myfragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by USER on 2016-12-21.
 */

public class MainFragment extends Fragment {
	TextView textView;

	EditText editText;
	Button button;
	TextView resultTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		//레이아웃 전개
		View view = inflater.inflate(R.layout.mainfragment, null);

		//일반 액티비티처럼 처리
		textView = (TextView) view.findViewById(R.id.txt_main);

		editText = (EditText) view.findViewById(R.id.editText);
		button = (Button) view.findViewById(R.id.button);
		resultTextView = (TextView) view.findViewById(R.id.result);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				resultTextView.setText(editText.getText().toString());
			}
		});

		//반드시 변경된 View를 리턴해야함.
		return view;
	}
}
