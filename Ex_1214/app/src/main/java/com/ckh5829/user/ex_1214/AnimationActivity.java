package com.ckh5829.user.ex_1214;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AnimationActivity extends AppCompatActivity {

    Button btn01, btn02, btn03, btn04, btn_menu;
    Animation menu_visible_ani, rotate_ani;
    LinearLayout visible_layout;  //버튼 4개를 포함하는 layout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        //애니메이션을 적용할 layout
        visible_layout = (LinearLayout) findViewById(R.id.visible_layout);

        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        btn03 = (Button) findViewById(R.id.btn03);
        btn04 = (Button) findViewById(R.id.btn04);

        //애니메이션을 적용할 button
        btn_menu = (Button) findViewById(R.id.btn_menu);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //메뉴버튼 회전
                rotate_ani = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.menu_rotate);
                btn_menu.startAnimation(rotate_ani);

                if (visible_layout.getVisibility() == View.VISIBLE) {
                    menu_visible_ani = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.menu_invisible);

                    visible_layout.startAnimation(menu_visible_ani);
                    visible_layout.setVisibility(View.INVISIBLE);
                    btn01.setEnabled(false);


                } else {
                    menu_visible_ani = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.menu_visible);

                    visible_layout.startAnimation(menu_visible_ani);
                    visible_layout.setVisibility(View.VISIBLE);
                    btn01.setEnabled(true);

                }
            }
        });

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnimationActivity.this, "홈으로 이동합니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
