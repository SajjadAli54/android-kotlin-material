# Todo tasks app with SQLite database

## Code

### Main Activity kotlin

```kotlin
package com.example.todolistdemo

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class MainActivity : AppCompatActivity() {
    var array: Array<String> = arrayOf()
    lateinit var database : SQLiteDatabase
    lateinit var listView: ListView

    val TABLE_NAME = "tasks"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createOpen()

        listView = findViewById<ListView>(R.id.listview)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
    }

    fun addTask(view: View){
        var task = findViewById<EditText>(R.id.todoTask).text.toString()
        array += task
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)

        val cv = ContentValues()
        cv.put("task", task)
        database.insert(TABLE_NAME, null, cv)
    }

    private fun createOpen(){
        database = openOrCreateDatabase("tasksToDo", MODE_PRIVATE, null)
        database.execSQL(
            """
                CREATE TABLE IF NOT EXISTS $TABLE_NAME(
                    _id INTEGER PRIMARY KEY AUTOINCREMENT,
                    task TEXT NOT NULL UNIQUE
                )
            """.trimIndent()
        )
        val cursor = database.rawQuery("""
            SELECT task FROM $TABLE_NAME
        """.trimIndent(), null)
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            while (cursor.moveToNext())
                array += cursor.getString(0)
        }
    }
}
```

### Layout Main Activity xml file

```xml
<?xml version="1.0" encoding="utf-8" ?>
<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:orientation="vertical"
  android:weightSum="9"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context=".MainActivity"
>

    <ListView
    android:id="@+id/listview"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="8"
  >

    </ListView>

    <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    android:orientation="horizontal"
    android:weightSum="4"
  >

        <EditText
      android:id="@+id/todoTask"
      android:layout_width="0dp"
      android:layout_height="wrap_content"
      android:layout_weight="3"
      android:hint="Enter a task to do"
    />

        <Button
      android:layout_width="0dp"
      android:layout_height="wrap_content"
      android:layout_weight="1"
      android:text="Add Item"
      android:onClick="addTask"
    />
    </LinearLayout>

</LinearLayout>
```
