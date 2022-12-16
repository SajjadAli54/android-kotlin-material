package com.example.lab04activity03

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.widget.MediaController
import android.widget.SearchView
import android.widget.VideoView
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.song)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.music -> {
                if(mediaPlayer.isPlaying){
                    mediaPlayer.pause()
                    item.setIcon(R.drawable.music_of_foreground)
                }
                else{
                    mediaPlayer.start()
                    item.setIcon(R.drawable.music_on_foreground)
                }
            }
            R.id.speak ->{
                val item: SearchView = findViewById(R.id.app_bar_search)
                val text = item.query.toString()


            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }
}