package com.ckh5829.user.mypuzzle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by USER on 2016-12-22.
 */

public class GameManager {

	private LinkedList<Map> picInfoList;
	private Integer[] mThumbIds = { R.drawable.photo1, R.drawable.photo2 };


	public GameManager() {

		picInfoList = new LinkedList<>();

		createPicture();

	}

	private void createPicture() {

		//사진정보를 맵구조로 저장
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", String.valueOf(R.drawable.photo2));
		map.put("2", String.valueOf(R.drawable.photo1));
		picInfoList.add(map);





	}


}
