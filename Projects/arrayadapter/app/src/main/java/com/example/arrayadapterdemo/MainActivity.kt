package com.example.arrayadapterdemo

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            arrayListOf())

        var listView = findViewById<ListView>(R.id.listview)
        listView.adapter = adapter
    }

    fun add(view: View){
        var text = findViewById<EditText>(R.id.word).text.toString()
        adapter.add(text)
    }

    fun deleteFirst(view: View){
        var text = findViewById<EditText>(R.id.word).text.toString()
        adapter.remove(text)
    }
}