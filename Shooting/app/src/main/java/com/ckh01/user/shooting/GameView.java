package com.ckh01.user.shooting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by USER on 2016-12-09.
 */

public class GameView extends View {

    Context context;
    int width, height; // 현재뷰의 너비와 높이
    Bitmap back1, back2, unit;

    //각 배경으 y좌표를 저장하는 변수
    int back1_y, back2_y;
    //전투기 크기와 좌표
    int unitW, unitH, unitX, unitY;

    //이미지추가를 쉽게하기위해........
    Canvas canvas;

    public GameView(Context context) {
        super(context);
        this.context = context;

        //현재뷰의 크기를 알아내기 (API13부터 deprecated)
        //width = getWidth();
        //height = getHeight();

        /*
        WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
        height = dm.heightPixels;
        */

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        //필요한 이미지들 (전투기, 배경) 원본 이미지 로드
        //                decode(png->bitmap), getResources == res 폴더와 같음
        back1 = BitmapFactory.decodeResource(getResources(), R.mipmap.space);
        back2 = BitmapFactory.decodeResource(getResources(), R.mipmap.space);
        unit = BitmapFactory.decodeResource(getResources(), R.mipmap.gunship);

        //배경의 y좌표 설정 (배경이미지 2장 배열)
        back1_y =0;
        back2_y =-height;

        init();

        handler.sendEmptyMessage(0);
    }

    private void init() {

        //이미지 사이즈 설정 및 좌표 초기화 메서드  <-- 휴대폰 사이즈 맞게 리사이징
        // 두이미지간 연결부분 개선위해 height+10을 사용
        back1 = Bitmap.createScaledBitmap(back1, width, height+10, true);
        back2 = Bitmap.createScaledBitmap(back2, width, height+10, true);

        //전투기의 크기도 휴대폰에 맞게 변경후 unit 재적용
        unitW = width / 10;
        unitH = height / 12;

        //unit 사이즈 재적용
        unit = Bitmap.createScaledBitmap(unit, unitW, unitH, true);

        //전투기 초기 위치
        unitX = width / 2 - unitW / 2;
        unitY = height - (unitH + 100);

    }

    //이미지, 적등의 이동을 진행(화면갱신)하는 핸들러
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            invalidate();
            handler.sendEmptyMessageDelayed(0, 10);
        }
    };


    //캔버스에 이미지를 추가하는 메서드
    private void progressState() {

        //배경1 (0,0)에 그리기
        canvas.drawBitmap(back1, 0, back1_y, null);

        //배경2 (0, -height)에 그리기
        canvas.drawBitmap(back2, 0, back2_y, null);

        //전투기 그리기
        canvas.drawBitmap(unit, unitX, unitY, null);

        //배경움직이기
        scrollBack();
    }

    private void scrollBack() {
        //배경2장을 동시에 내린다.
        back1_y +=5;
        back2_y +=5;

        //back1 화면을 벗어날경우 다시 위쪽으로 올리기
        if (back1_y >= height) {
            back1_y = -height;
        }

        //back2 화면을 벗어날경우 다시 위쪽으로 올리기
        if (back2_y >= height) {
            back2_y = -height;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        progressState();

    }


}























