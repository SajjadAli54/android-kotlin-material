package com.example.madlibs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class InputActivity : AppCompatActivity() {
    lateinit var wordsTextView: TextView
    lateinit var inputEditText: EditText
    lateinit var hintTextView: TextView

    var placeholder: Array<String> = arrayOf()
    var file = ""
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        wordsTextView = findViewById(R.id.wordsLeft)
        inputEditText = findViewById(R.id.inputEditText)
        hintTextView = findViewById(R.id.typeWord)

        var random = Random().nextInt(5)
        val fileID = resources.getIdentifier(
            "madlib$random", "raw", packageName
        )
        var sc = Scanner(resources.openRawResource(fileID))
        while (sc.hasNext()){
            var word = sc.next()
            if(word.startsWith("<") && word.endsWith(">"))
                placeholder += word.subSequence(1, word.length - 1).toString()
        }
        sc.close()

        sc = Scanner(resources.openRawResource(fileID))
        while (sc.hasNextLine())
            file += "${sc.nextLine()}\n"
        show()
    }

    fun onOkClick(view: View){
        var word = inputEditText.text.toString()
        file = file.replace("<${inputEditText.hint}>", word)
        Toast.makeText(this, "Great! Keep doing.", Toast.LENGTH_LONG).show()
        if(count < placeholder.size)
            show()
        else{
            val intent = Intent(this, Story::class.java)
            intent.putExtra("story", file)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            )
            startActivity(intent)
            finish()
        }
    }

    fun show(){
        wordsTextView.text = "${placeholder.size - count} word(s) left"
        inputEditText.hint = placeholder.get(count)
        hintTextView.text = "Please type a/an ${placeholder.get(count).lowercase()}"

        inputEditText.text.clear()

        count++
    }
}