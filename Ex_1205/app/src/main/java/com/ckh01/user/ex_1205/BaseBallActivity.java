package com.ckh01.user.ex_1205;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class BaseBallActivity extends AppCompatActivity {

    TextView input_txt, result_txt;
    ScrollView scroll;
    Button[] btns;
    Button btn_c, btn_start;

    //com 난수
    int com1=0, com2=0, com3=0;
    //사용자
    int user1=0, user2=0, user3=0;
    //세글자 제한
    int inputcount=1;

    String inputStr="", resultStr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ball);

        btns = new Button[9];

        for (int i=0;i<btns.length;i++) {
            try {
                //Resource 파일을 문자열로 접근
                btns[i] = (Button) findViewById(new R.id().getClass().getField("btn"+(i+1)).getInt(null));
                btns[i].setOnClickListener(myClick);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        input_txt = (TextView) findViewById(R.id.input_txt);
        result_txt = (TextView) findViewById(R.id.result_txt);

        btn_c = (Button) findViewById(R.id.btn_c);
        btn_c.setOnClickListener(cs);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(cs);

        scroll = (ScrollView) findViewById(R.id.scroll);

        //컴퓨터 난수 (ascii 값으로 나중에 비교를 쉽게처리)
        do {
            com1 = new Random().nextInt('9'-'1' + 1 )+ '1';
            com2 = new Random().nextInt('9'-'1' + 1 )+ '1';
            com3 = new Random().nextInt('9'-'1' + 1 )+ '1';

        } while(com1==com2 || com1==com3 || com2==com3);

    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (inputcount <= 3) {
                input_txt.setText(((Button) view).getText().toString());
                inputStr += input_txt.getText().toString();
                input_txt.setText(inputStr);

                //중복입력금지
                //((Button)view).setEnabled(false);
                view.setEnabled(false);

                inputcount++;
            }
        }
    };

    View.OnClickListener cs = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_c:
                    screenInit();
                    break;
                case R.id.btn_start:

                    //버튼 (start, restart)
                    if (btn_start.getText().toString().equalsIgnoreCase("시작")) {

                        //경우의수 판별
                        if (inputcount>3) {

                            user1 = inputStr.charAt(0);
                            user2 = inputStr.charAt(1);
                            user3 = inputStr.charAt(2);

                            int strike=0, ball=0;

                            if (user1 == com1) strike++;
                            else if (user1 == com2 || user1==com3) ball++;

                            if (user2 == com2) strike++;
                            else if (user2 == com1 || user2==com3) ball++;

                            if (user3 == com3) strike++;
                            else if (user3 == com1 || user3==com2) ball++;

                            //정답
                            if (strike==3) {
                                inputStr = input_txt.getText().toString() + "빙고!";
                                scroll.setBackgroundResource(R.mipmap.img2);
                                btn_start.setText("재시작");
                            } else {
                                if (strike>0 || ball>0) {
                                    inputStr = input_txt.getText().toString() + " - "
                                            + strike +"스트라이크, " + ball + "볼";
                                } else {
                                    inputStr = input_txt.getText().toString() + " - 아웃!!";
                                }

                                resultStr += inputStr + "\n";
                                result_txt.setText(resultStr);
                                screenInit();
                            }

                        } else {
                            Log.d("MY", "onClick: 세글자입력");
                            Toast.makeText(getApplicationContext(), "세글자를 입력하세요", Toast.LENGTH_SHORT).show();
                            screenInit();
                        }

                    } else {
                        //게임다시시작
                        //Intent를 통한 게임 재시작

                        Intent i = new Intent (BaseBallActivity.this, BaseBallActivity.class);
                        startActivity(i);
                        finish();

                    }

                    break;
            }

        }
    };

    private void screenInit() {
        inputStr="";
        input_txt.setText(inputStr);

        inputcount=1;

        for (int i=0;i<btns.length;i++) {
            btns[i].setEnabled(true);
        }
    }

}
