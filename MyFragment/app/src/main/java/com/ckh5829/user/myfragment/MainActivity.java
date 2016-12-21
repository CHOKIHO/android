package com.ckh5829.user.myfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
			FragmentManager가 가지고 있는 FragmentTransaction을 통해
	        fragment 관련 작업을 수행 할 수 있다.
		 */

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		/*
		    int containerViewId에는 프래그먼트가 들어가야 될 엑티비티의 레이아웃의 id를 필요로하며,
		    fragmet자리에는 fragment를 추가.
		 */

		fragmentTransaction.replace(R.id.contents, new MainFragment());
		fragmentTransaction.commit();

	}
}
