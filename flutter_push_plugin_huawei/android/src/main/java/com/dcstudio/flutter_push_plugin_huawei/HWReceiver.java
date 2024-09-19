package com.dcstudio.flutter_push_plugin_huawei;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.PluginContext;
import com.dcstudio.flutter_push_plugin.TokenError;
import com.huawei.hms.push.HmsMessageService;

public class HWReceiver extends HmsMessageService {
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        IntentEventSender.sendTokenIntent(PluginContext.getContext(), token);
    }

    @Override
    public void onTokenError(Exception e) {
        super.onTokenError(e);
        IntentEventSender.sendTokenError(PluginContext.getContext(), TokenError.getTokenError, "HuaWei get token err: " + e.getMessage());
    }
}
