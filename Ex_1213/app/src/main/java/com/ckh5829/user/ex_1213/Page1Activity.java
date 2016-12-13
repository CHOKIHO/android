package com.ckh5829.user.ex_1213;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Page1Activity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //layout을 container로 넣기
        //LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_page1, container, false);

        View layout =  inflater.inflate(R.layout.activity_page1, container, false);

        //View view = inflater.inflate(R.layout.fragment_layout, container, 0);

        Button btn1 = (Button) layout.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "프래그먼트#1의 버튼1입니다.", Toast.LENGTH_SHORT).show();
            }
        });


        return layout;
    }
}
