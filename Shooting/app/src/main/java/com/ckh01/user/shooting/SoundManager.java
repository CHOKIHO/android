package com.ckh01.user.shooting;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by USER on 2016-12-15.
 */

public class SoundManager {

    private static SoundManager soundManager;

    //저용량 사운드 처리를 위한 객체
    private SoundPool soundPool;
    //사운드 저장을 위한 객체
    private HashMap<Integer, Integer> map;

    private AudioManager audioManager;
    private Context context;

    //현재 객체가 한개만 생성될수 있도록 하는 메서드
    private SoundManager() {

    }

    public static SoundManager getInstance() {
        if (soundManager == null) {
            soundManager = new SoundManager();
        }
        return soundManager;
    }

    //초기화
    public void init(Context context) {

        this.context = context;

        //파라메터  2:동시출력가능 스트림수, 재생 사운드 타입, 품질(기본값은 0)
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        map = new HashMap<>();
    }

    //사운드 추가 메서드
    public void addSound(int idx, int id) {

        //화면제어권자, Raw에 등록한 사운드, 우선순위
        int res_id = soundPool.load(context, id, 0);
        map.put(idx, res_id);
    }

    //사운드 재생
    public void play(int idx) {
        //파라메터
        //1 : 재생할 사운드
        //2 : 왼쪽볼륨
        //3 : 오른쪽 볼륨
        //4 : 우선순위
        //5 : 반복 (0:한번, -1:무한재생, 1,2,3,4 : 추가재생횟수)
        //6 : 재생속도 (0.5: 절반속도, 1:원본, 2:2배속, )
        soundPool.play(map.get(idx), 1, 1, 1 ,0, 1);
    }

}
