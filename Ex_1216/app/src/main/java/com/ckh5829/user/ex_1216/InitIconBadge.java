package com.ckh5829.user.ex_1216;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * Created by USER on 2016-12-16.
 */

public class InitIconBadge {


    public static String getLauncherClassName(Context context) {

        //프로세서에 동작중인 packagename을 가져온다.
        PackageManager pm = context.getPackageManager();

        //실행아이콘에 배지가 보여지기 때문에 Launcher 이미지가 필요하다.
        //실행아이콘들을 뽑기위한 조건
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);

/*        int i=0;
        for (ResolveInfo resolveInfo : resolveInfos) {
            i++;
            Log.d("MY", "package Name[" + i + "] : "+resolveInfo.activityInfo.applicationInfo.packageName);

        }*/
        //브로드캐스트 리서버 리스트 뽑기
        //pm.queryBroadcastReceivers();


        for (ResolveInfo resolveInfo : resolveInfos) {
            String pkgName = resolveInfo.activityInfo.applicationInfo.packageName;

            if (pkgName.equalsIgnoreCase(context.getPackageName())) {

                String className = resolveInfo.activityInfo.name;
                return className;
            }
        }

        return null;
    }


    //쪽지갯수를 아이콘에 배지로 표시
    public static void updateIconBadge(Context context, int notiCnt) {

        //배지 아이콘 갱신을 위한 intent
        Intent badgeIntent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");

        badgeIntent.putExtra("badge_count", notiCnt);  //키값(badge_count)은 정해진 값임
        badgeIntent.putExtra("badge_count_package_name", context.getPackageName());
        badgeIntent.putExtra("badge_count_class_name", getLauncherClassName(context));

        context.sendBroadcast(badgeIntent);   //꺼져있는 앱도 뱃지를 갱신하게끔....

    }
}










