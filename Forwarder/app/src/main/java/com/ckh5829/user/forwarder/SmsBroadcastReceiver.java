package com.ckh5829.user.forwarder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by USER on 2016-12-15.
 */

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private Context mContext;
    private NotificationManager mNotificationManager;


    @Override
    public void onReceive(Context mContext, Intent intent) {

        this.mContext = mContext;
        String action = intent.getAction();

        //Log.e("the time is right","yay!");
        //Intent in = new Intent(mContext.getApplicationContext() , MainActivity.class);
        //mContext.startService(in);

        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Log.d("onReceive()","문자가 수신되었습니다");

            // SMS 메시지를 파싱합니다.
            Bundle bundle = intent.getExtras();
            Object messages[] = (Object[])bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];

            for(int i = 0; i < messages.length; i++) {
                // PDU 포맷으로 되어 있는 메시지를 복원합니다.
                smsMessage[i] = SmsMessage.createFromPdu((byte[])messages[i]);
            }

            // SMS 수신 시간 확인
            Date curDate = new Date(smsMessage[0].getTimestampMillis());
            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);
            String originDate = mDateFormat.format(curDate);

            Log.d("문자 수신 시간", curDate.toString());

            // SMS 발신 번호 확인
            String origNumber = smsMessage[0].getOriginatingAddress();

            // SMS 메시지 확인
            String message = smsMessage[0].getMessageBody().toString();
            Log.d("문자 내용", "발신자 : "+origNumber+", 내용 : " + message);

            // abortBroadcast();
            // 우선순위가 낮은 다른 문자 앱이 수신을 받지 못하도록 함


            //특정번호만 포워딩하기

            if (origNumber.equals(new String("15881688"))) {

                Log.d("MY", "onReceive: "+ origNumber);
                return;

            }



            /****************************************************************

                Notification 띄우기 (originNum, smsDate, originText)

            *****************************************************************/


            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mContext);

            Intent notificationIntent = new Intent(mContext.getApplicationContext(), ShowActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);

            notificationIntent.putExtra("originNum", origNumber);
            notificationIntent.putExtra("smsDate", originDate);
            notificationIntent.putExtra("originText", message);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);


            PendingIntent pendingIntent = PendingIntent.getActivity(mContext.getApplicationContext(),
                    0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);



            //알림생성 및 액션추가
            Notification notification = new NotificationCompat.Builder(mContext)
                    .setContentTitle("문자포워딩")
                    .setContentText("포워딩")
                    .setSmallIcon(R.mipmap.x64)
                    .setContentIntent(pendingIntent)
                    //.addAction(R.mipmap.ic_launcher,"내용보기", pendingIntent)   //추가버튼
                    .build();

            notificationManagerCompat.notify(0, notification);

        }
    }
}
