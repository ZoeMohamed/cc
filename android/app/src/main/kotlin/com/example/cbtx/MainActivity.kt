package com.example.cbtx

import android.app.ActivityManager
import android.app.KeyguardManager
import android.app.PendingIntent
import android.app.PendingIntent.CanceledException
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
import android.os.Build
import android.util.Log
import android.view.KeyEvent
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel


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

         MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "example.com/channel"
        ).setMethodCallHandler { call, result ->
            if (call.method == "getRandomNumber") {

            } else {
                result.notImplemented()
            }

        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            // do nothing
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun bringToFront() {

        // if (Build.VERSION.RELEASE >= 11.toString()) { // honeycomb
        //     // Log.d("TAG", "==== Android 11 ====")
        //     // Log.d("TAG".toString())
        //     // Log.d("TAG", android.os.Build.VERSION.SDK_INT.toString())
        //     // Log.d("TAG", android.os.Build.VERSION_CODES.HONEYCOMB.toString())

            
        //     val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        //     val rt = am.getRunningTasks(Int.MAX_VALUE)

        //     for (i in rt.indices) {
        //         // bring to front
        //         if (rt[i].baseActivity!!.toShortString().indexOf("com.example.cbtx") > -1) {
        //             am.moveTaskToFront(rt[i].id, ActivityManager.MOVE_TASK_WITH_HOME)
        //         }
        //     }
        // } else {
            
        val myKeyManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
        if (myKeyManager.inKeyguardRestrictedInputMode()) return
        Log.d("TAG", "====Bringging Application to Front====")
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or FLAG_ACTIVITY_REORDER_TO_FRONT
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        try {
            pendingIntent.send()
        } catch (e: CanceledException) {
            e.printStackTrace()
        }
        }
   
    // }
}



//            val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
//            val recentTasks = activityManager.getRunningTasks(Int.MAX_VALUE)
//
//            for (i in recentTasks.indices) {
////                Log.d(
////                    "Executed app", "Application executed : "
////                            + recentTasks[i].baseActivity!!.toShortString()
////                            + "\t\t ID: " + recentTasks[i].id + ""
////                )
//                // bring to front
//                if (recentTasks[i].baseActivity!!.toShortString().indexOf("com.example.cbtx") > -1) {
//                    activityManager.moveTaskToFront(
//                        recentTasks[i].id,
//                        ActivityManager.MOVE_TASK_WITH_HOME
//                    )
//                }
//            }


// val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
// val packageName = context.packageName

// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//     val taskInfos = am.getRunningTasks(Int.MAX_VALUE)
//     for (taskInfo in taskInfos) {
//         if (taskInfo.baseActivity?.packageName == packageName) {
//             val intent = Intent(context, taskInfo.baseActivity?.javaClass)
//             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//             context.startActivity(intent)

//             return
//         }
//     }
// } else {
//     val taskInfo = am.getRunningTasks(1)[0]
//     if (taskInfo.topActivity?.packageName == packageName) {
//         val intent = Intent(context, taskInfo.topActivity?.javaClass)
//         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//         context.startActivity(intent)
//         return
//     }
// }

// // // If app is not running, bring app to the front using the Settings app
// val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
// intent.data = Uri.fromParts("com.example.cbtx", packageName, null)
// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
// context.startActivity(intent)


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

