# Android Networking
Read data from URL using Http URL Connect with Gson. Populate ListView with Custom Array Adapter

## Sample Output pics:


![image](https://user-images.githubusercontent.com/58862894/203845357-f208025b-e0ab-44f5-bc87-e735a1462c2c.png)

![image](https://user-images.githubusercontent.com/58862894/203845460-6771d158-b67a-456c-8f26-6fbdf8fbd15a.png)

![image](https://user-images.githubusercontent.com/58862894/203845578-4af480da-6122-4873-9166-2297bff03f64.png)

## Code

We will reading the posts data from open API 

https://jsonplaceholder.typicode.com/posts

We will be downloading the image from robohash api

https://robohash.org/

## Add Gson to Android project

Gson is a Java/Kotlin library for converting JSON string to an equivalent Java/Kotlin object.
Open build.gradle file and add Gson library.

```gradle
dependencies {
  implementation 'com.google.code.gson:gson:2.8.5'
}
```

## Add Intenet Request in Manifest file

```xml
 <uses-permission android:name="android.permission.INTERNET"/>
```

# Code is given below

## Data Class Post

```kotlin
package com.example.networkingdemo

data class Post(var userId: Int, var id: Int, var title: String,  var body: String)
```

## Main Activity

```kotlin
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
```
## My Adapter

```kotlin
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
            con.doInput = true
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
```

## Post View xml layout code

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center_horizontal"
    >

    <TextView
        android:id="@+id/postTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="20dp"
        android:foregroundTint="#303F9F"
        android:text="TextView"
        android:textColor="#303F9F"
        android:textSize="24sp" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:scaleType="fitXY"
        tools:srcCompat="@tools:sample/backgrounds/scenic" />

    <TextView
        android:id="@+id/postBody"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:foregroundTint="#E64A19"
        android:text="TextView"
        android:textColor="#C2185B"
        android:textSize="20sp" />
</LinearLayout>
```

## Main Activity Layout code

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:gravity="center"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ListView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/listView"
        />
</LinearLayout>
```

## Manifest file

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.example.networkingdemo">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@drawable/bg"
        android:label="@string/app_name"
        android:roundIcon="@drawable/bg"
        android:supportsRtl="true"
        android:theme="@style/Theme.NetworkingDemo"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

    <uses-permission android:name="android.permission.INTERNET"/>
</manifest>
```

That's it! Thanks.
