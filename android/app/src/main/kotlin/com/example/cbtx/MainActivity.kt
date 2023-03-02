package com.example.cbtx

import android.content.Context
import android.content.Intent
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.app.ActivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings


class MainActivity: FlutterActivity() {


    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "example.com/channel"
        ).setMethodCallHandler { call, result ->
            if (call.method == "getRandomNumber") {
                bringToFront()
            } else {
                result.notImplemented()
            }

        }
    }

    fun bringToFront() {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val packageName = context.packageName

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val taskInfos = am.getRunningTasks(Int.MAX_VALUE)
            for (taskInfo in taskInfos) {
                if (taskInfo.baseActivity?.packageName == packageName) {
                    val intent = Intent(context, taskInfo.baseActivity?.javaClass)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    
                    return
                }
            }
        } else {
            val taskInfo = am.getRunningTasks(1)[0]
            if (taskInfo.topActivity?.packageName == packageName) {
                val intent = Intent(context, taskInfo.topActivity?.javaClass)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return
            }
        }

        // If app is not running, bring app to the front using the Settings app
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", packageName, null)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

    }
}


// Antoher Method Channel
//            MethodChannel(
//                flutterEngine.dartExecutor.binaryMessenger,
//                "com.example.myapp/pinAppToTop"
//            )
//                .setMethodCallHandler { call, result ->
//
//                    if (call.method == "pinAppToTop") {
//
//                        print("tes")
////                    val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
////                    if (activityManager != null) {
////                       activityManager.startLockTask();
////                    }
//
//                        val myKeyManager =
//                            getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
////                    if (myKeyManager.inKeyguardRestrictedInputMode()){
////                        return false
////                    }
//
//
//                        val notificationIntent = Intent(this, MainActivity::class.java)
//                        notificationIntent.flags =
//                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//                        val pendingIntent =
//                            PendingIntent.getActivity(this, 0, notificationIntent, 0)
//                        try {
//                            print("ANJING")
//                            pendingIntent.send()
//                        } catch (e: PendingIntent.CanceledException) {
//                            e.printStackTrace()
//                            print("BABI")
//                        }
//
//                    }

