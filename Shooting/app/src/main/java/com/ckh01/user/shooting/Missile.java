package com.ckh01.user.shooting;

/**
 * Created by USER on 2016-12-15.
 */

public class Missile {

    //미사일 생성 (위치값, 너비, 높이) 클래스

    //미사일 x,y좌료
    int x, y;

    public Missile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= 10;
    }

    public boolean isDead() {

        //미사일 화면 밖 처리
        if (y < 0) {
            return true;
        } else {
            return false;
        }
    }
}
