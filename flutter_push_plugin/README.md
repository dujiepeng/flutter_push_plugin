# flutter_push_plugin

获取原生deviceToken插件

默认包含iOS，如果需要安卓，需要在依赖中添加对应的设备类型，安卓目前支持 `oppo`、`vivo`、`meizu`、`huawei`、`honor`

## 添加方式

```dart
  flutter_push_plugin: ^0.0.2
  flutter_push_plugin_meizu: ^0.0.1
  flutter_push_plugin_vivo: ^0.0.1
  flutter_push_plugin_oppo: ^0.0.1
  flutter_push_plugin_huawei: ^0.0.1
  flutter_push_plugin_honor: ^0.0.1
  flutter_push_plugin_xiaomi: ^0.0.1
```

## 获取 token

1. 添加监听

```dart
FlutterPushPlugin.getTokenStream().listen((event) {
  debugPrint('Token: $event');
  setState(() {
    _text = event;
  });
});
   ```

2. 注册token

```dart
    FlutterPushPlugin.registerToken();
```

3. 获取当前平台

```dart
  String? platform = await FlutterPushPlugin.getPlatform();
```