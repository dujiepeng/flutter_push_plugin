package com.dcstudio.flutter_push_plugin_honor;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.platform.TokenHelper;
import com.hihonor.push.sdk.HonorPushCallback;
import com.hihonor.push.sdk.HonorPushClient;

public class HonorTokenHelper extends TokenHelper {
    public HonorTokenHelper(Context context) {
        super(context);
    }

    @Override
    public void init() {
        HonorPushClient.getInstance().init(context, true);
        HonorPushClient.getInstance().getPushToken(new HonorPushCallback<String>() {
            @Override
            public void onSuccess(String pushToken) {
                IntentEventSender.sendTokenIntent(context, pushToken);
            }

            @Override
            public void onFailure(int errorCode, String errorString) {
                IntentEventSender.sendTokenError(context, String.valueOf(errorCode), "Honor get token err: " + errorString);
            }
        });
    }

    @Override
    public void unInit() {
        HonorPushClient.getInstance().deletePushToken(new HonorPushCallback<Void>() {
            @Override
            public void onSuccess(Void unused) {
                IntentEventSender.sendUnInit(context);
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }
}
