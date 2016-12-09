package com.ckh5829.user.naverapi;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by USER on 2016-12-09.
 * XML 파싱 처리
 */

public class Parser {
    //웹에서 요소(책제목, 저자, 가격, 이미지)를 검색하여 준비된 BookModel에 저장

    BookModel vo;
    String myQuery = ""; //검색어


    public ArrayList<BookModel> connectNaver(int start, ArrayList<BookModel> list) {

        try {
            //Main 액티비티의 검색어 가져오기
            myQuery = URLEncoder.encode(NaverActivity.search.getText().toString(), "UTF8");

            int count = 100; //검색결과 100건 표시
            //네이버 책검색 API 기본정보 (검색어와 시작위치, 갯수)
            String urlStr = "https://openapi.naver.com/v1/search/book.xml?query="
                          + myQuery + "&start="+ start +"&display=" + count;

            Log.d("MY", "connectNaver: usrStr = " + urlStr);

            //서버접속 url 생성
            URL url = new URL(urlStr);

            /*
            발급받은것
            Client ID : MkJWTqFbvcr_BDnTSQhC
            Client Secret : QNiE1SrAWs

            > X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 client id 값}
            > X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 client secret 값}

             */

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");  //네이버 GET방식만 지원, POST방식
            //네이버 API Property 정보 설정
            connection.setRequestProperty("X-Naver-Client-Id","MkJWTqFbvcr_BDnTSQhC");
            connection.setRequestProperty("X-Naver-Client-Secret","QNiE1SrAWs");

            //Content-Type: text/xml;charset=utf-8
            connection.setRequestProperty("Content-Type","application/xml");

            //XML 풀파서 파싱 준비 (dom, sax파서)
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(connection.getInputStream(), null);

            //파서의 현재 상태를 가져오기
            int parserEvent = parser.getEventType();

            //XML문서끝이 아니라면 반복
            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                //시작태그이름 가져오기

                if (parserEvent == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();

                    //순서대로 Parsing
                    if (tagName.equals("title")) {
                        //시작이면 BookModel 생성
                        vo = new BookModel();
                        String title = parser.nextText();

                        //정규표현식을 이용한 <b></b> 태그 제거
                        Pattern pattern = Pattern.compile("<.*?>");
                        Matcher matcher = pattern.matcher(title);
                        if (matcher.find()) {
                            String s_title = matcher.replaceAll("");
                            vo.setB_title(s_title);
                        } else {
                            vo.setB_title(title);
                        }


                    }else if (tagName.equals("image")) {
                        String image = parser.nextText();
                        vo.setB_img(image);

                    }else if (tagName.equals("author")) {
                        String author = parser.nextText();
                        vo.setB_author(author);

                    }else if (tagName.equals("price")) {
                        String price = parser.nextText();
                        vo.setB_price(price);

                        //마지막이면 생성된 BookModel을 list에 등록
                        list.add(vo);
                    }
                }
                parserEvent = parser.next();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}












































