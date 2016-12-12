package com.ckh5829.user.ex_1212;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    Button btn_red, btn_green, btn_blue;

    //현재 Activity에서 Main으로 보내기위한 Intent와 Bundle을 준비
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        intent = new Intent();
        bundle = new Bundle();

        btn_red = (Button) findViewById(R.id.btn_red);
        btn_green = (Button) findViewById(R.id.btn_green);
        btn_blue = (Button) findViewById(R.id.btn_blue);

        btn_red.setOnClickListener(click);
        btn_green.setOnClickListener(click);
        btn_blue.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_red:
                    bundle.putInt("selColor", Color.RED);
                    bundle.putString("selText", "RED");
                    break;

                case R.id.btn_green:
                    bundle.putInt("selColor", Color.GREEN);
                    bundle.putString("selText", "GREEN");
                    break;

                case R.id.btn_blue:
                    bundle.putInt("selColor", Color.BLUE);
                    bundle.putString("selText", "BLUE");
                    break;
            }

            intent.putExtras(bundle);

            //intent를 메인으로 전달 (Main onActivityResult로 넘어간다.)
            setResult(RESULT_OK, intent);

            finish();

        }
    };
}
