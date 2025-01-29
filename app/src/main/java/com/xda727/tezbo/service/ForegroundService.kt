package com.xda727.tezbo.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.xda727.tezbo.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ActionType.START.toString() -> start()
            ActionType.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "tezbo_notification_channel").apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle("Service is running . ...")
            setContentText("Context text")
        }.build()
        startForeground(1, notification)
        runner()
    }

    private var count = 0

    @SuppressLint("MissingPermission")
    @OptIn(DelicateCoroutinesApi::class)
    private fun runner() {
        val context = this

        GlobalScope.launch {
            while (true) {
                delay(
                    5000
                )
                Log.e("RUnner", "count: $count")
                count++


                val notification = NotificationCompat.Builder(context, "tezbo_notification_channel")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Custom Sound Notification $notificationId")
                    .setContentText("This notification plays a custom sound!")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .build()

                with(NotificationManagerCompat.from(context)) {

                    notify(notificationId, notification)
                    notificationId++
                }


            }
        }


    }


    private var notificationId = 1

    companion object {

    }


}