package com.example.listviewandadapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

    data class Personality(var dp: Int, var name: String, var dob: String)
    var isTTSReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tts = TextToSpeech(this) { isTTSReady = true }

        val data = arrayOf(
            Personality(R.drawable.edhi, "Edhi", "1/1/1950"),
            Personality(R.drawable.quaid, "Quaid", "25/12/1876"),
            Personality(R.drawable.allama, "Allama", "09/09/1877"),
            Personality(R.drawable.sir, "Sir Nisar", "1/1/1950"),
            Personality(R.drawable.edhi, "Edhi", "1/1/1950"),
            Personality(R.drawable.quaid, "Quaid", "25/12/1876"),
            Personality(R.drawable.allama, "Allama", "09/09/1877"),
            Personality(R.drawable.sir, "Sir Nisar", "1/1/1950"),
            Personality(R.drawable.edhi, "Edhi", "1/1/1950"),
            Personality(R.drawable.quaid, "Quaid", "25/12/1876"),
            Personality(R.drawable.allama, "Allama", "09/09/1877"),
            Personality(R.drawable.sir, "Sir Nisar", "1/1/1950"),
            Personality(R.drawable.edhi, "Edhi", "1/1/1950"),
            Personality(R.drawable.quaid, "Quaid", "25/12/1876"),
            Personality(R.drawable.allama, "Allama", "09/09/1877"),
            Personality(R.drawable.sir, "Sir Nisar", "1/1/1950"),
            Personality(R.drawable.edhi, "Edhi", "1/1/1950"),
            Personality(R.drawable.quaid, "Quaid", "25/12/1876"),
            Personality(R.drawable.allama, "Allama", "09/09/1877"),
            Personality(R.drawable.sir, "Sir Nisar", "1/1/1950"),
        )

        val list = findViewById<ListView>(R.id.list)

        val adapter = PersonalityAdapter(this,
            R.layout.row, data)

        list.adapter = adapter

        list.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->

                Toast.makeText(this, data[i].name, Toast.LENGTH_LONG).show()
                if(isTTSReady)
                    tts.speak(
                        view.findViewById<TextView>(R.id.name).text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        "1234"
                    )
        }
    }

    class PersonalityAdapter(context: Context, resource: Int, objects: Array<out Personality>) :
        ArrayAdapter<Personality>(context, resource, objects) {

        val res = resource
        val data = objects

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            if(view == null)
                view = LayoutInflater.from(context).inflate(res, parent, false)

            val dp = view!!.findViewById<ImageView>(R.id.imageView)
            dp.setImageResource(data[position].dp)

            val name = view.findViewById<TextView>(R.id.name)
            name.text = data[position].name

            val dob = view.findViewById<TextView>(R.id.dob)
            dob.text = data[position].dob

            return view
        }

    }
}