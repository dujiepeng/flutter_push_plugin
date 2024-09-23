package com.dcstudio.flutter_push_plugin_xiaomi;

import android.content.Context;

import com.dcstudio.flutter_push_plugin.IntentEventSender;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

public class XMReceiver extends PushMessageReceiver {
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {

    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                IntentEventSender.sendTokenIntent(context, cmdArg1);
            }else {
                IntentEventSender.sendTokenError(context, String.valueOf(message.getResultCode()), cmdArg1);
            }
        }
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                IntentEventSender.sendTokenIntent(context, cmdArg1);
            }else {
                IntentEventSender.sendTokenError(context, String.valueOf(message.getResultCode()), cmdArg1);
            }
        }
    }
}
