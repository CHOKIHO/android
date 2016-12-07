package com.ckh01.user.ex_1207;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class IntentSubActivity extends AppCompatActivity {

    TextView txt_name, txt_age, txt_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_age = (TextView) findViewById(R.id.txt_age);
        txt_birth = (TextView) findViewById(R.id.txt_birth);


        //파라메터로 받은 intnet로 생성.
        Intent intent = getIntent();

/*
        String name = intent.getStringExtra("myName");
        String age = intent.getStringExtra("myAge");
*/

        Bundle bundle = intent.getExtras();

        String name = bundle.getString("myName");
        String age = bundle.getString("myAge");
        String birth = bundle.getString("myBirth");


        txt_name.setText(name);
        txt_age.setText(age);
        txt_birth.setText(birth);

    }
}
