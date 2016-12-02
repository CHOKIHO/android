package com.ckh01.user.ex_1202;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by USER on 2016-12-02.
 */

//빨간줄에 커서찍고 alt + enter
public class MyAdapter extends ArrayAdapter<String>{

    //메인에서 생성할때 넘겨받는다.
    Context context;
    ArrayList<String> arr;
    int resource;
    ListView list;
    static int num=0;

    //파라메터중 resource가 중요  R.layout.list_form이 resource로 넘어온다.
    public MyAdapter(Context context, int resource, ArrayList<String> arr, ListView list) {
        //ListView는 넘길수 없다
        super(context, resource, arr);

        this.context = context;
        this.resource = resource;
        this.arr = arr;
        this.list = list;

        //ListView 클릭 이벤트 리스너
        list.setOnItemClickListener(click);
    }

    //인터페이스 객체화
    AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getContext(), arr.get(i).toString(), Toast.LENGTH_SHORT ).show();
        }
    };

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //클래스가 다르다. (ListViewActivity가 아니다. context를 이용할것)
        LayoutInflater linf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //MyAdapter 파라메터
        convertView = linf.inflate(resource, null);

        String str = arr.get(position);

        TextView list_form_text = (TextView)convertView.findViewById(R.id.list_form_txt);
        list_form_text.setText(str);

        TextView  list_form_text1 = (TextView) convertView.findViewById(R.id.list_form_txt1);
        //정수형을 넣을수 없다.
        list_form_text1.setText(""+position);

        Log.d("MY", "getView: call" + num++);

        return convertView;
    }
}
