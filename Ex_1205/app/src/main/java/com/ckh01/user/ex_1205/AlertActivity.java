package com.ckh01.user.ex_1205;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
    }



/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //뒤로가기 longClick 처리를 할수없다.
        if (event.getAction() == KeyEvent.ACTION_DOWN) {

            //뒤로가기 (back버튼)
            if (keyCode == KeyEvent.KEYCODE_BACK) {


            }
        }

        return false;
    }*/


    //뒤로가기 버튼을 떼었을때 (UP)시 동작
    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        //뒤로가기 버튼 클릭시  AlertDialog 생성

        AlertDialog.Builder dialog = new AlertDialog.Builder(AlertActivity.this);

/*
        dialog.setTitle("종료");
        dialog.setMessage("종료하시겠습니까?");
        dialog.setIcon(R.mipmap.ic_launcher);
*/

        //Builder 특징을 이용해서
        dialog.setTitle("종료")
              .setMessage("종료할까요?")
              .setIcon(R.mipmap.ic_launcher);


        dialog.setPositiveButton("예", click);
        //dialog.setNeutralButton("maybe", click);
        dialog.setNegativeButton("취소", click);

        //다이알로그는 인터페이스다
        dialog.show();

    }

    DialogInterface.OnClickListener click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    finish();
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    Toast.makeText(AlertActivity.this, "취소를 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(AlertActivity.this, "취소를 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

}
