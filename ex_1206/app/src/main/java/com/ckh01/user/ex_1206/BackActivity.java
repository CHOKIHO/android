package com.ckh01.user.ex_1206;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class BackActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
    }

    @Override
    public void onBackPressed() {

        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        Log.d("MY", "tempTime: "+tempTime);
        Log.d("MY", "intervalTime: "+intervalTime);

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        }
    }

}
