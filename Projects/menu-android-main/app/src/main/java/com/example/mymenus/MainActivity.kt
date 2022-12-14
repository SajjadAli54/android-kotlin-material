package com.example.mymenus

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import java.io.PrintStream
import java.util.Scanner

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.song)
        val txtView = findViewById<TextView>(R.id.textView)

        val printStream: PrintStream = PrintStream(openFileOutput("out.txt", MODE_PRIVATE))
        printStream.println("Hello World!")
        printStream.println("How are you");
        printStream.close()

        val scanner: Scanner = Scanner(openFileInput("out.txt"))
        var text: String = ""
        while (scanner.hasNext())
            text += "${scanner.nextLine()}\n"
        txtView.text = text;
        scanner.close()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.music){
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                item.setIcon(R.drawable.music_of_foreground)
                Toast.makeText(this, "Paused song", Toast.LENGTH_SHORT).show()
            }
            else{
                mediaPlayer.start()
                item.setIcon(R.drawable.music_on_foreground)
                Toast.makeText(this, "Play Song song", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}