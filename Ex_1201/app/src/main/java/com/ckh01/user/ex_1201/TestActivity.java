package com.ckh01.user.ex_1201;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by USER on 2016-12-01.
 */

public class TestActivity extends AppCompatActivity {

    Button btn;
    View.OnClickListener click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        btn = (Button) findViewById(R.id.btn);






    }
}