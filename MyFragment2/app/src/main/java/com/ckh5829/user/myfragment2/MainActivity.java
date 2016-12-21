package com.ckh5829.user.myfragment2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		FragmentManager fragmentManager = getFragmentManager();
//		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//		fragmentTransaction.replace(R.id.contents, new BlankFragment().newInstance("Hi", "Fragment"));
//		fragmentTransaction.commit();


	}

	public void selectFlag(View view){
		Fragment fr;

		if(view == findViewById(R.id.fragment1)){
			fr = new BlankFragment().newInstance("Hi", "Fragment#1");
		}else {
			fr = new FragmentTwo().newInstance("Hi", "Fragment#2");

		}

		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.contents, fr);
		fragmentTransaction.commit();
	}

}
