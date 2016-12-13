package com.ckh5829.user.ex_sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OrientationActivity extends AppCompatActivity {

    int heading, pitch, roll;
    TextView t1, t2, t3;


    //센서관련 객체

    SensorManager manager;   //센서사용권한, 센서 등록 및 해제
    SensorEventListener sensorL; //센서 변경 감지 Listener
    Sensor orientSensor;  //센서(종류) 객체



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);

        //센서매니저 생성

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //type_orientation =  magnetic + accelolater
        orientSensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        sensorL = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                //센서변경시 호출메서드
                // TYPE_ORIENTATION → heading, pitch, roll

                heading = (int)sensorEvent.values[0]; //방위값
                pitch = (int) sensorEvent.values[1];  //경사도(수직기울기)
                roll = (int) sensorEvent.values[2];   //회전값(수평기울기

                t1.setText("방위(heading) : " + heading);
                t2.setText("경사도(pitch) : " + pitch);
                t3.setText("기울기(roll) : " + roll);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                //센서 반응속도를 감지

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        //다른화면에서 현재화면으로 넘어올대 센서등록
        //파라메터 :  Listener, 센서종류, 센서반응속도
        manager.registerListener(sensorL, orientSensor, SensorManager.SENSOR_DELAY_FASTEST);

        //반응속도 빠른 순서
        // SENSOR_DELAY_FASTEST → SENSOR_DELAY_GAME → SENSOR_DELAY_UI → SENSOR_DELAY_NORMAL
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (manager != null) {
            manager.unregisterListener(sensorL, orientSensor);
        }
    }
}











