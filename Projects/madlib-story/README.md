# MadLib Application

## Main Activity Code

```kotlin
package com.example.madlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartClick(view: View){
        val intent: Intent = Intent(this, InputActivity::class.java)
        startActivity(intent)
    }
}
```

## Main Activity XML

```xml
<?xml version="1.0" encoding="utf-8" ?>
<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:orientation="vertical"
  android:gravity="center"
  tools:context=".MainActivity"
>

    <ImageView
    android:id="@+id/imageView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:scaleType="fitXY"
    android:src="@drawable/img"
  />

    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="20dp"
    android:text="@string/hello_blank_fragment"
  />

    <Button
    android:id="@+id/start"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:onClick="onStartClick"
    android:textSize="32dp"
    android:layout_gravity="end"
    android:text="GET STARTED"
  />

</LinearLayout>
```

## InputActivity code

```kotlin
package com.example.madlibs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class InputActivity : AppCompatActivity() {
    lateinit var wordsTextView: TextView
    lateinit var inputEditText: EditText
    lateinit var hintTextView: TextView

    var placeholder: Array<String> = arrayOf()
    var file = ""
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        wordsTextView = findViewById(R.id.wordsLeft)
        inputEditText = findViewById(R.id.inputEditText)
        hintTextView = findViewById(R.id.typeWord)

        var random = Random().nextInt(5)
        val fileID = resources.getIdentifier(
            "madlib$random", "raw", packageName
        )
        var sc = Scanner(resources.openRawResource(fileID))
        while (sc.hasNext()){
            var word = sc.next()
            if(word.startsWith("<") && word.endsWith(">"))
                placeholder += word.subSequence(1, word.length - 1).toString()
        }
        sc.close()

        sc = Scanner(resources.openRawResource(fileID))
        while (sc.hasNextLine())
            file += "${sc.nextLine()}\n"
        show()
    }

    fun onOkClick(view: View){
        var word = inputEditText.text.toString()
        file = file.replace("<${inputEditText.hint}>", word)
        Toast.makeText(this, "Great! Keep doing.", Toast.LENGTH_LONG).show()
        if(count < placeholder.size)
            show()
        else{
            val intent = Intent(this, Story::class.java)
            intent.putExtra("story", file)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            )
            startActivity(intent)
            finish()
        }
    }

    fun show(){
        wordsTextView.text = "${placeholder.size - count} word(s) left"
        inputEditText.hint = placeholder.get(count)
        hintTextView.text = "Please type a/an ${placeholder.get(count).lowercase()}"

        inputEditText.text.clear()

        count++
    }
}
```

## Input Activity XML

```xml
<?xml version="1.0" encoding="utf-8" ?>
<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:padding="5dp"
  android:gravity="center_horizontal"
  android:orientation="vertical"
  android:layout_height="match_parent"
  tools:context=".InputActivity"
>

    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Fill in the words to complete the story!"
    android:textSize="32sp"
  />

    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/wordsLeft"
    android:layout_gravity="center_horizontal"
  />

    <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
  >

        <EditText
      android:id="@+id/inputEditText"
      android:layout_width="0dp"
      android:layout_height="wrap_content"
      android:layout_weight="3"
      android:hint="Noun"
    />
        <Button
      android:layout_width="0dp"
      android:layout_height="wrap_content"
      android:layout_weight="1"
      android:onClick="onOkClick"
      android:text="OK"
    />
    </LinearLayout>

    <TextView
    android:id="@+id/typeWord"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:text="Please type a/an noun"
  />
</LinearLayout>
```

## Story activity

```kotlin
package com.example.madlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Story : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        val textView = findViewById<TextView>(R.id.story)
        textView.text = intent.extras?.get("story").toString()
    }

    fun makeAnotherStory(view: View){
        val intent = Intent(this, InputActivity::class.java)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
        )
        startActivity(intent)
        finish()
    }
}
```

## Story Activity XML

```xml
<?xml version="1.0" encoding="utf-8" ?>
<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:fadeScrollbars="false"
  android:isScrollContainer="true"
  android:orientation="vertical"
  app:layout_scrollFlags="scroll"
  tools:context=".Story"
>

    <ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
  >
        <LinearLayout
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:orientation="vertical"
    >
            <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="Your MadLib Story!"
        android:textSize="32sp"
      />

            <TextView
        android:id="@+id/story"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="20dp"
        android:text="Story"
      />

            <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="40dp"
        android:onClick="makeAnotherStory"
        android:text="Make another story"
        android:textAppearance="@style/TextAppearance.AppCompat.Large"
      />
        </LinearLayout>
    </ScrollView>

</LinearLayout>
```
