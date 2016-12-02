package com.ckh01.user.ex_1202;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LotActivity extends AppCompatActivity {

    //액티비티 클래스의 속성으로 설정
    Button btn1, btn2, btn3, btn4, btn_re;
    TextView txt_result;
    Random rnd = new Random();
    int num = 0;
    int chiceNum =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot);

        //ALT + SHIFT  + 마우스클릭
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn_re = (Button)findViewById(R.id.btn_re);
        txt_result = (TextView)findViewById(R.id.txt_result);

        btn1.setOnClickListener(click2);
        btn2.setOnClickListener(click2);
        btn3.setOnClickListener(click2);
        btn4.setOnClickListener(click2);
        btn_re.setOnClickListener(click2);

        num = rnd.nextInt(4)+1;

    }


    //View.OnClickListener 방식 2
    View.OnClickListener click2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btn_re) {
                num = rnd.nextInt(4) + 1;
                txt_result.setText("RESULT");
                Log.d("MY", "num: " + num);
            } else {
                //버튼값 1, 2, 3, 4 받기
                String number = ((Button) view).getText().toString();

                int n = Integer.parseInt(number);

                if (num == n) {
                    txt_result.setText("당첨입니다.");
                } else {
                    txt_result.setText("꽝입니다.");
                }
            }
        }
    };



    //View의 OnClickListener click; 선언과 동시에 익명으로 구현(OnClickListener이 인터페이스임)

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn1:
                    Toast.makeText(LotActivity.this, "1번 버튼 클릭", Toast.LENGTH_SHORT).show();
                    chiceNum=1;
                    break;
                case R.id.btn2:
                    Toast.makeText(LotActivity.this, "2번 버튼 클릭", Toast.LENGTH_SHORT).show();
                    chiceNum=2;
                    break;
                case R.id.btn3:
                    Toast.makeText(LotActivity.this, "3번 버튼 클릭", Toast.LENGTH_SHORT).show();
                    chiceNum=3;
                    break;
                case R.id.btn4:
                    Toast.makeText(LotActivity.this, "4번 버튼 클릭", Toast.LENGTH_SHORT).show();
                    chiceNum=4;
                    break;
                case R.id.btn_re:
                    num = rnd.nextInt(4)+1;
                    Toast.makeText(LotActivity.this, " "+ num, Toast.LENGTH_SHORT).show();
                    txt_result.setText("RESULT");
                    return;
            }

            if (num == chiceNum) {
                txt_result.setText("당첨입니다.");
            } else {
                txt_result.setText("꽝입니다.");
            }
        }
    };


}
