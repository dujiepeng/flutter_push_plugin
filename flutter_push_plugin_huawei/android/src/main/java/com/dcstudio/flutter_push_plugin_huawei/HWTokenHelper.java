package com.dcstudio.flutter_push_plugin_huawei;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.platform.PushConfigLoader;
import com.dcstudio.flutter_push_plugin.platform.TokenHelper;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;

public class HWTokenHelper extends TokenHelper {
    public HWTokenHelper(Context context) {
        super(context);
    }

    @Override
    public void init() {
        new Thread(() -> {
            String appId = PushConfigLoader.huaweiAppId;
            try {
                String token = HmsInstanceId.getInstance(this.context).getToken(appId, "HMS");
                IntentEventSender.sendTokenIntent(context, token);
            } catch (ApiException e) {
                IntentEventSender.sendTokenError(context, String.valueOf(e.getStatusCode()), "Hua Wei get token err: " + e.getMessage());
            }
        }).start();
    }

    @Override
    public void unInit() {
        String appId = PushConfigLoader.huaweiAppId;
        try {
            HmsInstanceId.getInstance(this.context).deleteToken(appId, "HMS");
            IntentEventSender.sendUnInit(context);
        }catch (ApiException e) {

        }

    }
}
