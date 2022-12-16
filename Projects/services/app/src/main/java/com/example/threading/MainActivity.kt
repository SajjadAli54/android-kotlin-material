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