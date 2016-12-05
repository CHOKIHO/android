package com.ckh01.user.ex_1205;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VisibleActivity extends AppCompatActivity {

    Button back1, back2, bottom, btn_gone;
    ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);

        back1 = (Button) findViewById(R.id.back1);
        back2 = (Button) findViewById(R.id.back2);
        bottom = (Button) findViewById(R.id.bottom);
        btn_gone = (Button) findViewById(R.id.btn_gone);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);

        back1.setOnClickListener(click);
        back2.setOnClickListener(click);
        bottom.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.back1:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    break;

                case R.id.back2:
                    img1.setVisibility(View.INVISIBLE);
                    img2.setVisibility(View.VISIBLE);

                    break;
                case R.id.bottom:
                    if (btn_gone.getVisibility() == View.GONE) {
                        btn_gone.setVisibility(View.VISIBLE);
                    } else {
                        btn_gone.setVisibility(View.GONE);
                    }
                    break;
            }

        }
    };
}
