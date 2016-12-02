package com.ckh01.user.ex_1202;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CustomActivity extends AppCompatActivity {

    LinearLayout activity_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        activity_custom = (LinearLayout)findViewById(R.id.activity_custom);

        for (int i=0;i<5;i++) {

            //Button 생성자 (context)
            Button b = new Button(CustomActivity.this);
            //버튼 아이디세팅 (정수값으로...)
            b.setId(i);

            //OnClickListener 등록
            b.setOnClickListener(click);

            //button text
            b.setText("버튼"+(i));

            //width, height
            b.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            b.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

            //weight 주기
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

            params.weight=1;
            b.setLayoutParams(params);
            activity_custom.addView(b);

        }

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() % 2 ==1) {
                Toast.makeText(CustomActivity.this, "홀수 버튼 클릭" + view.getId(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CustomActivity.this, "짝수 버튼 클릭" + view.getId(), Toast.LENGTH_SHORT).show();
            }
        }
    };



}
