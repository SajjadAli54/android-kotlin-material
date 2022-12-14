package com.example.higherlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var randomNumber = Random().nextInt(1000)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun validate(view: View){
        val label = findViewById<TextView>(R.id.lblMessage)
        val number = Integer.parseInt(findViewById<EditText>(R.id.txtValidateNumber).text.toString())
        when{
            number > randomNumber -> label.text = "Too Big"
            number < randomNumber -> label.text = "Too Low"
            else -> label.text = "Bingo! You guessed correct"
        }
    }

    fun playAgain(view: View){
        randomNumber = Random().nextInt(1000)
    }
}