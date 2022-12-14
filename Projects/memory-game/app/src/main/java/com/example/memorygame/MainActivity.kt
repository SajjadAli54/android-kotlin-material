package com.example.memorygame

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var previousButton: ImageButton
    private lateinit var arr: Array<Int>
    var isFirst: Boolean = true

    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arr = arrayOf<Int>(
            android.R.drawable.btn_star_big_on,
            android.R.drawable.ic_delete,
            android.R.drawable.ic_input_add,
            android.R.drawable.ic_dialog_email,
            android.R.drawable.ic_dialog_dialer,
            android.R.drawable.ic_dialog_map,
            android.R.drawable.ic_lock_power_off,
            android.R.drawable.ic_menu_edit,
            android.R.drawable.presence_audio_online,
            android.R.drawable.ic_menu_gallery
        )

        setUiElements()
    }

    fun playAgain(view: View){
        setUiElements()
    }

    fun imageButtonClick(view: View){
        var imageButton = findViewById<ImageButton>(view.id)
        var imageButtonTag = Integer.parseInt(imageButton.tag.toString())

        imageButton.setImageResource(imageButtonTag)

        when {
            isFirst -> {
                previousButton = imageButton;
                imageButton.isClickable = false
                isFirst = false
            }
            previousButton.tag.equals(imageButton.tag) -> {
                findViewById<TextView>(R.id.viewTime).text = "Great! You won"
                disable()
            }
            else -> {
                previousButton.isClickable = true;
                isFirst = true
                previousButton.setImageResource(android.R.drawable.ic_menu_help)
                imageButton.setImageResource(android.R.drawable.ic_menu_help)
            }
        }
    }

    private fun getId(resource: String): Int {
        return resources.getIdentifier(resource, "id", packageName)
    }

    private fun setUiElements(){
        var i = 0
        while(i < 16) {
            var id = getId("imageButton${i++}")
            var btn = findViewById<ImageButton>(id);
            var random = Random().nextInt(10)

            btn.tag = arr[random]
            btn.isClickable = true
            btn.setImageResource(android.R.drawable.ic_menu_help)
        }

        var textView = findViewById<TextView>(R.id.viewTime)
        textView.text = "00:00:00"
        isFirst = true

        timer = Timer(textView)
        timer.startTimer()
    }

    private fun disable(){
        var i = 0
        while (i < 16){
            var id = getId("imageButton${i++}")
            findViewById<ImageButton>(id).isClickable = false
        }
        timer.cancel()
    }

}