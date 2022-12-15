# Working with ListView Adapters and Text to Speech engine

## Code

Main Activity file

```kotlin
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
```

Layout code for main_activity

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    tools:context=".MainActivity">

    <ListView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:entries="@array/OS" />
</LinearLayout>
```

Layout code for row in List View

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:gravity="center_vertical"
    android:layout_height="120dp"
    >

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_weight="0"
        app:srcCompat="@drawable/allama" />

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="120dp"
        android:layout_weight="2"
        android:gravity="center_vertical"
        android:orientation="vertical">

        <TextView
            android:id="@+id/name"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="20dp"
            android:text="Text View"
            android:textSize="20dp" />

        <TextView
            android:id="@+id/dob"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Text View"
            android:textSize="20dp" />

    </LinearLayout>

    <ImageView
        android:id="@+id/imageView3"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_weight="0"
        app:srcCompat="@drawable/call_foreground" />

</LinearLayout>
```

string file data

```xml
<resources>
    <string name="app_name">ListView and Adapter</string>
    <string-array name="OS">
        <item>Windows</item>
        <item>Linux</item>
        <item>Mac</item>
        <item>Android</item>
        <item>Windows</item>
        <item>Linux</item>
        <item>Mac OS</item>
    </string-array>
</resources>
```

That's it.
