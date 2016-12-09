package com.ckh01.user.ex_1208;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class RSPActivity extends AppCompatActivity {

    Button btnStart, btnExit, btnR, btnS, btnP;

    ImageView comR, comS, comP, usrR, usrS, usrP;

    ImageView[] com = new ImageView[3];
    ImageView[] usr = new ImageView[3];

    int num=0;
    int rot=0;
    int count=0;
    int comRand=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsp);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnExit = (Button) findViewById(R.id.btnExit);

        btnR = (Button) findViewById(R.id.btnR);
        btnS = (Button) findViewById(R.id.btnS);
        btnP = (Button) findViewById(R.id.btnP);


        comR = (ImageView) findViewById(R.id.comR);
        comS = (ImageView) findViewById(R.id.comS);
        comP = (ImageView) findViewById(R.id.comP);

        com[0] = (ImageView) findViewById(R.id.comR);
        com[1] = (ImageView) findViewById(R.id.comS);
        com[2] = (ImageView) findViewById(R.id.comP);


        usrR = (ImageView) findViewById(R.id.usrR);
        usrS = (ImageView) findViewById(R.id.usrS);
        usrP = (ImageView) findViewById(R.id.usrP);

        usr[0] = (ImageView) findViewById(R.id.usrR);
        usr[1] = (ImageView) findViewById(R.id.usrS);
        usr[2] = (ImageView) findViewById(R.id.usrP);

        btnStart.setOnClickListener(click);
        btnExit.setOnClickListener(click);
        btnR.setOnClickListener(select);
        btnS.setOnClickListener(select);
        btnP.setOnClickListener(select);
    }

    View.OnClickListener select = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            handle.removeMessages(0);
            int uResult=0;
            switch (view.getId()) {
                case R.id.btnR:
                    uResult=0;
                    break;
                case R.id.btnS:
                    uResult=1;
                    break;
                case R.id.btnP:
                    uResult=2;
                    break;
            }
            setVisible(comRand, uResult);
        }
    };

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnStart:
                    comRand = new Random().nextInt(3);
                    //가위바위보 애니메이션
                    handle.sendEmptyMessage(0);
                    break;
                case R.id.btnExit:
                    finish();
                    break;
/*                case R.id.btnR:
                    handle.removeMessages(0);
                    int com1 = new Random().nextInt(3);
                    showComputer(com1);
                    showUser(0);
                    break;
                case R.id.btnS:
                    handle.removeMessages(0);
                    int com2 = new Random().nextInt(3);
                    showComputer(com2);
                    showUser(1);
                    break;
                case R.id.btnP:
                    handle.removeMessages(0);
                    int com3 = new Random().nextInt(3);
                    showComputer(com3);
                    showUser(2);
                    break;*/
            }
        }
    };


    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            //num++;
            //showComputer((num++%3));
            //showUser((num++%3));

            moving();
            handle.sendEmptyMessageDelayed(0,0);

        }
    };

    private void moving() {
        count++;
        rot = count%3;
        if (count==3) count=0;

        setVisible(rot, rot);
    }

    private void setVisible(int c, int u) {
        com[c].setVisibility(View.VISIBLE);
        usr[u].setVisibility(View.VISIBLE);

        for (int i=0;i<com.length;i++) {
            if (i != c) {
                com[i].setVisibility(View.INVISIBLE);
            }
            if (i != u) {
                usr[i].setVisibility(View.INVISIBLE);
            }
        }
    }


    private void showComputer(int i) {

        if (i==0) {
            comR.setVisibility(View.VISIBLE);
            comS.setVisibility(View.INVISIBLE);
            comP.setVisibility(View.INVISIBLE);
        } else if (i==1) {
            comR.setVisibility(View.INVISIBLE);
            comS.setVisibility(View.VISIBLE);
            comP.setVisibility(View.INVISIBLE);
        } else {
            comR.setVisibility(View.INVISIBLE);
            comS.setVisibility(View.INVISIBLE);
            comP.setVisibility(View.VISIBLE);
        }
    }

    private void showUser(int i) {
        if (i==0) {
            usrR.setVisibility(View.VISIBLE);
            usrS.setVisibility(View.INVISIBLE);
            usrP.setVisibility(View.INVISIBLE);
        } else if (i==1) {
            usrR.setVisibility(View.INVISIBLE);
            usrS.setVisibility(View.VISIBLE);
            usrP.setVisibility(View.INVISIBLE);
        } else {
            usrR.setVisibility(View.INVISIBLE);
            usrS.setVisibility(View.INVISIBLE);
            usrP.setVisibility(View.VISIBLE);
        }
    }
}
