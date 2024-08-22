import 'package:flutter/material.dart';

import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  static const platform = MethodChannel('com.example.app2/auth');

  Future<void> _getAuthToken() async {
    try {
      final String authToken = await platform.invokeMethod('getAuthToken');
      print('Auth token: $authToken');
    } on PlatformException catch (e) {
      print("Failed to get auth token: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("App 2"),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: _getAuthToken,
            child: Text("Get Auth Token"),
          ),
        ),
      ),
    );
  }
}
