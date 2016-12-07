package com.ckh01.user.ex_1207;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class IntentMainActivity extends AppCompatActivity {

    Button btn_date, btn_send;
    EditText edit_name, edit_age, edit_birth;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_age = (EditText) findViewById(R.id.edit_age);
        edit_birth = (EditText) findViewById(R.id.edit_birth);

        btn_date = (Button) findViewById(R.id.btn_date);
        btn_send = (Button) findViewById(R.id.btn_send);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(IntentMainActivity.this, IntentSubActivity.class);

                //서브액티비티로 전달할 값
                String name = edit_name.getText().toString();
                String age = edit_age.getText().toString();
                String birth = edit_birth.getText().toString();

                //Intent객체에 전달할 값을 저장하여 Sub 액티비티로 넘긴다. (전달값으로 클래스는 안된다)
                //※ 메서드에 put이 들어가면 map이다. (키, 값)

                //방법1. (intent 바로 저장후 전송)
/*
                intent.putExtra("myName", name);
                intent.putExtra("myAge", age);
*/

                //방법2. (Bundle에 저장 )
                // Bundle : map형태의 클래스
                //※ 이미 만들어져있는 액티비티 (갤러리, 카메라)에서 파라메터를 받을때는 bundle형태로 넘어온다
                Bundle bundle = new Bundle();
                bundle.putString("myName", name);
                bundle.putString("myAge", age);
                bundle.putString("myBirth", birth);

                //intent에 bundle을 추가  (메서드이름은 putExtras)
                intent.putExtras(bundle);

                //액티비티 전환
                startActivity(intent);
            }
        });

        //캘린더 날짜 받아오기
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("MY", "onClick: ");

                //오늘날짜 가져오기 (캘린더 클래스)
                Calendar now = Calendar.getInstance();
                int y = now.get(Calendar.YEAR);
                int m = now.get(Calendar.MONTH);
                int d = now.get(Calendar.DAY_OF_MONTH);

                //날짜선택 다이얼로그 생성 및 이벤트 리스너 등록
                dialog = new DatePickerDialog(IntentMainActivity.this, dateListener, y, m, d);
                dialog.show();
            }
        });

    }

    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            //month의 경우 1월 -> 0, 2월 -> 1, 1씩 적게 넘어온다.
            //날짜형식을 2016-12-07
            String str = String.format("%d-%02d-%02d", year, month + 1, day);
            edit_birth.setText(str);
        }
    };
}
