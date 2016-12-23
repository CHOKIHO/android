package com.ckh01.user.shooting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by USER on 2016-12-09.
 */

public class GameView extends View implements SensorEventListener{

    Context context;
    int width, height; // 현재뷰의 너비와 높이
    Bitmap back1, back2, unit;

    //각 배경의 y좌표를 저장하는 변수 (배경 스크롤)
    int back1_y, back2_y;

    //전투기 크기와 좌표
    int unitW, unitH, unitX, unitY;

    //이미지 추가를 쉽게 하기 위해........
    Canvas canvas;

    //토끼이미지와 이미지 제어변수
    Bitmap[] rabbit = new Bitmap[2];
    int imageIndex = 0;
    int rabbitX=100;
    int rabbitY=100;
    int rabbitW, rabbitH;
    int speedX = 5;
    int speedY = 5;

    //센서관련
    SensorManager sensorM;

    //사운드관련
    SoundManager soundManager;

    //게이지관련
    Gauge gauge;
    int gaugeX, gaugeY, gaugeW, gaugeH;

    //전투기 life
    int[] uLifeX = new int[2];
    int[] uLifeY = new int[2];
    int uLifeW, uLifeH;
    Bitmap[] uLife = new Bitmap[2];
    boolean unitCrash; //전투기와 토끼가 충돌했을때
    int lifeGauge = 2; //생명
    int safety;        //무적시간

    //게임종료후 다이얼로그 중복생성 방지 변수
    boolean finish;

    //핸들러 제어
    int h_int;

    //미사일
    Bitmap missile;
    ArrayList<Missile> missList = new ArrayList<>();
    int missileX, missileY, missileW, missileH ;

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

        rabbit[0] = BitmapFactory.decodeResource(getResources(), R.mipmap.rabbit);
        rabbit[1] = BitmapFactory.decodeResource(getResources(), R.mipmap.rabbit2);

        missile = BitmapFactory.decodeResource(getResources(), R.mipmap.missile);

        //Life이미지
        uLife[0] = BitmapFactory.decodeResource(getResources(), R.mipmap.gunship);
        uLife[1] = BitmapFactory.decodeResource(getResources(), R.mipmap.gunship);

        //배경의 y좌표 설정 (배경이미지 2장 배열)
        back1_y =0;
        back2_y =-height;

        init(); //초기화

        initSensor();  //센서 초기화

        initSound();   //사운드 초기화

        initGauge();   //게이지 초기화


        handler.sendEmptyMessage(0);
    }

    private void initGauge() {
        gaugeW = (width/10)*9;  //휴대폰너비의 9/10 사이즈
        gaugeH = height/20;

        //게이지 좌표
        gaugeX = (width-gaugeW)/2;
        gaugeY = gaugeX;

        //게이지 객체 생성 및 초기화
        gauge = new Gauge(gaugeX, gaugeY, gaugeW, gaugeH);
        gauge.initGauge();

        gauge.setStep(30);  //충돌발생시 깍일점수




    }


    private void initSound() {
        soundManager = SoundManager.getInstance();
        soundManager.init(context);
        //사운드 등록
        soundManager.addSound(1, R.raw.scream);
        soundManager.addSound(2, R.raw.shot);
    }

    private void init() {

        //비트맵이미지 스케일작업
        /*
           이미지 사이즈 설정 및 좌표 초기화 메서드  <-- 휴대폰 사이즈 맞게 리사이징
           두이미지간 연결부분 개선위해 height+10을 사용
        */
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

        //토끼이미지 크기설정
        for (int i=0;i<rabbit.length;i++) {
            rabbit[i] = Bitmap.createScaledBitmap(rabbit[i], width / 6, height / 6, true);
        }

        //충돌여부파악
        rabbitW = rabbit[0].getWidth();
        rabbitH = rabbit[0].getHeight();

        //미사일 이미지 사이즈 설정
        missile = Bitmap.createScaledBitmap(missile, unitW / 5, unitH, true);  //미사일크기를 비행기에 1/5로...
        missileW = missile.getWidth();
        missileH = missile.getHeight();

        //전투기 Life이미지 설정

        for (int i=0;i<uLife.length;i++) {
            uLife[i] = Bitmap.createScaledBitmap(uLife[i], unitW / 2, unitH / 2, true);
        }
        uLifeW = uLife[0].getWidth();
        uLifeH = uLife[0].getHeight();

        uLifeX[0] = 0;
        uLifeY[0] = height - uLifeH;

        uLifeX[1] = uLifeX[0] + uLifeW;
        uLifeY[1] = height - uLifeH;

    }

    //토끼를 움직이는 메서드
    private void moveRabbit() {
        rabbitX += speedX;
        rabbitY += speedY;

        //벽에 부딛쳤을때 방향 전환
        if (rabbitX <= 0) {
            speedX *= -1;
        } else if (rabbitX >= width - rabbitW) {
            speedX *= -1;
        }

        if (rabbitY <= 0) {
            speedY *= -1;
        } else if (rabbitY >= height - rabbitH) {
            speedY *= -1;
        }
    }

    private void initSensor() {

        sensorM = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        //센서종류 결정 및 이벤트 리스너 등록
        sensorM.registerListener(this, sensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    //이미지, 적등의 이동을 진행(화면갱신)하는 핸들러
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            h_int++;

            if (h_int % 10 == 0) {
                imageIndex++;

                if (imageIndex==2) {
                    imageIndex=0;
                    h_int=0;
                }
                Log.d("MY", "handleMessage:imageIndex " + imageIndex);
            }

            invalidate();
            handler.sendEmptyMessageDelayed(0, 10);

        }
    };


    //캔버스에 이미지를 추가하는 메서드
    private void progressState() {

        //※canvas에 그리는 순서가 중요 (배경 → 토끼 → 전투기)

        //배경1 (0,0)에 그리기
        canvas.drawBitmap(back1, 0, back1_y, null);

        //배경2 (0, -height)에 그리기
        canvas.drawBitmap(back2, 0, back2_y, null);

        //게이지그리기
        canvas.drawBitmap(gauge.imgGauge, gaugeX, gaugeY, null);

        //전투기 Life 이미지
        for (int i=0;i<uLife.length;i++) {
            canvas.drawBitmap(uLife[i], uLifeX[i], uLifeY[i], null);
        }

        //토끼
        canvas.drawBitmap(rabbit[imageIndex], rabbitX, rabbitY, null);

        //전투기 그리기
        canvas.drawBitmap(unit, unitX, unitY, null);

        //미사일그리기

        //미사일 정보는 ArrayList에 담겨져 있으므로 for 문을 반복하며 한개씩 표현

        for (int i=0;i<missList.size();i++) {
            Missile ms = missList.get(i);
            canvas.drawBitmap(missile, ms.x - missileW / 2, ms.y - missileH, null);

        }

        //배경움직이기
        scrollBack();


        //토끼움직이기
        moveRabbit();

        //미사일발사
        checkMissile();

        //충돌체크
        crash();

    }

    //미사일과 토끼 충돌 체크
    private void crash() {

        //비트맵이기 때문에 Shape의 contains 메서드는 사용할수 없음

        //미사일 좌표 = 토끼 영역 비교 -> 충돌여부 판단
        //발사된 모든 미사일과의 충돌을 비교해야함

        for (int i=0;i<missList.size();i++) {
            Missile ms = missList.get(i);
                            //미사일이 토끼 폭사이
            if (ms.x > rabbitX && ms.x < (rabbitX+rabbitW)-missileW  && ms.y > rabbitY && ms.y < rabbitY + rabbitH) {

                //토끼와 미사일 충돌 사운드
                soundManager.play(1);

                gauge.progress(); // 에너지 감소
                missList.remove(ms);

                if (gauge.isTimeout()) {
                    showFinishDialog();
                }
            }
        }

        //토끼와 비행기의 충돌
        if (!unitCrash && unitX > rabbitX && unitX < rabbitX + rabbitW && unitY > rabbitY && unitY < rabbitY + rabbitH) {

            unitCrash = true;
            h_safety.sendEmptyMessage(0);

            lifeGauge--;
            uLifeX[lifeGauge] = -100;  //보이지않는 화면 밖으로 이동 (따로 지울수가 없다.)

            //충돌시 진동

            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);

            if (lifeGauge == 0) {
                showFinishDialog();
                lifeGauge = 2;
            }
        }
    }

    private void showFinishDialog() {

        if (!finish) {
            finish = true;

            //alertDialog

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("게임종료").setMessage("다시 하시겠습니까?");


            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(context, BackScrollActivity.class);
                    context.startActivity(intent);
                    // 이전 액티비티 종료
                    ((Activity)context).finish();

                }
            });

            dialog.setNegativeButton("종료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((Activity)context).finish();
                }
            });

            dialog.setCancelable(false);
            dialog.show();
        }




    }

    Handler h_safety = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            h_safety.sendEmptyMessageDelayed(0, 1000);
            safety++;
            if (safety > 2) {
                safety = 0;
                unitCrash = false;
                h_safety.removeMessages(0);
            }

        }
    };

    //미사일을 움직이는 메서드
    private void checkMissile() {


       if (missList.size() > 0) {

           for (int i=0; i<missList.size(); i++) {
               Missile ms = missList.get(i);
               ms.move(); //미사일 이동

               //화면 밖으로 이동할경우 ArrayList에서 제거
               if (ms.isDead()) {
                   missList.remove(ms);
               }
           }

       }
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

        //전투기 이동
        //canvas.drawBitmap(unit, unitX, unitY, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //화면을 터치할때마다 missile 객체를 생성한 후,
        //비행기 위치에 해당하는 x,y좌표를 설정하여
        //ArrayList에 저장
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            //미사일을 생성
            Missile m1 = new Missile(unitX, unitY);
            Missile m2 = new Missile(unitX + unitW, unitY);

            missList.add(m1);
            missList.add(m2);

            //미사일 발사 사운드 재생
            soundManager.play(2);
        }

        return false;
    }


    //센서 감지 Listener

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //비행기에 움직임 추가

        unitX -= (int) sensorEvent.values[0] * 5;   //x축가속도값
        unitY += (int) sensorEvent.values[1] * 5;

        //좌우로 화면 벗어나지 못하도록 처리
        if (unitX <= 0) {
            unitX =0;
        } else if (unitX >= width - unitW) {
            unitX = width - unitW;
        }

        //상하로 화면 벗어나지 못하도록 처리

        if (unitY < 0) {
            unitY =0;
        }

        if (unitY >= height - unitH) {
            unitY = height - unitH;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}























