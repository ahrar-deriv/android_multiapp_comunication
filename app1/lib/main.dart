import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: LoginScreen(),
    );
  }
}

class LoginScreen extends StatelessWidget {
  final FlutterSecureStorage _secureStorage = FlutterSecureStorage();

  void _saveDummyToken() async {
    // Save a dummy token
    String token = "dummy_token";
    await _secureStorage.write(key: "auth_token", value: token);
    print("Dummy token saved");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Login"),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: _saveDummyToken,
          child: Text("Save Dummy Token"),
        ),
      ),
    );
  }
}
