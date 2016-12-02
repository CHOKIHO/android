package com.ckh01.user.ex_1202;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 2016-12-02.
 */

//빨간줄에 커서찍고 alt + enter
public class MyAdapter extends ArrayAdapter<String>{

    Context context;
    ArrayList<String> arr;
    int resource;

    public MyAdapter(Context context, int resource, ArrayList<String> arr) {
        super(context, resource, arr);

        this.context = context;
        this.resource = resource;
        this.arr = arr;
    }

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
        list_form_text1.setText(str);

        return convertView;
    }
}
