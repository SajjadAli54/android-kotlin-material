package com.example.networkingdemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.net.HttpURLConnection
import java.net.URL

class MyAdapter(context: Context, resource: Int, objects: Array<out Post>) :
    ArrayAdapter<Post>(context, resource, objects) {

    val cont = context
    val res = resource
    val posts = objects
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null)
            view = LayoutInflater.from(cont).inflate(res, parent, false)

        val title = view!!.findViewById<TextView>(R.id.postTitle)
        title.text = posts[position].title

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        Thread{
            val image = downloadImage(posts[position].id)
            imageView.post {
                imageView.setImageBitmap(image)
            }
        }.start()

        val body = view.findViewById<TextView>(R.id.postBody)
        body.text = posts[position].body
        return view
    }

    private fun downloadImage(id: Int): Bitmap?{
        var bitmap: Bitmap? = null
        val uri = "https://robohash.org/$id.png"
        try{
            val url = URL(uri)
            val con = url.openConnection() as HttpURLConnection
            con.connect()

            val responseCode = con.responseCode
            if(responseCode == HttpURLConnection.HTTP_OK){
                val inputStream = con.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
            }
        }catch (exception: Exception){
            Log.d("msg", exception.toString())
        }
        return bitmap
    }

}