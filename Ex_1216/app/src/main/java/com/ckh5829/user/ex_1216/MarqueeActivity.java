package com.ckh5829.user.ex_1216;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MarqueeActivity extends AppCompatActivity {

    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);

        t1 = (TextView) findViewById(R.id.t1);
        t1.setSelected(true);

    }
}
