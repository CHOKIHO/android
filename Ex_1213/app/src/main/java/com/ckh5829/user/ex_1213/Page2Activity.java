package com.ckh5829.user.ex_1213;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class Page2Activity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //layout을 container로 넣기
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_page2, container,false);


        return layout;
    }
}
