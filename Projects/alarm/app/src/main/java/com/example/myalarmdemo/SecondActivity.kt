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