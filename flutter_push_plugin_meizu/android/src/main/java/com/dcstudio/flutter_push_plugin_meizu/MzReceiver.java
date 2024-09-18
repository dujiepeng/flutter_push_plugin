package com.dcstudio.flutter_push_plugin_meizu;

import static com.meizu.cloud.pushsdk.platform.message.BasicPushStatus.SUCCESS_CODE;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.dcstudio.flutter_push_plugin.TokenError;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public class MzReceiver extends MzPushMessageReceiver {

    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        if(context != null) {
            if(registerStatus.getCode().equals(SUCCESS_CODE)){
                String token = registerStatus.getPushId();
                IntentEventSender.sendTokenIntent(context, token);
            }else {
                IntentEventSender.sendTokenError(context, TokenError.getTokenError, registerStatus.getCode());
            }
        }
    }

    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        if(context != null) {
            if(unRegisterStatus.isUnRegisterSuccess()) {
                IntentEventSender.sendUnInit(context);
            }else {
                IntentEventSender.sendTokenError(context, TokenError.getTokenError,  "meizu unregister err.");
            }
        }
    }

    public void onPushStatus(android.content.Context context, com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus pushSwitchStatus) {
        System.out.println('a');
    }

    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {

    }

    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {

    }

    @Override
    public void onNotificationClicked(Context context, MzPushMessage mzPushMessage) {
        super.onNotificationClicked(context, mzPushMessage);
//        Utils.sendNotificationOpenedAppEvent(context, MzMessageHelper.toJson(mzPushMessage));
    }
}
