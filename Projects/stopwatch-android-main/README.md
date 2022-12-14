# Android Stop Watch App

## Activity: Create a Stop Watch.

- Create the following application using Thread / Services etc for all background tasks.

Note: `This is just a sample image. Our app will have a different look and layout widgets.`

![image](https://user-images.githubusercontent.com/58862894/202622331-be0340c3-513b-42a8-b96f-9be6d655fbe0.png)

So, first MyTimer class for accessing time

```kotlin
package com.example.stopwatch

class MyTimer {
    private var seconds: Int = 0
    private var minutes: Int = 0
    private var hours: Int = 0

    fun getTime():String{
        seconds++
        if(seconds == 60){
            seconds  = 0
            minutes++
        }
        if(minutes == 60){
            minutes = 0
            hours++
        }

        return "${format(hours)}:${format(minutes)}:${format(seconds)}"
    }

    fun format(time: Int): String {
        return when(time < 10){
            true -> "0$time"
            else -> "$time"
        }
    }

    fun reset(){
        seconds = 0
        minutes = 0
        hours = 0
    }
}
```

Now let's work on thread part

```kotlin
package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val myTimer = MyTimer()
    lateinit var thread: Thread
    lateinit var textTime: TextView
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textTime = findViewById(R.id.textLabel)

        thread = Thread {
            while (true) {
                if(flag){
                    textTime.post {
                        textTime.text = myTimer.getTime()
                    }
                    Thread.sleep(1000)
                }

            }
        }
        thread.start()
    }

    fun handleStart(view: View){
        flag = true
    }

    fun handleStop(view: View){
        flag = false
    }

    fun handleReset(view: View){
        myTimer.reset()
        textTime.text = myTimer.getTime()
    }
}
```

Finallay, our UI 

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:backgroundTint="#918282"
    android:gravity="center"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="10dp"
        android:text="00:00:00"
        android:textSize="48sp" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center">

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:onClick="handleStart"
            android:backgroundTint="#514C4C"
            android:text="Start" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:onClick="handleStop"
            android:backgroundTint="#605454"
            android:text="Stop" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:onClick="handleReset"
            android:layout_margin="10dp"
            android:backgroundTint="#595353"
            android:text="Reset" />
    </LinearLayout>
</LinearLayout>
```
