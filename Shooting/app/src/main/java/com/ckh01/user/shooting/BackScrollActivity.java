package com.ckh01.user.shooting;

import android.app.Activity;
import android.os.Bundle;

public class BackScrollActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
