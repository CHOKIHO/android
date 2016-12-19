package com.ckh5829.user.forwarder;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_on, btn_off, btn_list, btn_add;
    Switch switch_BR;

    //등록전화번호 ArrayList
    ListView myList;
    ArrayList<String> arrs;
    NumsAdapter numsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_on = (Button) findViewById(R.id.btn_on);
        btn_off = (Button) findViewById(R.id.btn_off);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_add = (Button) findViewById(R.id.btn_add);
        switch_BR = (Switch) findViewById(R.id.switch_BR);

        //ListView 처리
        myList = (ListView) findViewById(R.id.myList);
        arrs = new ArrayList<>();

        numsAdapter = new NumsAdapter(this, R.layout.list_form, arrs, myList);

        myList.setAdapter(numsAdapter);

        switch_BR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                PackageManager pm = getApplication().getPackageManager();
                ComponentName componentName = new ComponentName(MainActivity.this, SmsBroadcastReceiver.class);

                if (isChecked == true){
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                    Toast.makeText(MainActivity.this, "Foward-ON", Toast.LENGTH_SHORT).show();

                } else {
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                    Toast.makeText(MainActivity.this, "Foward-OFF", Toast.LENGTH_SHORT).show();
                }

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



        btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm = getApplication().getPackageManager();
                ComponentName componentName = new ComponentName(MainActivity.this, SmsBroadcastReceiver.class);
                pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

                Toast.makeText(getApplicationContext(), "activated", Toast.LENGTH_LONG).show();
            }
        });

        btn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm  = getApplication().getPackageManager();
                ComponentName componentName = new ComponentName(MainActivity.this, SmsBroadcastReceiver.class);

                pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                Toast.makeText(getApplicationContext(), "cancelled", Toast.LENGTH_LONG).show();

            }
        });


        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("android.provider.Telephony.SMS_RECEIVED");
                List<ResolveInfo> infos = getPackageManager().queryBroadcastReceivers(intent, 0);
                Log.d("MY", "onClick:------------------------------------ ");
                for (ResolveInfo info : infos) {

                    Log.d("MY", "Receiver name:" + info.activityInfo.name + ";     priority=" + info.priority);
                }
            }

        });

    }
}
