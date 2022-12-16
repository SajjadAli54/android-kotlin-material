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