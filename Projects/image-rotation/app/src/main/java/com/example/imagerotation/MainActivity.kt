package com.example.imagerotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        Thread{
            while (true){
                if(flag){
                    imageView.post{
                        imageView.rotation = (imageView.rotation + 10)%360
                    }
                }
                Thread.sleep(100)
            }
        }.start()
    }

    fun handleToggle(view: View){
        flag = !flag
    }
}