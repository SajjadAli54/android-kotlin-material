# arrayadapter-android-app
Adding and removing data via adapter and shoowing data in list view

## Code

```kotlin
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
```

## UI Code

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        >
        <EditText
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="2"
            android:hint="Enter a word"
            android:id="@+id/word"
            />

        <Button
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:onClick="add"
            android:text="Add"
            />

        <Button
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1.5"
            android:text="Delete"
            android:onClick="deleteFirst"
            />
    </LinearLayout>

    <ListView
        android:id="@+id/listview"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```
