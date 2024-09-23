package com.dcstudio.flutter_push_plugin_xiaomi;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.platform.PushConfigLoader;
import com.dcstudio.flutter_push_plugin.platform.TokenHelper;
import com.xiaomi.mipush.sdk.MiPushClient;

public class XMTokenHelper extends TokenHelper {
    public XMTokenHelper(Context context) {
        super(context);
    }

    @Override
    public void init() {
        MiPushClient.registerPush(context, PushConfigLoader.xiaomiAppId,PushConfigLoader.xiaomiAppKey);
        MiPushClient.enablePush(context);
    }

    @Override
    public void unInit() {
        MiPushClient.disablePush(context);
        IntentEventSender.sendUnInit(context);
    }
}
