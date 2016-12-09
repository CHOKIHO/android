package com.ckh01.user.ex_1208;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Canvas01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

    }
}
