package com.example.mymediaplayer

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

class MyService : Service() {

    val channelId = "MUSIC_PLAYER"
    val channelName = "Music Player"

    lateinit var largeView: RemoteViews

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.song)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1234, createNotification(intent))

        mediaPlayer.start()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        mediaPlayer.pause()
        stopSelf()
        stopForeground(true)
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun createNotificationChannel(){
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        else TODO("VERSION.SDK_INT < O")
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    private fun createNotification(intent: Intent?): Notification {
        createNotificationChannel()

        val input = intent!!.getStringExtra("inputExtra")
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = intent.let {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }

        largeView = RemoteViews(packageName, R.layout.music_large)
        val smallView = RemoteViews(packageName, R.layout.music_small)

        return NotificationCompat
            .Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(smallView)
            .setCustomBigContentView(largeView)
            .setContentTitle(channelName)
            .setContentText(input)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
    }
}