package com.ckh01.user.ex_1201;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LifeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);

        //액티비티 라이프 사이클
        /*
          1.앱실행했을때
          onCreate  ← 첫실행시 한번만 호출
          onStart
          onResume

          2.홈버튼 일시정지
          onPause
          onStop

          3. 일시정지되었던 앱을 재실행했을때
          onRestart
          onStart
          onResume

          4.뒤로가기로 앱 종료시
          onPause
          onStop
          onDestroy   ← 종료시 한번만 호출
         */

        //로그찍기 (System.out.println 대신)
        Log.i("MY", "onCreate: execute");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MY", "OnCreate : execute");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MY", "onPause: execute");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MY", "onRestart: execute");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MY", "onResume: execute");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MY", "onStart: execute");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MY", "onStop: execute");
    }
}
