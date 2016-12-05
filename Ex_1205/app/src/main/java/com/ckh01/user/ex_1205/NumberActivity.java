package com.ckh01.user.ex_1205;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NumberActivity extends AppCompatActivity {

    Button btn_start, btn_yes, btn_no;
    TextView txt_showNumber;
    String strNums;

    int[][] sarr = { {4, 5, 6, 7, 12, 13, 14, 15, 20, 21, 22, 23, 28, 29, 30},
                     {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
                     {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29},
                     {8, 9, 10, 11, 12, 13, 14, 15, 24, 25, 26, 27, 28, 29, 30},
                     {2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 22, 23, 26, 27, 30} };

    int result = 0;
    int level = 0;
    boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_no = (Button) findViewById(R.id.btn_no);

        txt_showNumber = (TextView) findViewById(R.id.txt_showNumber);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level=0;
                result=0;
                flag= true;
                txt_showNumber.setText(printNumber(level));
            }
        });

        btn_yes.setOnClickListener(click);
        btn_no.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (!flag) {
                txt_showNumber.setText("시작버튼을\n 먼저 클릭하세요");
                return;
            }

            switch (view.getId()) {
                case R.id.btn_yes:
                    Log.d("MY", "onClick: btn_yes");

                    if (level==0) {
                        result +=4;
                    } else if (level == 1) {
                        result +=16;
                    } else if (level == 2) {
                        result +=1;
                    } else if (level == 3) {
                        result += 8;
                    } else if (level == 4) {
                        result +=2;
                    }
                    break;
                case R.id.btn_no:
                    break;

            }
            if (level == sarr.length-1) {
                if(result == 0 || result == 31){
                    txt_showNumber.setText("잘못 선택한 문항이 있습니다.");
                }else{
                    txt_showNumber.setText("당신이 생각한 숫자\n " + result + "입니다.");
                }

                level=0;
                flag = false;

            } else {
                txt_showNumber.setText(printNumber(++level));
            }

        }
    };

    private String printNumber(int num) {
        strNums="";
        for (int i =0; i<sarr[num].length;i++) {
            if (i%5==0) strNums += "\n";
            if (sarr[num][i]<10) {
                strNums += "0" + Integer.toString(sarr[num][i]) + " ";
            } else {
                strNums += Integer.toString(sarr[num][i]) + " ";
            }
        }
        return strNums;
    }
}
