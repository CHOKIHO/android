package com.ckh5829.user.ex_1212;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraResultActivity extends AppCompatActivity {

    //request code 값 (리턴값을 받을 호출 액티비티 구분)
    private final int TAKE_CAMERA =1;

    ImageView img_view;
    Button camera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_result);

        camera = (Button) findViewById(R.id.camera);
        img_view = (ImageView) findViewById(R.id.img_view);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //내부 카메라로 연결 (화면전환)
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                //결과값을 가지고 리턴
                //startActivityForResult → onActivityResult 와 쌍을 이룬다.
                startActivityForResult(intent, TAKE_CAMERA);

            }
        });

        //화면 회전 Listener
        Resources r = Resources.getSystem();
        Configuration config = r.getConfiguration();
        onConfigurationChanged(config);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        //현재디바이스의 방향 (portrait, landscape) 체크

        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                Toast.makeText(getApplicationContext(), "LandScape", 0).show();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Toast.makeText(getApplicationContext(), "Portrait", 0).show();
                break;

        }
    }

    @Override
    //※상태구분, 결과 , 데이터 인텐트       //보낼때코드,   카메라실행결과코드,    사진
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_CAMERA:
                    if (data !=null) {
                        //bundle로 넘어온 사진data를 Bitmap으로 캐스팅
                        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                        /* 화면전환 (portrait ↔ landscape) 액티비티의 onCreate가 호출되어
                        *  데이타가 사라지게 된다.
                        *
                        *  방지법은
                        *    1. AndroidManifest의 Activity 속성정의
                        *       configChanges, windowSoftInputMode 설정
                        *    2. 회전 감지자를 사용
                        *
                        * */
                        img_view.setImageBitmap(thumbnail);

                    }

                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
