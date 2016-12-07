package com.ckh01.user.ex_1206;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BackButtonActivity extends AppCompatActivity {

    int num=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_button);
    }

    @Override
    public void onBackPressed() {

        if (num!=3) {
            finish();

        } else {
            Toast.makeText(getApplicationContext(),
                    "뒤로가기를 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();

            timer.sendEmptyMessage(0);

        }

        //super.onBackPressed();
    }

    //3초 핸들러
    Handler timer = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            timer.sendEmptyMessageDelayed(0, 1000);
            if(num>0) {
                --num;
            }else {
                num = 3;
                timer.removeMessages(0);
            }
        }
    };
}
