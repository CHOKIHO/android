package com.ckh5829.user.ex_1213;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by USER on 2016-12-13.
 */

public class MyPagerAdapter extends FragmentPagerAdapter{
    //준비해둔 fragment를 연결해주는 클래스

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // 뷰페이저의 하면 구성을 위한 메서드
    // 연결할 page 등록

    //※ ListView의 getView와 같은역할 (position별 호출)
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case PageInfo.FRAGMENT_PAGE1:
                //page1이 호출될때 fragment에 우측으로 나란히 붙힌다.
                return new Page1Activity();
            case PageInfo.FRAGMENT_PAGE2:
                return new Page2Activity();
            case PageInfo.FRAGMENT_PAGE3:
                return new Page3Activity();
        }

        return null;
    }

    // 뷰페이저가 저장하는 페이지 수
    // page 총 갯수
    @Override
    public int getCount() {
        return PageInfo.PAGES;
    }

}
