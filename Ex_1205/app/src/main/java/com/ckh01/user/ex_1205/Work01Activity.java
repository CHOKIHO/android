package com.ckh01.user.ex_1205;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Work01Activity extends AppCompatActivity {

    Dialog dialog;
    ImageView img;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work01);

        img = (ImageView) findViewById(R.id.img);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.diagog:

                //다이얼로그 생성
                dialog = new Dialog(Work01Activity.this);
                dialog.setContentView(R.layout.dialog_visible);
                dialog.setTitle("이미지 메뉴");

                final Button visible = (Button) dialog.findViewById(R.id.btn_visible);

                if (img.getVisibility() == View.VISIBLE) {
                    visible.setText("invisible");
                    flag=true;
                } else {
                    visible.setText("visible");
                    flag=false;
                }

                visible.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (flag) {
                            img.setVisibility(View.INVISIBLE);

                        } else {
                            img.setVisibility(View.VISIBLE);
                        }

                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
