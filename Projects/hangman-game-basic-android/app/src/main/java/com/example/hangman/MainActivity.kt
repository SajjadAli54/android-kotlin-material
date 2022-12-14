package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private val array = arrayOf("apple", "orange", "banana")
    private var currentWord: String = ""
    private var visibleWord: String = ""
    private var limit = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        new()
    }

    private fun new() {
        val random = Random().nextInt(array.size)
        currentWord = array[random]

        val textView = findViewById<TextView>(R.id.word)
        visibleWord = ""
        for (i in currentWord.indices)
            visibleWord += "?"
        textView.text = visibleWord

        limit = 5

//        Toast.makeText(this, currentWord, Toast.LENGTH_SHORT)
    }

    fun onGuessClick(view: View){
        val guess = findViewById<TextView>(R.id.input).text.toString().toCharArray()

        val chars = currentWord.toCharArray()
        var visible = visibleWord.toCharArray()

        var bool = false
        for (i in guess.indices)
            for(j in chars.indices){
                if(guess[i] == chars[j]) {
                    visible[j] =  guess[i]
                    bool = true
                }
            }

        if(bool) {
            visibleWord = ""

            for (i in visible)
                visibleWord += i

            findViewById<TextView>(R.id.word).text = visibleWord
        }
        else
            limit--

        val message = findViewById<TextView>(R.id.message)
        message.text = "You have $limit guesses left"

        if(!visibleWord.contains("?")){
            Toast.makeText(this, "You won the game", Toast.LENGTH_LONG)
            new()
        }
        if(limit == 0){
            Toast.makeText(this, "You lost the game", Toast.LENGTH_LONG)
            new()
        }
    }

    fun onNewClick(view: View){
        new()
    }
}