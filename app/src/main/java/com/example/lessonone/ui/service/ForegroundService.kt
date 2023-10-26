package com.example.lessonone.ui.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.lessonone.R

private const val CHANNEL_ID: String = "CHANNEL_ID"

class ForegroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("LaunchActivityFromNotification")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(serviceChannel)
        }
        val notificationIntent = Intent(this, MyBroadcastReceiver::class.java)
        notificationIntent.action = Constant.ID_ACTION
        val pendingIntent: PendingIntent =
            PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_MUTABLE)

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.content_title))
            .setContentText(getString(R.string.content_text))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()

        startForeground(1, notification)

        return super.onStartCommand(intent, flags, startId)
    }
}