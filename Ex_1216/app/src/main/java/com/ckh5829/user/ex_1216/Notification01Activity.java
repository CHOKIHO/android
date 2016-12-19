package com.ckh5829.user.ex_1216;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Notification01Activity extends AppCompatActivity {

    Button basicBtn, basicCancelBtn, actionBtn, actionCancelBtn;

    //알림전송 매니저
    NotificationManagerCompat notificationManagerCompat;
    //알림객체
    Notification notification;
    //알림구분 id
    static final int BASIC_ID = 0;
    static final int ACTION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification01);

        notificationManagerCompat = notificationManagerCompat.from(Notification01Activity.this);

        basicBtn = (Button) findViewById(R.id.basicBtn);
        basicCancelBtn = (Button) findViewById(R.id.basicCancelBtn);
        actionBtn = (Button) findViewById(R.id.actionBtn);
        actionCancelBtn = (Button) findViewById(R.id.actionCancelBtn);

        basicBtn.setOnClickListener(basicClick);
        basicCancelBtn.setOnClickListener(basicClick);

        actionBtn.setOnClickListener(actionClick);
        actionCancelBtn.setOnClickListener(actionClick);

    }

    View.OnClickListener actionClick = new View.OnClickListener() {
        @Override
        //액션알림 생성 및 삭제
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.actionBtn:

                    Intent intent = new Intent(Notification01Activity.this, Notification01Activity.class);

                    PendingIntent pendingIntent = PendingIntent.getActivity(Notification01Activity.this,
                            0, intent, PendingIntent.FLAG_UPDATE_CURRENT);



                    //알림생성 및 액션추가
                    notification = new NotificationCompat.Builder(Notification01Activity.this)
                            .setContentTitle("액션알림입니다.")
                            .setContentText("잠시 기다려 주세요")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .addAction(R.mipmap.ic_launcher,"액션명1", pendingIntent)   //추가버튼
                            //.addAction(R.mipmap.ic_launcher,"액션명2", pendingIntent)   //추가버튼
                            .build();
                    notificationManagerCompat.notify(ACTION_ID, notification);


                    break;
                case R.id.actionCancelBtn:
                    break;
            }
        }
    };

    View.OnClickListener basicClick = new View.OnClickListener() {
        @Override
        //기본알림 생성 및 삭제
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.basicBtn:

                    //intent를 포함한 알림객체 생성

                    //화면전환을 위한 Intent 생성
                    Intent intent = new Intent(Notification01Activity.this, Notification01Activity.class);

                    //액션이 실행될때(클릭할때)까지 대기하는 PendingIntent 생성
                    //※PendingIntent.FLAG_UPDATE_CURRENT
                    PendingIntent pendingIntent = PendingIntent.getActivity(Notification01Activity.this,
                                                                     0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    notification = new NotificationCompat.Builder(Notification01Activity.this)
                            .setContentTitle("기본알림입니다.")
                            .setContentText("점심시간이 곧 다가옵니다.")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentIntent(pendingIntent)    //바로가 아닌 나중에 클릭되면 실행
                            .setAutoCancel(true)   //클릭해서 앱실행되면 알림표시 삭제
                            .build();
                    //매니저를 통해 알림 전송
                    notificationManagerCompat.notify(BASIC_ID, notification);

                    break;
                case R.id.basicCancelBtn:
                    //알림취소
                    notificationManagerCompat.cancel(BASIC_ID);

                    break;
            }

        }
    };
}
