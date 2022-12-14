package com.example.callbacksdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text = """
            1. What callbacks are called when an app is first launched?
            A: onCreate, onStart and onResume
            
            2. What callbacks occur when home is clicked?
            A: onPause, onStop
            
            3. What callbacks occur when an app is restarted from launcher?
            A: onRestart, onStart, nResume
            
            4. What callbacks are called when the device is rotated?
            A: onPause, onStop, onDestroy, onCreate, onStart and onResume
        """.trimIndent()

        findViewById<TextView>(R.id.textView).text = text;

        Log.d("Message", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Message", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Message", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Message", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Message", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Message", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Message", "onDestroy")
    }
}