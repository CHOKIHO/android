package com.ckh5829.user.ex_sqlite;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBActivity extends AppCompatActivity {

    //휴대폰 저장소의 root 디렉토리 + /database/
    final String PATH = Environment.getExternalStorageDirectory() + "/database/";

    private SQLiteDatabase mDatabase;
    private Button btn_all, btn_search, btn_insert, btn_del;
    private EditText input_et;
    private TextView result_txt;

    //폴더중복생성
    private boolean isFirst = true;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        pref = PreferenceManager.getDefaultSharedPreferences(DBActivity.this);

        //SharedPreferenct
        load();

        //assets myDB.db 파일을 휴대폰(내부저장소)에 저장
        copyAsset();

        //SharedPreferenct
        save();

        //DB읽기
        mDatabase = openOrCreateDatabase( PATH + "myDb.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        input_et = (EditText) findViewById(R.id.input_et);
        result_txt = (TextView) findViewById(R.id.result_txt);

        btn_all = (Button) findViewById(R.id.btn_allFriends);
        btn_search = (Button) findViewById(R.id.btn_searchFriends);
        btn_insert = (Button) findViewById(R.id.btn_insertFriend);
        btn_del = (Button) findViewById(R.id.btn_delFriend);

        btn_all.setOnClickListener(myClick);
        btn_search.setOnClickListener(myClick);
        btn_insert.setOnClickListener(myClick);
        btn_del.setOnClickListener(myClick);


    }

    public void save() {
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean("save", isFirst);
        edit.commit();
    }

    public void load() {
        isFirst = pref.getBoolean("save", true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //데이터베이스 종료
        mDatabase.close();

    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_allFriends:
                    searchQuery("select * from friend");
                    break;
                case R.id.btn_searchFriends:
                    String et = input_et.getText().toString();
                    searchQuery("select * from friend where name = '" + et + "'");

                    break;
                case R.id.btn_insertFriend:

                    //Dialog Show

                    final Dialog dialog = new Dialog(DBActivity.this);
                    dialog.setContentView(R.layout.dialog);

                    final EditText name = (EditText) dialog.findViewById(R.id.name);
                    final EditText phone = (EditText) dialog.findViewById(R.id.phone);
                    final EditText age = (EditText) dialog.findViewById(R.id.age);
                    Button send = (Button) dialog.findViewById(R.id.send);

                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            //ContentValues : DB추가할 데이터를 모아서 한번에 처리하는 클래스
                            ContentValues values = new ContentValues();
                            values.put("name", name.getText().toString());
                            values.put("phone", phone.getText().toString());
                            values.put("age", age.getText().toString());

                            //데이터 없을경우 처리 필요

                            //values를 DB저장
                            mDatabase.insert("friend", null, values);

                            //추가데이터 확인
                            searchQuery("select * from friend");
                            dialog.dismiss();

                        }
                    });

                    dialog.setTitle("친구추가하기");
                    dialog.setCancelable(true);
                    dialog.show();

                    break;
                case R.id.btn_delFriend:
                    et = input_et.getText().toString();
                    searchQuery("delete from friend where name = '" + et + "'");
                    break;
            }
        }
    };

    //쿼리문 검색
    private void searchQuery(String query) {

        Cursor c = mDatabase.rawQuery(query, null);

        //name, phone, age 3개 컬럼갯수 리턴
        String[] col = new String[c.getColumnCount()];
        //컬럼명
        col = c.getColumnNames();

        String[] str = new String[c.getColumnCount()];
        String result="";

        while (c.moveToNext()) {

            for (int i=0;i<c.getColumnCount();i++) {
                str[i]="";
                str[i] = c.getString(i); //각 컬럼별 데이터값
                result += col[i] + " : " + str[i] + "\n";
            }

            result += "\n";
            result_txt.setText(result);
        }



    }

    private void copyAsset() {
        AssetManager assetManager = getAssets();
        String files[] =null;
        String mkdir ="";

        try {
            //assets 폴더 내부의 파일명을 문자열 배열에 저장
            //참고 files = assetManger.list("image");
            files = assetManager.list("");

        } catch (IOException e) {

            e.printStackTrace();
        }

        for (int i=0;i<files.length;i++) {

            InputStream in = null;
            OutputStream out = null;

            try {

                in = assetManager.open(files[i]);

                //폴더생성
                File mpath = new File(PATH);
                if (!mpath.exists()) {
                    isFirst = true;
                }
                if (isFirst) {
                    mpath.mkdirs();
                    //db복사
                    out = new FileOutputStream(PATH + files[i]);
                    copyFile(in, out);
                    in.close();
                    out.close();
                    isFirst = false;
                }

            } catch (Exception e) {

            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) {
        byte[] buffer = new byte[2048];
        int code;

        try {
            while ((code = in.read(buffer)) != -1) {
                out.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
