package com.ckh01.user.ex_1205;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    // 1.메뉴추가 override 메서드
    //   타이틀바가 존재하는 경우 타이틀 우측에 ... 생성
    //   존재하지않을경우 메뉴처리 방법 필요

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Menu menu (view)에 menu1 붙이기
        getMenuInflater().inflate(R.menu.menu1, menu);

        return true;
    }

    // 2.메뉴이벤트 Listener 설정
    //   재정의만으로 가능

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(getApplicationContext(), "add 클릭", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(getApplicationContext(), "edit 클릭", Toast.LENGTH_SHORT).show();

                break;
            case R.id.menu3:
                //현재 액티비티 종료
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
