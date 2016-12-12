package com.ckh01.user.ex_1208;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class TutorialActivity extends AppCompatActivity {

    CheckBox check;
    Button start;
    SharedPreferences prefer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        check = (CheckBox) findViewById(R.id.check);
        start = (Button) findViewById(R.id.start);

        prefer = PreferenceManager.getDefaultSharedPreferences(TutorialActivity.this);

        //추가학습필요 : ListPreference listPreference = (ListPreference) preference;

        //CheckBox 현재상태 확인
        boolean bcheck = prefer.getBoolean("tutorial_check", false);

        if (bcheck) startActivityTutorial();

        Intent intent = getIntent();
        String tcheck = (String)intent.getStringExtra("tutorial_check");
        Log.d("MY", "onCreate: tcheck = "+ tcheck);

        if (new String("true").equals(tcheck))
        //if (tcheck.equals(new String("true")))
        //if (Objects.equals(tcheck, new String("test")) )

            check.setChecked(true);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //CheckBox현재상태 저장
                SharedPreferences.Editor edit = prefer.edit();
                edit.putBoolean("tutorial_check", check.isChecked());
                edit.commit();

                startActivityTutorial();
            }
        });
    }

    //화면전환메서드 (전환 → 종료)
    private void startActivityTutorial() {
        Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
        //전환
        startActivity(intent);
        //자기자신 종료
        finish();

    }
}
