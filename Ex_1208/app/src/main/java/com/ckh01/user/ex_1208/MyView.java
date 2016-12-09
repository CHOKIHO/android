package com.ckh01.user.ex_1208;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by USER on 2016-12-08.
 */

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);  (필요없다)

        //화면에 그림을 갱신하는 메서드

        //붓 - Paint 클래스
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        //사각형
        Rect rect = new Rect();
        rect.set(100,200,300,400);
        canvas.drawRect(rect, paint);

        //원
        paint.setColor(Color.RED);
        canvas.drawCircle(200, 600, 100, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(600, 600, 100, paint);


        //테두리만 있는 속성의 원
        paint.setStyle(Paint.Style.STROKE);
        for (int i=100;i<300;i++) {
            canvas.drawCircle(i, 500, 100, paint);
        }





    }
}
