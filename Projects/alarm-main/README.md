# Create a simple alaram application, which will notifiy user at specific time

Use the alaram manager to create an alarm service which will notify user on specific times.

## Executing the app

![image](https://user-images.githubusercontent.com/58862894/203142924-cf6a8392-c67f-4e08-9587-0e1364e53f98.png)

![image](https://user-images.githubusercontent.com/58862894/203142992-ec345463-7eeb-4992-8dc9-722415fa924f.png)

![image](https://user-images.githubusercontent.com/58862894/203143023-f8734b71-9ed1-4158-a043-7760a07ae3e6.png)

![image](https://user-images.githubusercontent.com/58862894/203143048-49ebf25a-7a94-444e-b1b7-219a66b5deab.png)

## Code understanding

I have created this using only two activities. First activity sets an alarm while second opens up and just plays a song as a alarm.


### Main Activity Code

I have used here TimePickerDialog which takes 5 parameters.

```kotlin
TimePickerDialog(
    Context, 
    TimePickerDialogListener, 
    DefaultHourOfDialog(Int), 
    DefaultMinuteOfDialog(Int), 
    is24HourView(boolean))
```

I have declared the listener at the end with its only method ```onTimeSet```

```kotlin
package com.example.myalarmdemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import java.lang.Long

class MainActivity : AppCompatActivity() {

    lateinit var previewSelectedTimeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        previewSelectedTimeTextView = findViewById(R.id.textView)
    }

    fun handlePick(view: View){
        val timePicker: TimePickerDialog =
            TimePickerDialog(this,
                timePickerDialogListener, 12, 10, false)
        timePicker.show()
    }

    private val timePickerDialogListener : TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minutes ->
            val formattedText = when{
                hourOfDay == 0 -> "${format(hourOfDay, minutes, 12)} AM"
                hourOfDay > 12 -> "${format(hourOfDay, minutes, -12)} PM"
                hourOfDay == 12 -> "${format(hourOfDay, minutes, 0)} PM"
                else -> "${format(hourOfDay, minutes, 0)} AM"
            }
            previewSelectedTimeTextView.text = formattedText

            val intent = Intent(applicationContext, SecondActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

            val manager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            manager.set(
                AlarmManager.RTC_WAKEUP,
                (hourOfDay * 60 * 60 * 1000 + minutes * 60 * 1000).toLong(),
                pendingIntent
            )
        }

    private fun format(hours: Int, mins: Int, add: Int): String{
        return "${hours + add}:${if(mins < 10) "0$mins" else "$mins"}"
    }
}
```

Code for Second Activity 

```kotlin
package com.example.myalarmdemo

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        mediaPlayer = MediaPlayer.create(this, R.raw.song)
        mediaPlayer.start()
    }

    fun handleStop(view: View){
        mediaPlayer.pause()
        finish()
    }
}
```

Create the main activity layout
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/btnPick"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="handlePick"
        android:text="Set Alarm" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="SHow Time" />
</LinearLayout>
```

Now create the second activity resource layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:gravity="center"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SecondActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="handleStop"
        android:text="Stop" />
</LinearLayout>
```

That's it! Thanks.
