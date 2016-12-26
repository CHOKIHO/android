package com.ckh02.a.xpuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by A on 2016-12-24.
 */

public class ImageClipper extends View {

    int width, height;
    int left, top;
    int orgW, orgH;
    int picW, picH;
    Bitmap imgPic[][] = new Bitmap[2][2];
    Bitmap imgBack, imgOrg;

    ArrayList<Bitmap> arrBitmap;

    Context context;
    int resources;
    int splitNumber;



    public ImageClipper(Context context, int resources) {
        super(context);


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;

        arrBitmap = new ArrayList<Bitmap>();
        //ArrayList<Bitmap> picArr = new ArrayList<Bitmap>();


        //이미지 불러오기
        imgOrg = BitmapFactory.decodeResource(context.getResources(), resources);

        //해당 이미지를 width,height만큼 늘려서 imgBack에 저장
        imgOrg = Bitmap.createScaledBitmap(imgOrg, width-width/10, height-height/10, true);

        orgW = imgOrg.getWidth();
        orgH = imgOrg.getHeight();

        picW = orgW / 2;
        picH = orgH / 2;

        left = (width - orgW) / 2;
        top = (height - orgH) / 2;

        //사진자르기
        for (int i=0;i<2;i++) {
            for (int j=0;j<2;j++) {
                //imgPic[i][j] = Bitmap.createBitmap(imgOrg, j * picW, i * picH, picW, picH);
                Bitmap bitmap = Bitmap.createBitmap(imgOrg, j * picW, i * picH, picW, picH);
                arrBitmap.add(bitmap);
            }
        }

    }

    public ArrayList<Bitmap> getArrBitmap() {
        return arrBitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        EmbossMaskFilter emboss = new EmbossMaskFilter(new float[]{1, 1, 1}, 05f, 1, 1);
        paint.setMaskFilter(emboss);

        for (int i=0;i<2;i++) {
            for (int j=0;j<2;j++) {
                canvas.drawBitmap(imgPic[i][j], left + j * picW, top + i * picH, paint);
            }
        }
    }

}
