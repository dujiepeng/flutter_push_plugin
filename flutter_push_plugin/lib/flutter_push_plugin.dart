import 'dart:io';

import 'package:flutter/services.dart';

class FlutterPushPlugin {
  static const _tokenEvent = EventChannel('token');
  static const _openNotificationEvent = EventChannel('openNotification');
  static const _channel = MethodChannel('flutter_push_plugin');

  static Stream<String> getTokenStream() {
    return _tokenEvent.receiveBroadcastStream().cast<String>();
  }

  static Stream<String> getOpenNotificationStream() {
    return _openNotificationEvent.receiveBroadcastStream().cast<String>();
  }

  static Future<void> registerToken() async {
    await _channel.invokeMethod('init');
  }

  static Future<void> unRegisterToken() async {
    await _channel.invokeMethod('unInit');
  }

  static Future<String?> getPlatform() async {
    if (Platform.isAndroid) {
      final version = await _channel.invokeMethod<String>('getPlatform');
      return version;
    } else {
      return 'IOS';
    }
  }
}
