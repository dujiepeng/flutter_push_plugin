package com.dcstudio.flutter_push_plugin;

import android.content.Context;

import androidx.annotation.NonNull;

import com.dcstudio.flutter_push_plugin.event.EaseStreamHandler;
import com.dcstudio.flutter_push_plugin.platform.PushConfigLoader;
import com.dcstudio.flutter_push_plugin.platform.TokenHelper;
import com.dcstudio.flutter_push_plugin.tools.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;


/** FlutterPushPlugin */
public class FlutterPushPlugin implements FlutterPlugin , MethodChannel.MethodCallHandler {

  private MethodChannel channel;
  private final List<EventChannel> eventChannels = new ArrayList<>();
  public static TokenHelper tokenHelper;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    PluginContext.initialize(flutterPluginBinding.getApplicationContext());
    PushConfigLoader.initialize(flutterPluginBinding.getApplicationContext());
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_push_plugin");
    channel.setMethodCallHandler(this);
    setStreamHandlers(flutterPluginBinding);
  }


  private void setStreamHandlers(FlutterPluginBinding flutterPluginBinding) {
    Context context = flutterPluginBinding.getApplicationContext();
    Map<String, EventChannel.StreamHandler> streams = new ConcurrentHashMap<>();
    streams.put("token", new EaseStreamHandler(context, TokenReceiver::new, PushIntent.TOKEN_INTENT_ACTION));
    streams.put("openNotification", new EaseStreamHandler(context, NotificationOpenReceiver::new, PushIntent.REMOTE_MESSAGE_NOTIFICATION_INTENT_ACTION));
    for (Map.Entry<String, EventChannel.StreamHandler> entry : streams.entrySet()) {
      EventChannel eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), entry.getKey());
      eventChannel.setStreamHandler(entry.getValue());
      eventChannels.add(eventChannel);
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    if (channel != null) {
      channel.setMethodCallHandler(null);
      for (EventChannel e : eventChannels) {
        e.setStreamHandler(null);
      }
      eventChannels.clear();
      tokenHelper = null;
    }
  }


  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
    if(tokenHelper == null)
    {
      result.notImplemented();
      return;
    }

    if (call.method.equals("getPlatform")) {
      result.success(Utils.getPlatform());
    } else if (call.method.equals("init")) {
      tokenHelper.init();
      result.success("");
    } else if (call.method.equals("unInit")) {
      tokenHelper.unInit();
      result.success("");
    } else{
      result.notImplemented();
    }
  }
}
