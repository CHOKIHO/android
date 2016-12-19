package com.ckh5829.user.forwarder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 2016-12-19.
 */

public class NumsAdapter extends ArrayAdapter<String> {

    private  Context context;
    private int resource;
    private ArrayList<String> arrs;
    private ListView list;


    public NumsAdapter(Context context, int resource, ArrayList<String> arrs, ListView list) {
        super(context, resource, arrs);

        this.context = context;
        this.resource = resource;
        this.arrs = arrs;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(this.resource, null);

        TextView txt_fwdNum = (TextView) convertView.findViewById(R.id.txt_fwdNum);

        String nums = arrs.get(position);
        txt_fwdNum.setText(nums);

        return convertView;
    }
}
