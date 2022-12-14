# Create a Media Player using Foreground Service
Create a mediaplayer using foreground service, which will constantly show the song being currently
played in Notification bar.

First Include the permission in Manifest file

```xml
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
```

Now, Create a Service class using Android Studio. I have named it ```MyService```

```kotlin
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

    private val channelId = "MUSIC_PLAYER"
    private val channelName = "Music Player"

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

        val largeView = RemoteViews(packageName, R.layout.music_large)
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
```

Layout resource code music_small

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Music is currently playing" />
</LinearLayout>
```

Layout resource code for music_large

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_horizontal"
    android:orientation="vertical">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Music song" />

    <Button
        android:id="@+id/btn"
        android:text="Play"
        android:layout_width="57dp"
        android:layout_height="48dp"
        />
</LinearLayout>
```

Main Activity Code

```kotlin
package com.example.mymediaplayer

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun play(view: View){
        var serviceIntent = Intent(this, MyService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(serviceIntent)
        else startService(serviceIntent)

    }

    fun pause(view: View){
        var serviceIntent = Intent(this, MyService::class.java)
        stopService(serviceIntent)
    }
}
```

Main Activity layout resource code
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:gravity="center"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/btnPlay"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="play"
        android:text="Play" />

    <Button
        android:id="@+id/btnPause"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="pause"
        android:text="Pause" />
</LinearLayout>
```

That's it. Thanks!

