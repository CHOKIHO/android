package com.ckh01.user.memorygame;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by USER on 2016-12-08.
 */

public class GameView extends View {

    final static int BLANK =0;    //대기상태
    final static int PLAY=1;      //게임진행중

    final static int DELAY=1500;  //1.5초간 clear
    int status;

    ArrayList<Shape> arShape = new ArrayList<>();

    //사이즈, 모양, 색깔
    Random rnd = new Random();
    Activity parent;

    public GameView(Context context) {
        super(context);
        //부모 액티비티 받기
        parent = (Activity) context;
        status = BLANK;
        handler.sendEmptyMessageDelayed(0, DELAY);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            addNewShape();
            status = PLAY;
            invalidate();

            //메인액티비티 타이틀을 단계로 변경
            String title = "stage - " + arShape.size();
            parent.setTitle(title);
        }
    };

    //새로운 도형 추가
    private void addNewShape() {
        Shape shape = new Shape();

        boolean bFindIntersect;  //도형 좌표 중복 여부 파악
        Rect rt = new Rect();

        while (true) {

            int size = rnd.nextInt(101)+50; //50~150사이의 난수

            rt.left = rnd.nextInt(getWidth());//캔버스의너비
            rt.top = rnd.nextInt(getHeight());//캔버스의높이
            rt.right = rt.left+size;
            rt.bottom = rt.top+size;

            //도형이 화면을 벗어날경우
            if (rt.right>= getWidth() || rt.bottom>=getHeight()) {
                continue;
            }

            bFindIntersect = false;

            for (int idx=0; idx< arShape.size(); idx++) {

                if (rt.intersect(arShape.get(idx).rt)) {
                    bFindIntersect = true;
                    break;
                }
            }

            if (!bFindIntersect) break;
        }
        //도형종류
        shape.what = rnd.nextInt(3);

        //도형색갈
        switch (rnd.nextInt(5)) {
            case 0:
                shape.color = Color.WHITE;
                break;
            case 1:
                shape.color = Color.RED;
                break;
            case 2:
                shape.color = Color.BLUE;
                break;
            case 3:
                shape.color = Color.GREEN;
                break;
            case 4:
                shape.color = Color.YELLOW;
                break;

        }

        //겹치는것을 제외한 도형 위치 설정
        shape.rt = rt;
        arShape.add(shape);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //배경 검정색
        canvas.drawColor(Color.BLACK);

        if (status == BLANK) return;


        //매번 전체도형을 다시 그려준다. (arShape ArrayList를 이용)

        for (int i=0;i<arShape.size();i++) {
            Paint paint = new Paint();
            paint.setColor(arShape.get(i).color);
            Rect rt = arShape.get(i).rt;

            switch (arShape.get(i).what) {

                case Shape.RECT:
                    canvas.drawRect(rt, paint);
                    break;
                case Shape.CIRCLE:
                    canvas.drawCircle(rt.left+ rt.width()/2, rt.top + rt.height() /2, rt.width()/2, paint);
                    break;
                case Shape.TRIANGLE:
                    Path path = new Path();

                    path.moveTo(rt.left + rt.width() / 2, rt.top);
                    path.lineTo(rt.left, rt.bottom);
                    path.lineTo(rt.right, rt.bottom);
                    //paint 채움속성으로 나머지선은 안그려도 된다.
                    canvas.drawPath(path, paint);
                    break;
            }
        }
    }


    //도형 터치 구별하는 메서드
    private int findShapeIdx(int x, int y) {

        for (int idx=0; idx<arShape.size();idx++) {
            if (arShape.get(idx).rt.contains(x, y)) {
                return idx;
            }
        }
        return -1;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //int x = (int) event.getX();
        //int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int sel = findShapeIdx((int)event.getX(), (int)event.getY());

            if (sel == -1)  return true;

            if (sel == arShape.size()-1) {
                status = BLANK;
                invalidate();
                handler.sendEmptyMessageDelayed(0, DELAY);

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent);

                builder.setTitle("게임종료");
                builder.setMessage("다시할까요?");

                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //게임초기화
                        arShape.clear();
                        status = BLANK;
                        invalidate();
                        handler.sendEmptyMessageDelayed(0, DELAY);
                    }
                });

                builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       //뷰는 finish가 없다.
                        parent.finish();
                    }
                });

                //뒤로가기
                builder.setCancelable(false);
                builder.show();
            }
        }
        return false;
    }
}
