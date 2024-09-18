package com.dcstudio.flutter_push_plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

import java.util.Objects;

import io.flutter.plugin.common.EventChannel;

public class NotificationOpenReceiver extends BroadcastReceiver {
    final EventChannel.EventSink events;
    public NotificationOpenReceiver(Context context, EventChannel.EventSink events) {
        IntentFilter filter = new IntentFilter(PushIntent.REMOTE_MESSAGE_NOTIFICATION_INTENT_ACTION.id());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.registerReceiver(this, filter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            context.registerReceiver(this, filter);
        }
        this.events = events;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.requireNonNull(intent.getAction()).equals(PushIntent.REMOTE_MESSAGE_NOTIFICATION_INTENT_ACTION.id())) {
            try {
                final String res = intent.getStringExtra(PushIntent.NOTIFICATION_OPEN.id());
                this.events.success(res);
            } catch (Exception e) {
                this.events.error("", e.getMessage(), "");
            }
        }
    }
}
