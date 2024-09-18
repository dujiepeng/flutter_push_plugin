package com.dcstudio.flutter_push_plugin;


import android.content.Context;
import android.content.Intent;

import com.dcstudio.flutter_push_plugin.tools.Utils;


public class IntentEventSender {

    public static void sendTokenIntent(Context context,  String token) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction(PushIntent.TOKEN_INTENT_ACTION.id());
            intent.putExtra(PushIntent.TOKEN.id(), token);
            context.sendBroadcast(intent);
        }
    }

    public static void sendUnInit(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction(PushIntent.TOKEN_INTENT_ACTION.id());
            intent.putExtra(PushIntent.UN_INIT.id(), "");
            context.sendBroadcast(intent);
        }
    }

    public static void sendTokenError(Context context, String errCode, String errDesc) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction(PushIntent.TOKEN_INTENT_ACTION.id());
            intent.putExtra(PushIntent.TOKEN_ERROR.id(), errCode);
            if(Utils.isNotEmpty(errDesc)) {
                intent.putExtra(PushIntent.TOKEN_ERROR_DESC.id(), errDesc);
            }
            context.sendBroadcast(intent);
        }
    }


    public static void sendIntent( Context context, PushIntent action, PushIntent extraName1, String extra1, PushIntent extraName2, String extra2) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction(action.id());
            intent.putExtra(extraName1.id(), extra1);
            if(extra2 != null) {
                intent.putExtra(extraName2.id(), extra2);
            }
            context.sendBroadcast(intent);
        }
    }

}
