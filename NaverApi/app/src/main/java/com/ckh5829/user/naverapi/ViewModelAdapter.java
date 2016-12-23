package com.ckh5829.user.naverapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by USER on 2016-12-09.
 * <p>
 * 리스트뷰 Footer처리
 */


class myAdapter extends ArrayAdapter<String> {

	public myAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return super.getView(position, convertView, parent);
	}
}


public class ViewModelAdapter extends ArrayAdapter<BookModel> {

    Context context;
    ArrayList<BookModel> list;
    BookModel vo;
    int resource;


    public ViewModelAdapter(Context context, int resource, ArrayList<BookModel> list, ListView myListView) {

        //부모 list 포함시킨다. LisstView는 추가할수 없다.
        super(context, resource, list);

        this.context = context;
        this.list = list;
        this.resource = resource;

        myListView.setOnItemClickListener(listClick);

    }

    //리스트뷰 클릭 이벤트 리스너
    AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            String bookImg = list.get(position).getB_img();

            //이미지 이름만 추출
            String bookId = bookImg.substring(bookImg.lastIndexOf('/') + 1,
                    bookImg.lastIndexOf(".jpg"));

            //추출이름으로 상세정보 페이지 이동
            String bookLink = "http://book.naver.com/bookdb/book_detail.nhn?bid=" + bookId;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(bookLink));
            context.startActivity(intent);

        }
    };


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layout = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layout.inflate(resource, null);

        //BookModel 타입
        vo = list.get(position);

        TextView title = (TextView) convertView.findViewById(R.id.book_title);
        TextView author = (TextView) convertView.findViewById(R.id.book_author);
        TextView price = (TextView) convertView.findViewById(R.id.book_price);

        ImageView img = (ImageView) convertView.findViewById(R.id.book_img);

        title.setText("번호 : " + (position+1) + "\n제목 : " + vo.getB_title());
        author.setText("저자 : " + vo.getB_author());
        price.setText("가격 : " + vo.getB_price());

        //이미지로드
        new ImgAsync(img, vo).execute();

        return convertView;
    }


    //Async 클래스 만들기 (이미지 로딩용)
    class ImgAsync extends AsyncTask<String, String, Bitmap> {

        Bitmap bm;
        ImageView mImg;
        BookModel vo;

        public ImgAsync(ImageView mImg, BookModel vo) {
            this.mImg = mImg;
            this.vo = vo;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {

                URL img_url = new URL (vo.getB_img());

                //BufferedInputStream으로 이미지 가져오기
                BufferedInputStream bis = new BufferedInputStream(img_url.openStream());

                //BufferedInputStream → BitmapFactory → Bitmap
                bm = BitmapFactory.decodeStream(bis);

                bis.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImg.setImageBitmap(bitmap);
        }
    }

}





















