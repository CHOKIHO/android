package com.ckh5829.user.ex_1216;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;

public class Notification02Activity extends AppCompatActivity {

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    static final int BIG_PICTURE = 0;
    static final int BIG_TEXT = 1;
    static final int INBOX = 2;


    Button bigPicture, bigText, inbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification02);

        //알림매니저 객체 생성
        notificationManagerCompat = NotificationManagerCompat.from(Notification02Activity.this);

        bigPicture = (Button) findViewById(R.id.bigPicture);
        bigText = (Button) findViewById(R.id.bigText);
        inbox = (Button) findViewById(R.id.inbox);

        //bigPicture 스타일 알림
        bigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //준비된 사진을 가져온다 (Bimpap 형태만 가능)
                Bitmap bigpic = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

                //알림 스타일 설정
                NotificationCompat.BigPictureStyle p_style = new NotificationCompat.BigPictureStyle();
                p_style.bigPicture(bigpic);
                p_style.setBigContentTitle("BigTitle");   //사진이 펼쳐졌을때의 제목
                p_style.setSummaryText("summaryText");    //사진이 펼져졌을때의 내용

                notification = new NotificationCompat.Builder(Notification02Activity.this)
                        .setContentTitle("타이틀")
                        .setContentText("텍스트")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(p_style)
                        .setVibrate(new long[]{0, 100, 200, 300})  // delay, 진동, delay, 진동
                        .build();

                notificationManagerCompat.notify(BIG_PICTURE, notification);

            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationCompat.InboxStyle i_style = new NotificationCompat.InboxStyle();
                i_style.setBigContentTitle("Inbox Title");
                i_style.setSummaryText("Summary Text");
                i_style.addLine("line-------1------");
                i_style.addLine("line-------2------");
                i_style.addLine("line-------3------");

                notification = new NotificationCompat.Builder(Notification02Activity.this)
                        .setContentTitle("title")
                        .setContentText("text")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(i_style)
                        .build();
                notificationManagerCompat.notify(INBOX, notification);


            }
        });




        //bigText 스타일 알림 (글씨체, 사이즈, 색상 등등)
        bigText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //알림창 텍스트의 스타일 정의 클래스
                SpannableStringBuilder title = new SpannableStringBuilder();
                title.append("Style Title");

                //범위를 지정하여 스타일 적용
                title.setSpan(new RelativeSizeSpan(1.5f), 0, 5, 0);  //글씨크기
                title.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 10, 0); //스타일
                title.setSpan(new ForegroundColorSpan(Color.RED), 0, 3, 0);  //글씨색
                title.setSpan(new ForegroundColorSpan(Color.GREEN), 3, 5, 0);  //글씨색

                NotificationCompat.BigTextStyle t_style = new NotificationCompat.BigTextStyle();
                t_style.setBigContentTitle(title);
                t_style.setSummaryText("summary");
                notification = new NotificationCompat.Builder(Notification02Activity.this)
                        .setContentTitle("title")
                        .setContentText("text")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(t_style)
                        .build();

                notificationManagerCompat.notify(BIG_TEXT, notification);
            }
        });
    }

}

















