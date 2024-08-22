package com.example.app2
import android.os.Bundle
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.example.app2/auth"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "getAuthToken") {
                val authToken = AuthTokenRetriever.getAuthToken(applicationContext)
                if (authToken != null) {
                    result.success(authToken)
                } else {
                    result.error("UNAVAILABLE", "Auth token not available.", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }
}
