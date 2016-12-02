package com.ckh01.user.ex_1202;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    EditText et;
    Button addBtn;
    ListView myList;
    MyAdapter adapter;
    ArrayList<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        et = (EditText) findViewById(R.id.et);
        addBtn = (Button) findViewById(R.id.addBtn);
        myList = (ListView) findViewById(R.id.myList);

        arr = new ArrayList<>();
        //리스트뷰 항목 구현을 위해 어댑터생성
        adapter = new MyAdapter(ListViewActivity.this, R.layout.list_form, arr, myList);

        //생성된 어댑터 연결
        myList.setAdapter(adapter);

        //버튼 click
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = et.getText().toString();
                arr.add(text);

                if (myList != null) {

                    //어댑터갱신
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
