package com.ckh5829.user.ex_1212;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class CalActivity extends AppCompatActivity {

    Button[] numbers;

    Button plus, sub, multi, div, equal, clear;

    TextView txt_result;

    String resultStr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        txt_result = (TextView) findViewById(R.id.txt_result);

        numbers = new Button[10];

        for (int i=0; i<numbers.length; i++) {
            try {

                numbers[i] = (Button) findViewById(new R.id().getClass().getField("btn"+i).getInt(null));

                numbers[i].setOnClickListener(numberClick);

            } catch (Exception e) {

            }
        }

        plus = (Button) findViewById(R.id.btn10);
        sub = (Button) findViewById(R.id.btn11);
        multi = (Button) findViewById(R.id.btn12);
        div = (Button) findViewById(R.id.btn13);
        equal = (Button) findViewById(R.id.btn14);
        clear = (Button) findViewById(R.id.btn15);

        plus.setOnClickListener(signClick);
        sub.setOnClickListener(signClick);
        multi.setOnClickListener(signClick);
        div.setOnClickListener(signClick);
        equal.setOnClickListener(signClick);
        clear.setOnClickListener(signClick);


    }

    View.OnClickListener numberClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            resultStr += ((Button) view).getText().toString();
            txt_result.setText(resultStr);

        }
    };

    View.OnClickListener signClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view != equal) {
                resultStr += " "+((Button)view).getText().toString()+" ";
                txt_result.setText(resultStr);

                if (view == clear) {
                    resultStr = "";
                    txt_result.setText(resultStr);
                }

            } else {
                //=버튼처리
                //문자열을 수식으로 변환 (콜백필요)
                JsEvaluator evaluator = new JsEvaluator(CalActivity.this);

                evaluator.evaluate(resultStr, new JsCallback() {
                    @Override
                    public void onResult(String s) {
                        txt_result.setText(s);

                    }
                });
            }
        }
    };

}
