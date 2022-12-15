# android-networking-with-volley

Add Internet permissions in Manifest file

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

Add depedency

```gradle
implementation 'com.android.volley:volley:1.2.1'
```

Source Code

```kotlin
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
```

UI Code

```xml
<?xml version="1.0" encoding="utf-8" ?>
<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context=".MainActivity"
>

    <ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
  >
        <TextView
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Hello World!"
      android:id="@+id/textview"
    />
    </ScrollView>

</LinearLayout>
```

I have just shown the `JSON` data returned from the url into `TextView`. However, If you want to parse the JSON data into your own data object (Obviously you would need to do that in your real life projects), then you can use `GSON` library. I already have used GSON library with `HttpURLConnection` at https://github.com/SajjadAli54/android-material/tree/main/Projects/android-networking. HttpURLConnection is much faster than volley and also core library of android so you do not need to add the dependecy for using it. If you either want to use GSON or HttpURLConnection, I would recommend you to have a look at that one also. Thanks!
