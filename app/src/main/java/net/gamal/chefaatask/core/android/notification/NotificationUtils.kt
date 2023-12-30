package net.sbs.softpos.finpay.core.android.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import javax.inject.Inject
import kotlin.random.Random

@SuppressLint("ObsoleteSdkInt")
class NotificationUtils @Inject constructor(private var builder: NotificationBuilder.Builder) {

    fun showNotification(title: String, message: String) {
        val notificationID = Random.nextInt()
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
            NotificationCompat.Builder(builder.context, builder.channelId!!).apply {
                setContentTitle(title)
                setContentText(message)
                setAutoCancel(true)
                priority = NotificationCompat.PRIORITY_HIGH
                setSound(defaultSoundUri)
                setContentIntent(builder.pendingIntent!!)
                setSmallIcon(builder.imageRes!!)
                setVibrate(longArrayOf(0, 1000, 500, 1000))
                setDefaults(Notification.DEFAULT_ALL)
                setWhen(System.currentTimeMillis())
                setVisibility(NotificationCompat.VISIBILITY_PUBLIC)    //to show content in lock screen
                setGroup(groupID)
            }
        val notificationManager =
            builder.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                builder.channelName!!, builder.channelDescription!!, notificationManager
            )
        }

        notificationManager.notify(notificationID, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(
        channelName: String, channelDescription: String, notificationManager: NotificationManager
    ) {
        val channel = NotificationChannel(
            builder.channelId, channelName, NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = channelDescription
            enableLights(true)
            vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            lightColor = builder.context.getColor(builder.colorRes!!)
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun updateBuilder(builder: NotificationBuilder.Builder) {
        this.builder.apply {
            builder.channelId?.let { setChannelId(it) }
            builder.channelName?.let { setChannelName(it) }
            builder.channelDescription?.let { setChannelDescription(it) }
            builder.colorRes?.let { setColorRes(it) }
            builder.imageRes?.let { setImageRes(it) }
            builder.pendingIntent?.let { setPendingIntent(it) }
            build()
        }
    }

    fun builderData(): NotificationBuilder.Builder = builder

    companion object {
        private var groupID: String = "FinPay"
    }
}