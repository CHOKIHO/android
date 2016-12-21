package com.ckh01.user.ex_1202;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.Touch;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TouchEventActivity extends AppCompatActivity {

    Button btn;
    TextView txt_view, box;
    boolean isCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_touch_event);

        btn = (Button)findViewById(R.id.btn_event);
        txt_view = (TextView)findViewById(R.id.txt_view);
        box = (TextView)findViewById(R.id.box);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck==true) {
                    box.setText("터치이벤트 불가능");
                }else {
                    box.setText("터치이벤트 가능");
                }
                isCheck = !isCheck;
                Log.d("MY", "isCheck value : " + isCheck);
            }
        });


        //box 터치이벤트 감지자 등록
        box.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String result="";
                int x=0, y=0;

                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        result="down!!";
                        break;
                    case MotionEvent.ACTION_UP:
                        result="up!!";
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x = (int)motionEvent.getX();
                        y = (int)motionEvent.getY();
                        result = "x :" + x + ", y :" + y;
                        break;
                }

                txt_view.setText(result);

                //return값은 true일때만 적용되지만,
                //ACTION_DOWN의 경우 리턴값에 관계없이 적용된다. (이벤트 발생여부확인)
                return isCheck;
            }
        });

    }
}
