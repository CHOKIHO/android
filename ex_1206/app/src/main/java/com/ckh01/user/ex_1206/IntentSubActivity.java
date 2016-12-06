package com.ckh01.user.ex_1206;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class IntentSubActivity extends AppCompatActivity {

    Button btn_pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        btn_pre = (Button) findViewById(R.id.btn_pre);

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //※ 중복문제가 발생한다.
                //Intent i = new Intent(IntentSubActivity.this, IntentMain2Activity.class);
                //startActivity(i);

                //액티비티가 2개의 경우 가능한방법
                //finish();

                Intent intent = new Intent(IntentSubActivity.this, IntentMain2Activity.class);

                //중복호출 방지를 위한 Flag 적용
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);

/*
                //※ 액티비티가 3개 이상일경우엔 Intent_Flag를 효과적이다.

                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP)
                intent.addFlag(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                -FLAG_ACTIVITY_CLEAR_TOP (해당 액티비티 clear)
                -FLAG_ACTIVITY_SINGLE_TOP (기존에 액티비티가 존재하면 재사용)

                FLAG_ACTIVITY_BROUGHT_TO_FRONT
                FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
                FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                FLAG_ACTIVITY_FORWARD_RESULT
                FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY
                FLAG_ACTIVITY_MULTIPLE_TASK
                FLAG_ACTIVITY_NEW_TASK
                FLAG_ACTIVITY_NO_ANIMATION
                FLAG_ACTIVITY_NO_HISTORY
                FLAG_ACTIVITY_NO_USER_ACTION
                FLAG_ACTIVITY_REORDER_TO_FRONT

*/



            }
        });
    }
}
