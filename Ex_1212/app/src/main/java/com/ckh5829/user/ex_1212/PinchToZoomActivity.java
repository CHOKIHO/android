package com.ckh5829.user.ex_1212;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PinchToZoomActivity extends AppCompatActivity {

    ImageView iv_photo;

    //추가한 photoview라이브러리
    //Zoom 관련 기능을 사용하기 위한 클래스
    PhotoViewAttacher attacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinch_to_zoom);

        iv_photo = (ImageView) findViewById(R.id.iv_photo);

        //attacher에 iv_photo 추가 후 update 호출
        attacher = new PhotoViewAttacher(iv_photo);
        attacher.update();


    }
}
