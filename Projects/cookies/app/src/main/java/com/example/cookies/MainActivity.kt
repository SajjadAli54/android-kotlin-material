package com.example.cookies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onBtnClick(view: View){
        findViewById<Button>(R.id.button).text = "Done"
        findViewById<TextView>(R.id.textView).text = "I'm full"
        findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.full)
    }
}