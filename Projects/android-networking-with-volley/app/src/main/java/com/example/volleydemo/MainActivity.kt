package com.example.volleydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            "https://jsonplaceholder.typicode.com/users",
            Response.Listener<String> {
                findViewById<TextView>(R.id.textview).text = it
            },
            Response.ErrorListener {Log.d("error", it.toString())}
        )

        queue.add(request)
    }
}