package com.ckh01.user.ex_1206;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    TextView txt_count;
    Button btn_start, btn_stop;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        txt_count = (TextView) findViewById(R.id.txt_count);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //핸들러호출 (쓰레드의 start유사 → Handler의 handleMessage 호출)
                handler.sendEmptyMessage(0);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //핸들러정지
                handler.removeMessages(0);
            }
        });
    }

    //android.os package
    //Handler 클래스 (상속받아서 다양하게 적용)
    Handler handler = new Handler() {

        //메인과 별도로 동작하는 메서드
        @Override
        public void handleMessage(Message msg) {

            //1초후에 재귀호출 (while문과 같은 효과)
            handler.sendEmptyMessageDelayed(0, 1000);
            count++;
            txt_count.setText("" + count);

        }
    };




}
