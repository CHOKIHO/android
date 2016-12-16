package com.ckh5829.user.ex_1216;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

public class SwipeRefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        refreshLayout.setOnRefreshListener(this);

        //로딩 디스크 백그라운드 변경
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.argb(255, 127, 179, 255));

        //크기변경
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);

        //화살표 색상변경 (colors.xml에 미리 정의 해놓아야함)
        refreshLayout.setColorSchemeResources(R.color.color1, R.color.color2, R.color.color3, R.color.color4);

        //디스크 확대/축소
        //refreshLayout.setProgressViewOffset(true,0, 200);
        refreshLayout.setProgressViewEndTarget(true, 100);

    }

    //로딩시작시 호출되는 메서드
    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //로딩 디스크 제거
            refreshLayout.setRefreshing(false);
        }
    };
}
