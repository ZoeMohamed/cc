import 'package:flutter/services.dart';
import 'package:flutter/material.dart';

void main() async {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({key, required this.title});
  final String title;
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> with WidgetsBindingObserver {
  int _counter = 0;
  static const platform = MethodChannel('example.com/channel');
  Future<void> _generateRandomNumber() async {
    int random;
    try {
      random = await platform.invokeMethod('getRandomNumber');
    } on PlatformException catch (e) {
      print("Failed to bring app to front: '${e.message}'.");
    }
  }

  _tes() async {
    // Pin App To Top Before Running
    const platform = MethodChannel('com.example.myapp/pinAppToTop');
    await platform.invokeMethod('pinAppToTop');
  }

  @override
  void initState() {
    WidgetsBinding.instance!.addObserver(this);

    // TODO: implement initState
    super.initState();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    // TODO: implement didChangeAppLifecycleState
    super.didChangeAppLifecycleState(state);

    if (state == AppLifecycleState.paused) {
      _generateRandomNumber();
      // _tes();
    }

    if (state == AppLifecycleState.resumed) {
      _generateRandomNumber();
      // _tes();
    }

    if (state == AppLifecycleState.inactive) {
      _generateRandomNumber();
      // _tes();
    }

    // if (state == AppLifecycleState.detached) {
    //   _generateRandomNumber();
    //   // _tes();
    // }
  }

  @override
  void dispose() {
    WidgetsBinding.instance!.removeObserver(this);
    // TODO: implement dispose
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'Kotlin generates the following number:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _generateRandomNumber,
        tooltip: 'Generate',
        child: const Icon(Icons.refresh),
      ),
    );
  }
}
