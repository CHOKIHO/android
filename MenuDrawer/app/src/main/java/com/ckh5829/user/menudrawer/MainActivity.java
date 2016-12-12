package com.ckh5829.user.menudrawer;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

public class MainActivity extends Activity {

    MenuDrawer drawer_right, drawer_left;
    Button close, toast;
    Button open_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

/*
        //슬라이더(서랍)의 속성을 추가
        drawer_right = MenuDrawer.attach(this,
                MenuDrawer.Type.BEHIND,
                Position.RIGHT,  //오른쪽방향으로 열림
                MenuDrawer.MENU_DRAG_WINDOW);

        drawer_left = MenuDrawer.attach(this,
                MenuDrawer.Type.BEHIND,
                Position.LEFT,  //왼쪽방향으로 열림
                MenuDrawer.MENU_DRAG_WINDOW);

        //기본레이아웃지정
        drawer_right.setContentView(R.layout.activity_main);
        drawer_right.setMenuView(R.layout.side_right);

        drawer_left.setContentView(R.layout.activity_main);
        drawer_left.setMenuView(R.layout.side_left);
*/

        //슬라이더(서랍)의 속성을 추가
        //기본레이아웃 지정

        drawer_left = MenuDrawer.attach( this,
                MenuDrawer.Type.BEHIND, Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW );
        drawer_left.setContentView( R.layout.activity_main );
        drawer_left.setMenuView( R.layout.side_left );

        drawer_right = MenuDrawer.attach( this,
                MenuDrawer.Type.BEHIND, Position.RIGHT, MenuDrawer.MENU_DRAG_WINDOW );
        drawer_right.setContentView( R.layout.activity_main );
        drawer_right.setMenuView( R.layout.side_right );

        //화면크기
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        //서랍을 디바이스 너비의 5/6까지 열기
        drawer_right.setMenuSize(dm.widthPixels*5/6);
        drawer_left.setMenuSize(dm.widthPixels*5/6);

        //배젤 터치 영역
        drawer_right.setTouchBezelSize(dm.widthPixels/2);
        //drawer_left.setTouchBezelSize(dm.widthPixels/2);

        //left, right 동일하게
        open_btn = (Button) findViewById(R.id.open_btn);
        close = (Button) findViewById(R.id.btn1);
        toast = (Button) findViewById(R.id.btn2);

        open_btn.setOnClickListener(click);
        close.setOnClickListener(click);
        toast.setOnClickListener(click);

        //메뉴에 이벤트 감지자 등록
        drawer_left.setOnDrawerStateChangeListener(new MenuDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int i, int i1) {
                //서랍현재 상태구별
                if (drawer_left.isMenuVisible()) {  //메뉴 열려있는 상태
                    drawer_right.setTouchMode(MenuDrawer.STATE_CLOSED);
                } else {
                    drawer_right.setTouchMode(MenuDrawer.STATE_DRAGGING);
                }
            }

            @Override
            public void onDrawerSlide(float v, int i) {

            }
        });

    }


    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.open_btn:
                    drawer_right.openMenu(true);
                    break;
                case R.id.btn1:
                    drawer_right.closeMenu();
                    break;
                case R.id.btn2:
                    Toast.makeText(MainActivity.this, "왼쪽 메뉴 Toast", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}

