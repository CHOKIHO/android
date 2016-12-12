package com.ckh01.user.ex_1208;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Canvas02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyView2 view2 = new MyView2(this);
        setContentView(view2);
    }
}
