package com.ckh01.user.ex_1201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    ListView list;
    String[] arr = { "java", "android", "jsp", "servlet", "spring", "wearable" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);

        list = (ListView) findViewById(R.id.list);

        //리스트뷰 표시할 Adapter 생성 - ※ 선택 위젯은 adapter가 있어야 event처리가 가능함
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, arr);


        //어댑터를 리스트뷰에 연결
        list.setAdapter(adapter);
    }
}
