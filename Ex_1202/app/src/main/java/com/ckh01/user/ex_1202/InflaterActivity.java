package com.ckh01.user.ex_1202;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class InflaterActivity extends AppCompatActivity {

    //xml -> view 화

    LayoutInflater linf;
    View sub;
    RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);
        //------------------------------------------------------------
        //  ※ InFlater 사용하기
        //------------------------------------------------------------
        //sub layout 사용하기
        parent =(RelativeLayout)findViewById(R.id.activity_inflater);

        //xml파일 객체화
         /*
          -LayoutInflater를 사용하려면 서비스 등록
          -주어진 매개변수에 대응되는 안드로이드가 제공하는 시스템-레벨 서비스를 요청
           메모리내 클래스의 인스턴스를 생성하는것이 아니라, 시스템에서 제공하는
           디바이스나 안드로이드 프레임워크내 기능을 다른 애플리케이션과 공유하고자
           시스템으로부터 객체를 얻을때 사용한다.
          */
        linf = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        //LayoutInflator는 .xml을 객체화(View) 시켜주는 클래스
        //R.layout.xml파일명 (xml확장자는 생략)
        //인플레이터를 통해 sub(View) 객체화시켜 parent에 add
        sub = linf.inflate(R.layout.sub_layout, parent);


        //parent.addView(sub);
        //------------------------------------------------------------

        //sub_layout의 btn_event버튼 찾기
        Button event = (Button)sub.findViewById(R.id.btn_event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "event 클릭 !!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
