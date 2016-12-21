package com.ckh5829.user.myfragment3;

import android.app.Activity;
import android.os.Bundle;



public class MainActivity extends Activity implements HeadFragment.CustomOnClickListener{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// HeadFragment.CustomOnClickListener의 구현
	@Override
	public void onClicked(int id) {

		TailFragment tailFragment = (TailFragment)
				getFragmentManager().findFragmentById(R.id.tail_fragment);

		switch(id){
			case R.id.headBt1:
				tailFragment.setText("Button1 is Clicked");
				break;
			case R.id.headBt2:
				tailFragment.setText("Button2 is Clicked");
				break;
		}
	}

}
