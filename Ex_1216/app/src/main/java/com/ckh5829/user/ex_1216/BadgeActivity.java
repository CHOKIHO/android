package com.ckh5829.user.ex_1216;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BadgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        //아이콘 뱃지표시
        InitIconBadge.updateIconBadge(BadgeActivity.this, 3);

        //뱃지표시하지 않음
        //InitIconBadge.updateIconBadge(BadgeActivity.this, 0);

    }
}
