package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun callClick(view: View){
        val number = Uri.parse("tel:5551234")
        val intent = Intent(Intent.ACTION_DIAL, number)
        startActivity(intent)
    }

    fun webClick(view: View){
        val webPage: Uri = Uri.parse("http://www.stanford.edu/")
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        startActivity(intent)
    }

    fun callMap(view: View){
        val location: Uri = Uri.parse("geo:37.422219,-122.08364?z=14")
        val intent = Intent(Intent.ACTION_VIEW, location)
        startActivity(intent)
    }
}