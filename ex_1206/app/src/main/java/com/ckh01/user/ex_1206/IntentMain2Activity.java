package com.ckh01.user.ex_1206;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class IntentMain2Activity extends AppCompatActivity {

    Button btn_next, btn_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main2);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_link = (Button) findViewById(R.id.btn_link);

        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //웹페이지 전환 (※ Intent 클래스 사용)
                /*
                   안드로이드 화면전환 관련 기능을 담당하는 Intent 클래스
                 */
                Intent i = new Intent(Intent.ACTION_VIEW);

                //Intent i = new Intent(Intent.ACTION_CALL);


                //uri는 http://까지 인식 Intent 생성자에 Intent.ACTION_VIEW를 지정해야 web으로 연동
                i.setData(Uri.parse("http://www.naver.com"));
                //i.setData(Uri.parse("tel:01043595829"));

                //실제화면전환 (인텐트의 정보를 가지고 화면 전환)
                startActivity(i);

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();

                //※이동할 액티비티는 AndroidManifest에 반드시 정의되어 있어야함.
                i.setClass(IntentMain2Activity.this, IntentSubActivity.class);

                //Intent i = new Intent(IntentMain2Activity.this, IntentSubActivity.class);
                startActivity(i);
            }
        });
    }
}
