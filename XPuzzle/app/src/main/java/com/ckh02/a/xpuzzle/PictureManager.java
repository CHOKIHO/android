package com.ckh02.a.xpuzzle;

/**
 * Created by A on 2016-12-24.
 */

public class PictureManager {
    // imageIDs 배열은 GridView 뷰를 구성하는 이미지 파일들의 리소스 ID들을 담습니다.

    private int[] imageIDs = new int[] {
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3,
            R.drawable.photo4,
            R.drawable.photo5
    };


    public int[] getImageIDs() {
        return imageIDs;
    }
}
