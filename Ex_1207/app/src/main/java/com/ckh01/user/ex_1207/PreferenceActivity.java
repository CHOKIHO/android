package com.ckh01.user.ex_1207;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreferenceActivity extends AppCompatActivity {

    TextView value;
    Button btn_up, btn_down, btn_reset;
    int num =0;

    //값을 저장하기위한 클래스
    SharedPreferences prefer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        //save, load 준비
        prefer = PreferenceManager.getDefaultSharedPreferences(PreferenceActivity.this);

        //※저장되어있는 프레퍼런스를 로드 (최초실행시 또는 값이 없을경우 디폴트값으로 지정한다)
        num = prefer.getInt("save", 0);

        value = (TextView) findViewById(R.id.value);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_down = (Button) findViewById(R.id.btn_down);
        btn_reset = (Button) findViewById(R.id.btn_reset);

        value.setText(String.valueOf(num));

        btn_up.setOnClickListener(click);
        btn_down.setOnClickListener(click);
        btn_reset.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_up:
                    //++num;
                    //value.setText(Integer.toString(num));
                    //String.valueOf(++n)
                    //value.setText("" + (++num));
                    ++num;
                    break;
                case R.id.btn_down:
                    //--num;
                    //value.setText(Integer.toString(num));
                    //value.setText(String.valueOf(--num));
                    --num;
                    break;
                case R.id.btn_reset:
                    num=0;
                    //value.setText("0");
                    break;
            }
            value.setText(String.valueOf(num));
        }
    };

    @Override
    protected void onDestroy() {

        super.onDestroy();
        //앱 종료시 n값 저장
        SharedPreferences.Editor edit = prefer.edit();

        //중복되는 key값은 마지막이 저장됨 (key 중복을 프로젝트 레벨에서 제거해야함)
        edit.putInt("save", num);
        //물리적으로 commit해야 저장됨
        edit.commit();
    }
}
