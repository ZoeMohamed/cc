package com.example.cbtx

import android.app.ActivityManager
import android.content.Context
import android.os.Process

object ProcessHelper {
    fun pinAppToTop(context: Context) {

        
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningProcesses = activityManager.runningAppProcesses

        // Sort the list of running processes by memory usage
        runningProcesses.sortByDescending { it.memoryInfo.totalPss }

        // Get the PID of the first process in the sorted list
        val pid = runningProcesses.first().pid

        // Pin the app to the very first
        Process.setProcessPriority(pid, Process.THREAD_PRIORITY_FOREGROUND)
    }
}