package com.dcstudio.flutter_push_plugin_vivo;

import androidx.annotation.NonNull;

import com.dcstudio.flutter_push_plugin.FlutterPushPlugin;
import com.dcstudio.flutter_push_plugin.PluginContext;
import com.dcstudio.flutter_push_plugin.tools.Utils;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterPushPluginVivoPlugin */
public class FlutterPushPluginVivoPlugin implements FlutterPlugin, MethodCallHandler {
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    if(Utils.getPlatform().equals("VIVO")) {
      PluginContext.initialize(flutterPluginBinding.getApplicationContext());
      FlutterPushPlugin.tokenHelper = new VivoTokenHelper(flutterPluginBinding.getApplicationContext());
    }
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
  }
}
