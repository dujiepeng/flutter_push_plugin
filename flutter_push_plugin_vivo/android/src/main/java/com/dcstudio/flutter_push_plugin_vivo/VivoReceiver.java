package com.dcstudio.flutter_push_plugin_vivo;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

public class VivoReceiver extends OpenClientPushMessageReceiver {
    @Override
    public void onReceiveRegId(Context context, String regId) {
        super.onReceiveRegId(context, regId);
        IntentEventSender.sendTokenIntent(context, regId);
    }

    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage msg) {
        super.onNotificationMessageClicked(context, msg);
    }
}
