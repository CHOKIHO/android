package com.ckh5829.user.naverapi;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by USER on 2016-12-23.
 */

public class DaumAsync extends AsyncTask<String, String, ArrayList<BookModel>> {

	private ArrayList<BookModel> list;
	private Parser paser  = new Parser();
	int start =1;
	private ViewModelAdapter adapter;
	Context context;
	int res;
	ListView listView;


	public DaumAsync(Context context, int res, ArrayList<BookModel> list, ListView listView) {

		this.context = context;
		this.list = list;
		this.res = res;
		this.listView = listView;

	}

	@Override
	protected ArrayList<BookModel> doInBackground(String... strings) {
		return paser.connectNaver(start, list);
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(ArrayList<BookModel> list) {

		if (adapter == null) {

			adapter = new ViewModelAdapter(context, R.layout.book_item, list, listView);


			// 리스트 뷰에 스크롤 리스너를 등록합니다.
			//listView.setOnScrollListener(scrollListener);

			//리스트뷰에 footer등록!! setAdapter 이전에 해야 합니다.
			//listView.addFooterView(footerView);

			//리스트뷰에 어댑터 세팅
			listView.setAdapter(adapter);

		}
		//리스트뷰의 변경사항이 있다면 갱신
		adapter.notifyDataSetChanged();

	}
}
