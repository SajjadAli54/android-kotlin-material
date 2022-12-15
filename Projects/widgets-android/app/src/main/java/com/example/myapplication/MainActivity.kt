package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        radioLayout()
//        spinnerClick()
    }

    fun radioClick(view: View){
        val id = when(view.id){
            R.id.allama -> R.drawable.allama
            R.id.quaid -> R.drawable.quaid
            R.id.edhi -> R.drawable.edhi
            else -> R.drawable.sir
        }
        findViewById<ImageView>(R.id.image).setImageResource(id)
    }

    fun switchClick(view: View){
        val switch = findViewById<Switch>(R.id.switch1)
        findViewById<ImageView>(R.id.imageView).visibility =
            if(switch.isChecked) View.VISIBLE
            else View.INVISIBLE
    }

    private fun radioLayout(){
        setContentView(R.layout.radiobuttons)
    }

    private fun spinnerClick(){
        setContentView(R.layout.spinner_demo)
        val array = arrayOf("allama", "edhi", "quaid", "sajjad", "sir")

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)

        spinner.onItemSelectedListener = object: OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?, index: Int, size: Long) {
                val id = resources.getIdentifier(
                    array[index], "drawable", packageName
                )
                findViewById<ImageView>(R.id.imageSpinner).setImageResource(id)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}