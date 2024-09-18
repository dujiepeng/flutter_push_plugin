package com.dcstudio.flutter_push_plugin.event;

import android.content.BroadcastReceiver;
import android.content.Context;

import io.flutter.plugin.common.EventChannel;

public interface CreateBroadcastReceiverCallback {
    BroadcastReceiver createBroadcastReceiver(Context context, EventChannel.EventSink events);
}
