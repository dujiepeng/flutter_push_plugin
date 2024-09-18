package com.dcstudio.flutter_push_plugin_oppo;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.TokenError;
import com.dcstudio.flutter_push_plugin.platform.PushConfigLoader;
import com.dcstudio.flutter_push_plugin.platform.TokenHelper;
import com.dcstudio.flutter_push_plugin.tools.Utils;
import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;

public class OppoTokenHelper extends TokenHelper {
    public OppoTokenHelper(Context context) {
        super(context);
    }

    @Override
    public void init() {
        if(!HeytapPushManager.isSupportPush(context)) {
            IntentEventSender.sendTokenError(context, TokenError.unSupportCode, TokenError.unSupportCodeDesc);
            return;
        }

        HeytapPushManager.requestNotificationPermission();
        HeytapPushManager.init(context, false);
        String token = HeytapPushManager.getRegisterID();
        if(Utils.isNotEmpty(token)) {
            IntentEventSender.sendTokenIntent(context, token);
        }else {
            HeytapPushManager.register(context, PushConfigLoader.oppoAppKey, PushConfigLoader.oppoAppSecret, new ICallBackResultService() {
                @Override
                public void onRegister(int i, String s) {
                    if(i == 0) {
                        IntentEventSender.sendTokenIntent(context, s);
                    }else {
                        IntentEventSender.sendTokenError(context, TokenError.getTokenError, "oppo get token err: " + s);
                    }
                }

                @Override
                public void onUnRegister(int i) {

                }

                @Override
                public void onSetPushTime(int i, String s) {

                }

                @Override
                public void onGetPushStatus(int i, int i1) {

                }

                @Override
                public void onGetNotificationStatus(int i, int i1) {

                }

                @Override
                public void onError(int i, String s) {
                    IntentEventSender.sendTokenError(context, String.valueOf(i), "Oppo get token err: " + s);
                }
            });
        }
    }

    @Override
    public void unInit() {
        HeytapPushManager.unRegister();
        IntentEventSender.sendUnInit(context);
    }
}
