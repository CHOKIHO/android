package com.ckh5829.user.ex_1212;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultMainActivity extends AppCompatActivity {

    TextView color_view, color_txt;
    Button btn;

    Bundle bundle;

    private static final int SELECT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_main);

        color_view = (TextView) findViewById(R.id.color_view);
        color_txt = (TextView) findViewById(R.id.color_txt);
        btn = (Button) findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ResultMainActivity.this, SubActivity.class);
                startActivityForResult(i, SELECT);

            }
        });
    }

    @Override
    /*
       파라메터를 주고받는 Activity간 호출 방식 (intent+bundle)
       (Main) startActivityForResult →  (Sub) setResult  →  (Main) onActivityResult
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case SELECT:

                    bundle = data.getExtras();
                    int color = bundle.getInt("selColor");
                    String txt = bundle.getString("selText");

                    color_view.setBackgroundColor(color);
                    color_txt.setText(txt);

                    break;
            }

        }
    }
}
