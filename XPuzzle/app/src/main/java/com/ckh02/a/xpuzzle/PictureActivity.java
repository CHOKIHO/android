package com.ckh02.a.xpuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class PictureActivity extends AppCompatActivity {

    ImageView imgPictureView;
    GridView gridView;
    View view1;
    Button btnStart;
    int imageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        //----------------------------------------------------------------
        // 확대되는 이미지를 보여주기 위해 ImageView 뷰를 설정합니다.
        imgPictureView = (ImageView)findViewById(R.id.imgPictureView);
        setImage(imgPictureView);

        gridView = (GridView) findViewById(R.id.gridView2);
        btnStart = (Button) findViewById(R.id.btnStart);
        //view1 = (View) findViewById(R.id.view1);

        btnStart.setOnClickListener(click);


    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //이미지뷰 이미지를 분할하여 gridView에 보여주기
            imgPictureView.setVisibility(View.GONE);

            gridView.setVisibility(View.VISIBLE);
            //gridView.setNumColumns(4);
            ImageAdapter ia = new ImageAdapter(PictureActivity.this, imageID);
            gridView.setAdapter(ia);


        }
    };

    private void setImage(ImageView imageView) {
        //----------------------------------------------------------------
        // 초기 액티비티의 GridView 뷰의 이미지 항목을 클릭할 때 생성된 인텐트는
        // 이 액티비티는 getIntent 메소드를 호출하여 접근할 수 있습니다.
        Intent receivedIntent = getIntent();

        //----------------------------------------------------------------
        // 확대되는 이미지의 리소스 ID를 인텐트로부터 읽어들이고,
        // 그것을 ImageView 뷰의 이미지 리소스로 설정합니다.

        imageID = (Integer)receivedIntent.getExtras().get("image ID");
        imageView.setImageResource(imageID);
    }


}
