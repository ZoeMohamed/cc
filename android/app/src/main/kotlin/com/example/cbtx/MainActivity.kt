package com.example.cbtx

import kotlin.random.Random
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "example.com/channel").setMethodCallHandler {
                call, result ->
            if(call.method == "getRandomNumber") {
                val rand = Random.nextInt(100)
                result.success(rand)
            }
            else {
                result.notImplemented()
            }
        }

        // Antoher Method Channel
         MethodChannel(flutterEngine.dartExecutor.binaryMessenger,  "com.example.myapp/pinAppToTop")
            .setMethodCallHandler { call, result ->
                if (call.method == "pinAppToTop") {
                    ProcessHelper.pinAppToTop(this)
                    result.success(true)
                } else {
                    result.notImplemented()
                }
            }
    }
}