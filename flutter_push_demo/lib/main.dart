import 'package:flutter/material.dart';
import 'package:flutter_push_plugin/flutter_push_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String? _text;
  @override
  void initState() {
    // FlutterPushPlugin.init();

    FlutterPushPlugin.getOpenNotificationStream().listen((event) {
      setState(() {
        _text = event;
      });
    }).onDone(() {
      setState(() {
        _text = "done";
      });
    });

    FlutterPushPlugin.getTokenStream().listen((event) {
      debugPrint('Token: $event');
      setState(() {
        _text = event;
      });
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextButton(
                onPressed: () {
                  FlutterPushPlugin.registerToken();
                },
                child: const Text('getToken')),
            TextButton(
                onPressed: () {
                  FlutterPushPlugin.unRegisterToken();
                },
                child: const Text('unInit')),
            Text(_text ?? ""),
          ],
        ),
      ),
    );
  }
}
