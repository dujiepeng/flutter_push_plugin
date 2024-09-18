package com.dcstudio.flutter_push_plugin_meizu;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.platform.PushConfigLoader;
import com.dcstudio.flutter_push_plugin.platform.TokenHelper;
import com.dcstudio.flutter_push_plugin.tools.Utils;
import com.meizu.cloud.pushsdk.PushManager;

public class MzTokenHelper extends TokenHelper {
    public MzTokenHelper(Context context) {
        super(context);
    }

    @Override
    public void init() {
        new Thread(() -> {
            String pushId =  PushManager.getPushId(context);
            if (Utils.isNotEmpty(pushId)) {
                IntentEventSender.sendTokenIntent(context, pushId);
            }else {
                PushManager.register(context, String.valueOf(PushConfigLoader.meizuAppId), PushConfigLoader.meizuAppKey);
            }
        }).start();
    }

    @Override
    public void unInit() {
        new Thread(() -> {
            PushManager.unRegister(context, String.valueOf(PushConfigLoader.meizuAppId), PushConfigLoader.meizuAppKey);
        }).start();
    }
}
