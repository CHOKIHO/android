package com.ckh01.user.ex_1201;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Event2Activity extends AppCompatActivity {

    EditText edit;
    Button btn_start;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event2);

        edit = (EditText)findViewById(R.id.edit);
        btn_start = (Button)findViewById(R.id.btn_start);
        txt = (TextView)findViewById(R.id.txt);


/*        View.OnClickListener v = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회문수판단
                String editstr = edit.getText().toString();
                String tmp = "";

                for (int i=editstr.length();i>0;i--) {

                    tmp += editstr.charAt(i-1);

                }

                if (editstr.equalsIgnoreCase(tmp)) {
                    txt.setText("TRUE");
                } else {
                    txt.setText("FALSE");
                }
            }
        };
        btn_start.setOnClickListener(v);*/


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //회문수판단
                String editstr = edit.getText().toString();
                String tmp = "";

                for (int i=editstr.length();i>0;i--) {

                    tmp += editstr.charAt(i-1);

                }

                if (editstr.equalsIgnoreCase(tmp)) {
                    txt.setText("회문수");
                } else {
                    txt.setText("FALSE");
                }

                Log.d("MY", "onClick: "+tmp.toString());
            }
        });


    }
}
