package com.ckh01.user.ex_1208;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by USER on 2016-12-08.
 */

public class MyView2 extends View {

    Paint paint = new Paint();
    //그리기 위한 좌표정보를 모아둠
    Path path = new Path();
    int x, y;

    public MyView2(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        //path가 기억하는 좌표를 화면에 표시
        canvas.drawPath(path, paint);
    }

    //터치 Listener

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                //화면에 터치된 좌표를 기억
                path.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                //움직인 좌표
                x = (int) event.getX();
                y = (int) event.getY();

                path.lineTo(x, y);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        //바뀐 path로 draw 호출
        invalidate();

        return true;
    }
}
