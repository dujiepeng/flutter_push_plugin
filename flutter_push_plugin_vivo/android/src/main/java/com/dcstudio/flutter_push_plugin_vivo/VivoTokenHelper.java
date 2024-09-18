package com.dcstudio.flutter_push_plugin_vivo;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.platform.TokenHelper;
import com.vivo.push.PushClient;
import com.vivo.push.PushConfig;
import com.vivo.push.listener.IPushQueryActionListener;
import com.vivo.push.util.VivoPushException;

public class VivoTokenHelper extends TokenHelper {
    public VivoTokenHelper(Context context) {
        super(context);
    }

    @Override
    public void init() {
        try {
            PushClient.getInstance(context).checkManifest();
            PushConfig config = new PushConfig.Builder()
                    .agreePrivacyStatement(true)
                    .build();
            PushClient.getInstance(context).initialize(config);

            PushClient.getInstance(context).turnOnPush(i -> {
                if(i == 0) {
                    PushClient.getInstance(context).getRegId(new IPushQueryActionListener() {
                        @Override
                        public void onSuccess(String regId) {
                            IntentEventSender.sendTokenIntent(context, regId);
                        }

                        @Override
                        public void onFail(Integer errCode) {
                            IntentEventSender.sendTokenError(context, errCode.toString(), "vivo get token err.");
                        }
                    });
                } else {
                    IntentEventSender.sendTokenError(context, String.valueOf(i), "vivo register err,  onStatueChanged: " + i);
                }
            });
        } catch (VivoPushException e) {
            IntentEventSender.sendTokenError(context, String.valueOf(e.getCode()), "vivo register err, " + e.getMessage());
        }

    }

    @Override
    public void unInit() {
        PushClient.getInstance(context).turnOffPush(i -> {
            if(i == 0) {
                IntentEventSender.sendUnInit(context);
            }
        });
    }
}
