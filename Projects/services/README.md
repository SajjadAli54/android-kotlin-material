# Android Services

## Services Code

```kotlin
package com.example.threading

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyServiceForeground : Service() {

    val CHANNEL_ID: String = "mad-fall22-b"
    val CHANNEL_NAME = "MAD - Fall 2022 - B"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1234, createNotification())
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        stopSelf()
        stopForeground(true)
        super.onDestroy()
    }

    private fun createNotification(): Notification{
        val manager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        val notification: Notification =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setContentTitle(CHANNEL_NAME)
                .setContentText("This is a test notification, which does nothing")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        manager.createNotificationChannel(channel)

        return notification
    }
}
```

## Main Activity Code

```kotlin
package com.example.threading

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var serviceIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startService(view: View){
        serviceIntent = Intent(this, MyServiceForeground::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        }
        else{
            startService(serviceIntent)
        }
    }

    fun stop(view: View){
        stopService(serviceIntent)
    }
}
```

## UI Layout Code

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:orientation="vertical"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="startService"
        android:text="Hello World!"
        />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="stop"
        android:text="Stop"
        />

</LinearLayout>
```

That's it. Thanks!
