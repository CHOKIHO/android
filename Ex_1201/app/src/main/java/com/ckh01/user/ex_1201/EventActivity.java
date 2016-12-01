package com.ckh01.user.ex_1201;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {
    Button red, blue, toast;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //버튼검색    Button ----down casting ----View
        red = (Button)findViewById(R.id.btn_red);
        blue = (Button)findViewById(R.id.btn_blue);
        toast = (Button)findViewById(R.id.btn_toast);

        //텍스트뷰
        txt = (TextView)findViewById(R.id.txt);

        //event 처리 (Event Listener 감지자 등록)

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //버튼클릭시 호출되는 메서드
                txt.setBackgroundColor(Color.RED);
                txt.setText("RED");
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setBackgroundColor(Color.BLUE);
                txt.setText("BLUE");
            }
        });

        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //※ context : 액티비티 부모 = 화면제어권자를 의미함
                //Toast.makeText(EventActivity.this, "Toast message", Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "Toast message", Toast.LENGTH_LONG).show();
            }
        });

    }
}
