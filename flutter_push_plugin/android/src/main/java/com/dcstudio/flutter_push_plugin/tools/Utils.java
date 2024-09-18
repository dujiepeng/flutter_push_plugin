package com.dcstudio.flutter_push_plugin.tools;

import android.os.Build;

public class Utils {
    public static String getPlatform() {
        return Build.MANUFACTURER.toUpperCase();
    }

    public static boolean isNotEmpty(String str) {
        if(str != null && str.trim().length() != 0){
            return true;
        }else {
            return false;
        }
    }
}
