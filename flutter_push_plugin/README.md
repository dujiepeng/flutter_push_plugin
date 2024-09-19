# flutter_push_plugin

获取原生deviceToken插件

默认包含iOS，如果需要安卓，需要在依赖中添加对应的设备类型，安卓目前支持 `oppo`、`vivo`、`meizu`、`huawei`、`honor`

## 添加方式

```dart
  flutter_push_plugin:
    path: ../flutter_push_plugin
  flutter_push_plugin_meizu:
    path: ../flutter_push_plugin_meizu
  flutter_push_plugin_vivo:
    path: ../flutter_push_plugin_vivo
  flutter_push_plugin_oppo:
    path: ../flutter_push_plugin_oppo
  flutter_push_plugin_huawei:
    path: ../flutter_push_plugin_huawei
  flutter_push_plugin_honor:
    path: ../flutter_push_plugin_honor
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