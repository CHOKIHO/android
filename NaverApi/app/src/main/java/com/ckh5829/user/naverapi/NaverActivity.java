package com.ckh5829.user.naverapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NaverActivity extends AppCompatActivity {

    static EditText search;
    private Button search_btn;
    private ListView myListView;
    Parser parser;

    ArrayList<BookModel> list;

    ViewModelAdapter adapter;

    //로딩용 다이얼로그
    ProgressDialog dialog;


    int start = 1; //검색을 시작할 인덱스 번호

    //스크롤링을 통한 추가 로드를 위해 필요한 변수
    LayoutInflater mInflater;
    View footerView;
    boolean mLockListView = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver);

        search = (EditText) findViewById(R.id.search);
        myListView = (ListView) findViewById(R.id.myListView);
        search_btn = (Button) findViewById(R.id.search_btn);

        dialog = new ProgressDialog(NaverActivity.this);

        dialog.setMessage("검색중...");

        parser = new Parser();
        list = new ArrayList<>();

        //리스트뷰의 오버스크롤 이펙트 제거
        myListView.setOverScrollMode(View.OVER_SCROLL_NEVER);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            /*
                //가져오는동안 아무것도 할수없다. 백그라운드 통신서비스로 변경
                parser.connectNaver(list);
            */

                //한글자 이상 입력받은 경우에만 검색
                if (search.getText().toString().trim().length() > 0) {
                    //list.clear();

                    list = new ArrayList<>();
                    adapter = null;
                    start = 1;

                    dialog.show();
                    new NaverAsync().execute("통신중입니다."); // donInBackground메서드 호출 (파라메터로 "통신중입니다.")

	                //DaumAsync daumAsync = new DaumAsync(NaverActivity.this, R.layout.book_item, list, myListView);
	                //daumAsync.execute();
                }
            }
        });

        //로딩을 표시하기 위해 미리 만들어둔 footer를 등록하기 위한 준비
        mInflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footerView = mInflater.inflate(R.layout.footer, null);

    }

    // Sync추상클래스(AsyncTask) 상속
    //서버와 통신작업을 백그라운드에서 실행할 수 있도록 해주는 클래스가 AsyncTask (진저브래드이상버전)
    //AsyncTask의 제네릭3
    /*
       1. doInBackground의 파라메터로 전달될 타입
       2. onProgressUpdate()가 재정의 되었을때 메서드에서 사용할 자료형 타입
       3. Sync클래스의 작업결과를 반영하는 onPostExecute()메서드로 전달될 결과 타입
          doInBackground의 리턴값

          NaverAsync().execute() → doInBackground() → onPostExecute() : 최종결과값은 onPostExecute()의 리턴타입
     */
    class NaverAsync extends AsyncTask<String, String, ArrayList<BookModel>> {

        //각종 반복이나 제어등 주된처리 로직을 담당
        @Override
        /*
            Stringg... strings : varArgs타입, 정해진갯수가 없다.
         */
        protected ArrayList<BookModel> doInBackground(String... strings) {
            return parser.connectNaver(start, list);  // 비동기로 실행할 실제 작업 parser.connectNaver(list);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        //doInBackground에서 작업을 마친 결과물이 onPostExecute파라메터로 전달
        @Override
        protected void onPostExecute(ArrayList<BookModel> bookModels) {
            //super.onPostExecute(bookModels);

            //통신정상파악 (로그)
/*            for (int i = 0; i < bookModels.size(); i++) {
                String str = bookModels.get(i).getB_title();
                Log.d("MY", str + "\n");
            }
*/

/*
            //리스트뷰 클릭 이벤트처리용 myListView추가
            adapter = new ViewModelAdapter(NaverActivity.this, R.layout.book_item, bookModels, myListView);
            myListView.setAdapter(adapter);

            //검색종료
            dialog.dismiss();
*/

            //doInBackground에서 통신을 마친 결과가
            //onPostExecute의 result로 넘어온다. 이것을 이용해서 리스트 뷰에 화면을 갱신한다.
            if (adapter == null) {

                adapter = new ViewModelAdapter(NaverActivity.this, R.layout.book_item, bookModels, myListView);
                // 리스트 뷰에 스크롤 리스너를 등록합니다.
                myListView.setOnScrollListener(scrollListener);

                //리스트뷰에 footer등록!! setAdapter 이전에 해야 합니다.
                myListView.addFooterView(footerView);

                //리스트뷰에 어댑터 세팅
                myListView.setAdapter(adapter);

            }

            //리스트뷰의 변경사항이 있다면 갱신
            adapter.notifyDataSetChanged();
            mLockListView = false;

            //검색종료
            dialog.dismiss();

        }


    }

    //리스트 뷰의 스크롤 감지자
    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {
                    //onScrollStateChanged 는 현재 리스트뷰의 상태를 알려줍니다.
                    // scrollState 으로 넘어오는 상태값은 다음과 같은 3 가지입니다.

                    //SCROLL_STATE_FLING ( 2 ) :
                    //터치 후 손을 뗀 상태에서 아직 스크롤 되고 있는 상태입니다.
                    //SCROLL_STATE_IDLE ( 0 ) :
                    //스크롤이 종료되어 어떠한 애니메이션도 발생하지 않는 상태입니다.
                    //SCROLL_STATE_TOUCH_SCROLL ( 1 ) :
                    //스크린에 터치를 한 상태에서 스크롤하는 상태입니다.
                }

                @Override
                public void onScroll(AbsListView absListView,
                                     int firstVisibleItem,
                                     int visibleItemCount,
                                     int totalItemCount) {
                //onScroll() 메서드는 스크롤이 발생하는 동안 지속적으로 호출되는
                //메서드로 현재 보여지는 리스트뷰에서 상단에 보여지는 항목의
                //index 와 현재 리스트뷰에서 보여지는 항목의 수, 그리고 리스트뷰의
                //총 항목의 수를 알려줍니다.
                // 현재 가장 처음에 보이는 항목index와 보여지는 셀번호를 더한값이


                // 전체의 숫자와 동일해지면 가장 아래로 스크롤 되었다고 가정합니다.
                    int count = totalItemCount - visibleItemCount;

                    if(firstVisibleItem >= count && totalItemCount != 0 && mLockListView == false){
                        mLockListView = true;
                        //1. 총 1000개까지 로드(네이버에서 제공하는 최대 start값이 1000임)
                        //할것이므로 +10을 했을 때를 생각하여, 990보다
                        //작을 때 까지만 start를 10씩 증가시킨다.
                        //2. 한번에 10개씩 검사하므로, list.size()가 10보다 작을경우에는
                        //스크롤을 만들 필요가 없다고 판단해도 좋다.
                        //if( ( start < 총 개수 – 한번에 검색하는 수 ) && ( list.size() >= 한번에 검색하는 수 ) ){ }


                        if(start < 1000 - 100 && list.size() >= 100) {
                            if(start >= list.size()){
                            /*list.size() -> 검색결과가 15개라고 가정해보면. 검색이 완료 되었음에도 불구하고 15는
                            990보다 작기 때문에 start += 10을 반복하며 if문을 계속 수행하게 된다.
                            이것을 방지하기 위해 start를 강제로 990으로 만들어서 더 이상 if문으로 접근해 통신을 수
                            행하지 않도록 한다*/
                                start = 1000 - 100; //총 갯수 – 한번에 검색하는 수
                            }
                            start += 100;//열개씩 추가 로드할 예정
                            //통신!
                            //만약 footerView.setOnClickListener를 사용할 예정이라면 주석처리 하면 된다.
                            new NaverAsync().execute();
                        }else{
                            Toast.makeText(getApplicationContext(),"더 불러올 내용이 없습니다" ,Toast.LENGTH_SHORT).show();

                            //footer 제거
                            myListView.removeFooterView(footerView);
                        }
                    }
                }//onScroll()
            };

}














