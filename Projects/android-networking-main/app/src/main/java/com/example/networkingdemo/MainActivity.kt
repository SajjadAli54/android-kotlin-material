package com.example.networkingdemo

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val urlString = "https://jsonplaceholder.typicode.com/posts"
        val self = this
        Thread{
            val data = getJsonData(urlString)
            val posts: Array<Post> = getPostsList(data).toTypedArray()
            val list = findViewById<ListView>(R.id.listView)

            list.post {
                list.adapter = MyAdapter(self, R.layout.post_view, posts)
            }
        }.start()
    }

    private fun getJsonData(urlString: String): String{
        var data = ""
          try {
            val url: URL = URL(urlString)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            var line = reader.readLine()

            while (line != null){
                data += line
                line = reader.readLine()
            }

        }catch (exception: Exception){
            Log.d("msg", exception.toString())
        }

        return data
    }

    private fun getPostsList(data: String): List<Post> {
        val gson = Gson()
        val listPostType = object : TypeToken<List<Post>>() {}.type
        return gson.fromJson(data, listPostType)
    }
}