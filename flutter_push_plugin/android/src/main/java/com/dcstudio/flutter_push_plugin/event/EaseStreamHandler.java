package com.dcstudio.flutter_push_plugin.event;

import android.content.BroadcastReceiver;
import android.content.Context;

import com.dcstudio.flutter_push_plugin.PushIntent;

import io.flutter.plugin.common.EventChannel;

public class EaseStreamHandler implements EventChannel.StreamHandler {

    public EaseStreamHandler(Context context, CreateBroadcastReceiverCallback cb, PushIntent intentAction) {
        this.context = context;
        this.cb = cb;
        this.intentAction = intentAction;
    }

    protected Context context;

    protected BroadcastReceiver broadcastReceiver;

    protected CreateBroadcastReceiverCallback cb;

    protected PushIntent intentAction;


    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        broadcastReceiver = cb.createBroadcastReceiver(context, events);
    }

    @Override
    public void onCancel(Object arguments) {
        context.unregisterReceiver(broadcastReceiver);
    }

}
