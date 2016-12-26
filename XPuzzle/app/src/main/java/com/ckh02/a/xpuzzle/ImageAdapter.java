package com.ckh02.a.xpuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by A on 2016-12-24.
 */

public class ImageAdapter extends BaseAdapter {

    ArrayList<Bitmap> arrBitmap;
    Context context;
    int resource;
    LayoutInflater inflater;



    public ImageAdapter(Context context, int resource) {
        this.context = context;
        this.resource = resource;



        ImageClipper ic = new ImageClipper(context, resource);
        arrBitmap = ic.getArrBitmap();
    }

    @Override
    public int getCount() {
        return arrBitmap !=null ? arrBitmap.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return arrBitmap != null ? arrBitmap.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_image_layout, null);


        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgaeList2);
        imageView.setImageBitmap(arrBitmap.get(position));

        return convertView;
    }
}
