package com.ckh01.user.ex_1206;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class Back2Activity extends AppCompatActivity {


    boolean backPressFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
    }

    @Override
    public void onBackPressed() {


        if (!backPressFlag) {

            Toast.makeText(getApplicationContext(),
                    "Back버튼 한번 더 클릭하면 종료합니다.", 0).show();
            backPressFlag = true;

            //2초후에 handler호출하여 backPressFlag 초기화
            handle.sendEmptyMessageDelayed(0, 2000);

        } else {
            super.onBackPressed();
        }

    }


    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            backPressFlag = false;

        }
    };


}
