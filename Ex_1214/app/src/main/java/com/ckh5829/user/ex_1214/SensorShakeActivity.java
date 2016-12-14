package com.ckh5829.user.ex_1214;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SensorShakeActivity extends AppCompatActivity {

    private float speed;
    private float x, y, z;

    //휴대폰이 흔들림을 판단할 순간속도를 임의로 지정
    private static final int SHAKE_HOLD_1 = 25;  //오른손
    private static final int SHAKE_HOLD_2 = -15; //왼손

    //센서준비
    private SensorManager manager;
    private Sensor accSensor;
    private SensorEventListener sensorL;

    //애니메이션 처리 관련 객체
    private AnimationDrawable ani;
    private ImageView img;

    //애니메이션 시간
    private int count;
    //애니메이션 진행여부
    private boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_shake);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorL = new SensorEventListener() {
            @Override
            //센서변경 Listener
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (!isStart) {
                    x= sensorEvent.values[0];
                    y= sensorEvent.values[1];
                    z= sensorEvent.values[2];

                    speed = x+y+z;

                    if (speed > SHAKE_HOLD_1 || speed < SHAKE_HOLD_2) {
                        ani.start();
                        handler.sendEmptyMessage(0);

                        isStart = true; //중복실행방지

                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        //애니메이션처리준비
        img = (ImageView) findViewById(R.id.img);
        //만들어둔 drawable의 animation.xml과 연결하기
        img.setBackgroundResource(R.drawable.animation);
        ani = (AnimationDrawable) img.getBackground();
        //애니메이션 시작
        //ani.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //현재액티비티로 화면전환시 센서등록
        manager.registerListener(sensorL, accSensor, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //다른 액티비티로 화면전환시 센서해제
        if (manager != null) {
            manager.unregisterListener(sensorL);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            sendEmptyMessageDelayed(0, 1000);
            count++;

            if (count == 3) {
                ani.stop();
                count=0;
                isStart =false;
                handler.removeMessages(0);
            }
        }
    };
}
