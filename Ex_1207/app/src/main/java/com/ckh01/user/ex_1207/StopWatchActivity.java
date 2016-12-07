package com.ckh01.user.ex_1207;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    TextView time_out, record;
    ScrollView scroll;
    Button btn_start, btn_rec;

    //스톱워치 현재상태
    final static int INIT =0;  //초기상태
    final static int RUN =1;   //진행
    final static int PAUSE =2; //정지

    int cur_status = INIT;
    int myCount = 1;

    //타이머 진행상황을 저장할 변수
    long myBaseTime, myPauseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        time_out = (TextView) findViewById(R.id.time_out);
        record = (TextView) findViewById(R.id.record);
        scroll = (ScrollView) findViewById(R.id.scroll);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_rec = (Button) findViewById(R.id.btn_rec);

        btn_start.setOnClickListener(myClick);
        btn_rec.setOnClickListener(myClick);


    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_start:

                    switch (cur_status) {
                        case INIT:
                            //앱이 켜지고 나서 실제로 경과된 시간 (쓰레드보다 편하다)
                            myBaseTime = SystemClock.elapsedRealtime();

                            //시간 갱신 핸들러 호출
                            myTimer.sendEmptyMessage(0);

                            //버튼활성화
                            btn_start.setText("정지");
                            btn_rec.setEnabled(true);

                            //상태변경
                            cur_status = RUN;
                            break;

                        case RUN:
                            myTimer.removeMessages(0);
                            myPauseTime = SystemClock.elapsedRealtime();

                            btn_start.setText("시작");
                            btn_rec.setText("리셋");
                            cur_status = PAUSE;

                            break;

                        case PAUSE:
                            long now = SystemClock.elapsedRealtime();
                            myTimer.sendEmptyMessage(0);
                            myBaseTime += now - myPauseTime;

                            btn_start.setText("정지");
                            btn_rec.setText("기록");

                            cur_status = RUN;
                            break;
                    }

                    break;
                case R.id.btn_rec:
                    switch (cur_status) {
                        case RUN:
                            String str = record.getText().toString();
                            str += String.format("%d. %s\n", myCount, getTime());
                            record.setText(str);
                            myCount++;

                            //강제 스크롤 효과
                            scroll.scrollTo(0, record.getHeight());
                            break;

                        case PAUSE:
                            btn_rec.setText("기록");
                            btn_rec.setEnabled(false);
                            cur_status=INIT;
                            myCount=1;
                            record.setText("");
                            time_out.setText("00:00:00");

                            break;
                    }
                    break;
            }
        }
    };

    //시간갱신 핸들러 → handlemessage
    Handler myTimer = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            time_out.setText(getTime());

            //최고속도로 반복 (while 루프)
            myTimer.sendEmptyMessage(0);
        }
    };

    private String getTime() {
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myBaseTime;
        String resultTime = String.format("%02d:%02d:%02d",
                                            outTime / 1000 / 60,
                                            (outTime / 1000) % 60,
                                            (outTime % 1000) / 10);
        return ""+resultTime;
    }


}
