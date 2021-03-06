package com.ckh5829.user.forwarder;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.ckh5829.user.forwarder.util.JsonSharedPreference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_list, btn_add;
    Switch switch_BR;

    //등록전화번호 ArrayList
    ListView myList;
    ArrayList<String> arrs;
    NumsAdapter numsAdapter;
    //수신전화목록 Json Shared Preferences
    JsonSharedPreference jsonSP = new JsonSharedPreference();
    //BR on/off용 일반 Shard preferences
    SharedPreferences prefer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_list = (Button) findViewById(R.id.btn_list);
        btn_add = (Button) findViewById(R.id.btn_add);
        switch_BR = (Switch) findViewById(R.id.switch_BR);

        //1단계 : ListView 처리
        myList = (ListView) findViewById(R.id.myList);
        arrs = new ArrayList<>();

        //2단계 : SharedPreference 처리 (저장된 전화번호와 br switch 정보를 가져온다)
        arrs = jsonSP.getStringArrayPref(this, "ForwardNumbers");

        for (String i : arrs) {
            Log.d("MY", "Forward Number List: " + i);
        }

        //3단계 : 리스트뷰 어댑터 생성 및 연결
        numsAdapter = new NumsAdapter(this, R.layout.list_form, arrs);
        myList.setAdapter(numsAdapter);

        //4단계 : on/off 스위치 불러오기
        prefer = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        //------현재상태확인
        Boolean broadcastSwitch = prefer.getBoolean("BroadcastSwitch", false);

        if (broadcastSwitch) {
            switch_BR.setChecked(true);
            Log.d("MY", "SWITCH: TRUE ");
        } else {
            switch_BR.setChecked(false);
            Log.d("MY", "SWITCH: FALSE ");
        }

        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,  arrs.get(i) + " 등록번호 삭제완료", Toast.LENGTH_SHORT).show();
                arrs.remove(i);
                numsAdapter.notifyDataSetChanged();

                //변경된 정보 shared preference 반영
                jsonSP.setStringArrayPref(MainActivity.this, "ForwardNumbers", arrs);

                return false;
            }
        });

        switch_BR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                PackageManager pm = getApplication().getPackageManager();
                ComponentName componentName = new ComponentName(MainActivity.this, SmsBroadcastReceiver.class);

                ArrayList<String> fCheck = new ArrayList<String>();

                if (isChecked == true){
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                    Toast.makeText(MainActivity.this, "Foward-ON", Toast.LENGTH_SHORT).show();

                } else {
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                    Toast.makeText(MainActivity.this, "Foward-OFF", Toast.LENGTH_SHORT).show();
                }

                //Switch 현재상태 저장
                SharedPreferences.Editor edit = prefer.edit();
                edit.putBoolean("BroadcastSwitch", isChecked);
                edit.commit();

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_dialog);

                final EditText ed_num = (EditText) dialog.findViewById(R.id.ed_num);
                Button btn_numAdd = (Button) dialog.findViewById(R.id.btn_numAdd);

                dialog.setTitle("포워딩 번호등록");
                dialog.setCancelable(false);
                dialog.show();

                btn_numAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String num = ed_num.getText().toString();
                        if (num.length() >= 8) {
                            //ArrayList 번호추가
                            arrs.add(num);

                            //SharedPreferenct 저장
                            jsonSP.setStringArrayPref(MainActivity.this, "ForwardNumbers", arrs);

                            if (myList != null) {
                                //어댑터갱신
                                numsAdapter.notifyDataSetChanged();
                            }

                            Toast.makeText(MainActivity.this, num + " 번호 입력 OK", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, " 번호를 입력하세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
