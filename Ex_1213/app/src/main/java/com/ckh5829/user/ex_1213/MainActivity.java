package com.ckh5829.user.ex_1213;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    ViewPager mPager;
    Button btn_page1, btn_page2, btn_page3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰페이저에 검색 및 페이지 등록

        mPager = (ViewPager) findViewById(R.id.pager);


        //상속을 FragmentActivity에서 받아서 getSupportFragmentManager() 가능
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        //최초로 보여지게 할 페이지
        mPager.setCurrentItem(PageInfo.FRAGMENT_PAGE1);

        //상단 버튼처리
        btn_page1 = (Button) findViewById(R.id.btn_page1);
        btn_page2 = (Button) findViewById(R.id.btn_page2);
        btn_page3 = (Button) findViewById(R.id.btn_page3);

        //drawable의 selector 지정으로 선택했을때의 색상표현
        btn_page1.setSelected(true);

        //뷰페이저의 변경사항을 감지하는 Listener
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //페이지 변경 완료시
            @Override
            public void onPageSelected(int position) {

                //페이지 용량은 500KB이하로....
                btn_page1.setSelected(false);
                btn_page2.setSelected(false);
                btn_page3.setSelected(false);

                switch (position) {
                    case PageInfo.FRAGMENT_PAGE1:
                        btn_page1.setSelected(true);
                        break;
                    case PageInfo.FRAGMENT_PAGE2:
                        btn_page2.setSelected(true);
                        break;
                    case PageInfo.FRAGMENT_PAGE3:
                        btn_page3.setSelected(true);
                        break;
                }

            }

            //페이지 이동중일때 호출됨
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //페이지 이동중일때 호출됨
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}
