package com.ckh5829.user.ex_sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AcceleroMeterActivity extends AppCompatActivity {

    TextView t1, t2, t3;
    //x축, y축, z축 가속도 값
    int x, y, z;

    SensorManager manager;
    SensorEventListener sensorL;
    Sensor accSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelero_meter);

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorL = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                x = (int) sensorEvent.values[0];
                y = (int) sensorEvent.values[1];
                z = (int) sensorEvent.values[2];

                t1.setText("x축 가속도 : " + x);
                t2.setText("y축 가속도 : " + y);
                t3.setText("z축 가속도 : " + z);  //중력가속도
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        //다른화면에서 현재화면으로 넘어올대 센서등록
        //파라메터 :  Listener, 센서종류, 센서반응속도
        manager.registerListener(sensorL, accSensor, SensorManager.SENSOR_DELAY_FASTEST);

        //반응속도 빠른 순서
        // SENSOR_DELAY_FASTEST → SENSOR_DELAY_GAME → SENSOR_DELAY_UI → SENSOR_DELAY_NORMAL
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (manager != null) {
            manager.unregisterListener(sensorL, accSensor);
        }
    }

}
