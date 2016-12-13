package com.ckh01.user.ex_1205;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    EditText et;
    TextView txt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        et = (EditText) findViewById(R.id.et);
        txt = (TextView) findViewById(R.id.txt);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = et.getText().toString();

                switch (result) {
                    case "RED":
                    case "red":
                        //txt.setBackgroundColor(Color.RED);
                        txt.setTextColor(Color.RED);
                        break;
                    case "BLUE":
                    case "blue":
                        //txt.setBackgroundColor(Color.BLUE);
                        txt.setTextColor(Color.BLUE);
                        break;
                }

            }
        });
    }
}
