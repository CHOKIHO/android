package com.ckh02.a.xpuzzle;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by A on 2016-12-24.
 */

public class PictureClickListener implements View.OnClickListener {

    Context context;
    int imageId;

    public PictureClickListener(Context context, int imageId) {
        this.context = context;
        this.imageId = imageId;
    }

    @Override
    public void onClick(View v) {
        //---------------------------------------------------------
        // 확대된 이미지를 보여주는 액티비티를 실행하기 위해 인텐트 객체를 정의합니다.
        // 그리고 이 액티비티에 전달할 imageID의 값을 이 객체에 저장합니다.
        // 인텐트 객체를 정의 후 이 액티비티를 실행합니다.

        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra("image ID", imageId);
        context.startActivity(intent);


    }
}
