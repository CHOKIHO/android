package com.ckh01.user.menutest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu1Activity extends Activity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //메뉴실행
                openOptionsMenu();
                //메뉴닫기
                //closeOptionsMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater().inflate(R.menu.menu1, menu);

        menu.add(0,1,0,"배경색(빨강)");
        menu.add(0,2,0,"배경색(초록)");
        menu.add(0,3,0,"배경색(파랑)");

        SubMenu sMenu = menu.addSubMenu("버튼변경 >>");
        sMenu.add(0,4,0,"버튼 45도 회전");
        sMenu.add(0, 5, 0, "버튼 2배 확대");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(getApplicationContext(), "메뉴1 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(getApplicationContext(), "메뉴2 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(getApplicationContext(), "메뉴3 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
