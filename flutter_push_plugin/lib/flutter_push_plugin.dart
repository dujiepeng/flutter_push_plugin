import 'package:flutter/services.dart';

class FlutterPushPlugin {
  static const tokenEvent = EventChannel('token');
  static const openNotificationEvent = EventChannel('openNotification');
  static const MethodChannel channel = MethodChannel('flutter_push_plugin');

  static Stream<String> getTokenStream() {
    return tokenEvent.receiveBroadcastStream().cast<String>();
  }

  static Stream<String> getOpenNotificationStream() {
    return openNotificationEvent.receiveBroadcastStream().cast<String>();
  }

  static Future<void> registerToken() async {
    await channel.invokeMethod('init');
  }

  static Future<void> unRegisterToken() async {
    await channel.invokeMethod('unInit');
  }
}
