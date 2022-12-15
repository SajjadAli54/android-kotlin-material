package com.example.speechpractise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var tts: TextToSpeech
    private lateinit var speakToTextLauncher: ActivityResultLauncher<Intent>
    var flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this) { flag = true }

        speakToTextLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            run {
                val data: Intent? = result.data
                val list = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                speakText(list?.get(0).toString())
            }
        }
    }

    fun speak(view: View){
        var text = findViewById<EditText>(R.id.text).text.toString()
        speakText(text)
    }

    private fun speakText(text: String){
        if(flag){
            tts.speak(text, TextToSpeech.QUEUE_ADD, null)
        }
    }

    fun listen(view: View){
        var intent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ha bhai kya bolna hai tere ko?")
//        startActivityForResult(intent, 1234)
        speakToTextLauncher.launch(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1234) {
            val list = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val text = list?.get(0).toString()
            speakText(text)
        }
    }
}