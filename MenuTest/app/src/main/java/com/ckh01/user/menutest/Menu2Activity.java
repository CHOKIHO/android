package com.ckh01.user.menutest;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Menu2Activity extends Activity {

    Button menu_show_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        menu_show_btn = (Button) findViewById(R.id.menu_show_btn);

        menu_show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //팝업메뉴
                //팝업메뉴를 띄울 기준뷰 view(여기서는 Button)
                PopupMenu popup = new PopupMenu(Menu2Activity.this, view);

                //팝업메뉴가 참조할 리소스 파일 등록
                getMenuInflater().inflate(R.menu.menu1, popup.getMenu());

                //Popup 메뉴 클릭이벤트 Listener
                popup.setOnMenuItemClickListener(popupClick);

/*
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
*/
/*
                        switch (menuItem.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(getApplicationContext(), "메뉴1이 선택되었습니다.", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu2:
                                Toast.makeText(getApplicationContext(), "메뉴2가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu3:
                                Toast.makeText(getApplicationContext(), "메뉴3이 선택되었습니다.", Toast.LENGTH_SHORT).show();
                                break;
                        }
*//*

                        Toast.makeText(getApplicationContext(), menuItem.getTitle() +" 선택되었습니다.", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });
*/

                popup.show();

            }

            PopupMenu.OnMenuItemClickListener popupClick = new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(getApplicationContext(), menuItem.getTitle() +" 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    //True/False에 상관없이 동작한다 (true일경우 2번 실행될수도 있다. 여기선 false가 안전)
                    return false;
                }
            };
        });





    }


}
