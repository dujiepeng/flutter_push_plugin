package com.dcstudio.flutter_push_plugin.platform;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class PushConfigLoader {

    static public int meizuAppId;
    static public String meizuAppKey;

    static public String oppoAppKey;
    static public String oppoAppSecret;


    static public void initialize(Context context)  {
        try {
            String packageName = context.getPackageName();
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            meizuAppId = info.metaData.getInt("MEIZU_APP_ID", 0);
            meizuAppKey = info.metaData.getString("MEIZU_APP_KEY", "");
            oppoAppKey = info.metaData.getString("OPPO_APP_KEY", "");
            oppoAppSecret = info.metaData.getString("OPPO_APP_SECRET", "");
            System.out.println("");
        }catch (PackageManager.NameNotFoundException e) {

        }
    }
}
