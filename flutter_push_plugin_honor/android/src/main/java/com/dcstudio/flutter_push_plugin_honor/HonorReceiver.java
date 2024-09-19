package com.dcstudio.flutter_push_plugin_honor;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.PluginContext;
import com.hihonor.push.sdk.HonorMessageService;

public class HonorReceiver extends HonorMessageService {
    @Override
    public void onNewToken(String pushToken) {
        IntentEventSender.sendTokenIntent(PluginContext.getContext(), pushToken);
    }
}
