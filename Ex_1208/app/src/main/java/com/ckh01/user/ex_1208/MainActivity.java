package com.ckh01.user.ex_1208;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_review;

    SharedPreferences prefer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_review = (Button) findViewById(R.id.btn_review);

        prefer = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //CheckBox현재상태 변경
                SharedPreferences.Editor edit = prefer.edit();
                edit.putBoolean("tutorial_check", false);
                edit.commit();

                Intent intent = new Intent(MainActivity.this, TutorialActivity.class);
                intent.putExtra("tutorial_check", "true");

                startActivity(intent);
                finish();

            }
        });
    }
}
