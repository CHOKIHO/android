package com.ckh01.user.ex_1205;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {

    Button btn_show, btn1, btn2;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btn_show = (Button) findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //다이얼로그 생성 (context -> 화면구성)
                dialog = new Dialog(DialogActivity.this);

                //다이얼로그 타이틀바 제거 (setContentView 전에)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //다이얼로그 반투명 효과 제거
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                //다이얼로그 배경 제거
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.setContentView(R.layout.dialog_layout);

                //dialog 안에서.....
                btn1 = (Button)dialog.findViewById(R.id.btn1);
                btn2 = (Button)dialog.findViewById(R.id.btn2);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "메뉴1이 선택되었습니다.", 0).show();
                    }
                });

                //다이얼로그 감추기
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.setTitle("메뉴선택");

                //※뒤로가기, 주변터치로 막기
                dialog.setCancelable(false);

                dialog.show();


            }
        });



    }
}
