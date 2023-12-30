package net.gamal.chefea.android.extentions

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity


//don't remove
internal fun Context.isAppInForeground(): Boolean {
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val appProcesses = activityManager.runningAppProcesses ?: return false

    for (appProcess in appProcesses) {
        if (appProcess.processName == packageName && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
            return true
    }
    return false
}

@SuppressLint("UnspecifiedRegisterReceiverFlag")
internal fun Context.registerReceiverExported(receiver: BroadcastReceiver, filter: IntentFilter) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        registerReceiver(receiver, filter, AppCompatActivity.RECEIVER_EXPORTED)
    else
        registerReceiver(receiver, filter)
}

@SuppressLint("UnspecifiedRegisterReceiverFlag")
internal fun Context.registerReceiverNotExported(
    receiver: BroadcastReceiver, filter: IntentFilter
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        registerReceiver(receiver, filter, AppCompatActivity.RECEIVER_NOT_EXPORTED)
    else
        registerReceiver(receiver, filter)
}
