package com.ckh01.user.ex_1205;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingActivity extends AppCompatActivity {

    TextView result_txt;
    Button btn, btn_ok;
    Dialog dialog;
    RatingBar rat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        btn = (Button) findViewById(R.id.btn);
        result_txt = (TextView) findViewById(R.id.result_txt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //다이얼로그 생성.
                dialog = new Dialog(RatingActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_rating);

                btn_ok = (Button) dialog.findViewById(R.id.btn_ok);
                rat = (RatingBar) dialog.findViewById(R.id.rating);


                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                //사용자가 별점을 임으로 변경할수 있도록 하는 메서드
                //  true : 읽기전용 (별점을 줄수없음)
                rat.setIsIndicator(false);

                //별 반칸을 0.5점으로 설정
                rat.setStepSize(0.5f);

                //rating의 변경사항 Listener 등록
                rat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    //v : 채워진 별의 점수
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                        //파라미터 v는 반칸당 0.5점씩 증가 (setStepSize에서 설정값)
                        float star = 10.0f / rat.getNumStars();

                        //소수점 한자리까지
                        String str = String.format("%.1f", (star*v));
                        result_txt.setText(str + "/10.0");

                    }
                });

                dialog.show();
            }
        });
    }
}
