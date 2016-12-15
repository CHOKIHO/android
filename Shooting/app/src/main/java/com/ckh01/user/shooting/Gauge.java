package com.ckh01.user.shooting;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

/**
 * Created by USER on 2016-12-15.
 */

public class Gauge {

    //에너지가 모두 소진되면 true로 변경
    private boolean isTimeout;
    //게이지비트맵
    Bitmap imgGauge;
    //게이지 시작위치
    int startX;
    //그림그리기 canvas와 paint 객체 (전경,배경)
    Canvas myCanvas;
    Paint gPaint, bPaint;
    //게이지배경 좌표
    int x, y;
    //게이지 너비 높이
    int width, height;
    //에너지 감소량
    int step;

    public Gauge(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        gPaint = new Paint();
        bPaint = new Paint();

        myCanvas = new Canvas();

        //파라메터 :  ARGB : 투명값 지정 가능, RGB_565 : 투명값 지정 불가능
        imgGauge = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //투명속성 가능

        myCanvas.setBitmap(imgGauge);

        //게이지 그라데이션
        //1 : 그라데이션 시작점 x
        //2 : 그라데이션 시작점 y
        //3 : 그라데이션 끝점 x
        //4 : 그라데이션 끝점 y
        //5 : 시작 색깔
        //6 : 끝 색깔
        //7 : 그라데이션 패턴 (CLAMP:무늬끝부분반복, MIRROR:무늬반사반복, REPEAT:같은무늬반복)
        gPaint.setShader(new LinearGradient(0, 0, width, height, Color.BLUE, Color.RED, Shader.TileMode.CLAMP));

        //게이지배경
        bPaint.setColor(Color.WHITE);
    }

    //에너지 감소량
    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    //에너지 소진 여부 판단
    public boolean isTimeout() {
        return isTimeout;
    }

    //캔버스에 게이지를 그리는 메서드
    private void printGauge() {
        myCanvas.drawRect(0, 0, width, height, bPaint);
        myCanvas.drawRect(startX, 0, width, height, gPaint);
    }

    //에너지 초기화
    public void initGauge() {
        startX =0;
        isTimeout = false;
        printGauge();
    }

    //에너지 연산
    public void progress() {
        //게임종료
        if (startX >= width) {
            isTimeout = true;
        }

        if (!isTimeout) {
            startX += step;

            //startX가 변경분 반영
            printGauge();
        }
    }


}
