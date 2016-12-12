package com.ckh5829.user.menudrawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;

public class DrawerLayoutActivity extends Activity {

    Button opendrawer, closedrawer, btn_main, toast;

    DrawerLayout drawerLayout;
    View drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //왼쪽 서랍
        drawView = findViewById(R.id.drawer);

        opendrawer = (Button) findViewById(R.id.open_drawer);
        opendrawer.setOnClickListener(click);

        closedrawer = (Button) findViewById(R.id.closedrawer);
        closedrawer.setOnClickListener(click);




    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.open_drawer:
                    drawerLayout.openDrawer(drawView);

                    break;
                case R.id.closedrawer:
                    //drawerLayout.closeDrawer(drawView);
                    drawerLayout.closeDrawers();
                    break;


            }
        }
    };
}
