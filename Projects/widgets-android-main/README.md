# Working with Android widgets such as Switch, RadioButtons, Buttons, Spinners, and Image View

## Code

Main Activity

```kotlin
package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        radioLayout()
//        spinnerClick()
    }

    fun radioClick(view: View){
        val id = when(view.id){
            R.id.allama -> R.drawable.allama
            R.id.quaid -> R.drawable.quaid
            R.id.edhi -> R.drawable.edhi
            else -> R.drawable.sir
        }
        findViewById<ImageView>(R.id.image).setImageResource(id)
    }

    fun switchClick(view: View){
        val switch = findViewById<Switch>(R.id.switch1)
        findViewById<ImageView>(R.id.imageView).visibility =
            if(switch.isChecked) View.VISIBLE
            else View.INVISIBLE
    }

    private fun radioLayout(){
        setContentView(R.layout.radiobuttons)
    }

    private fun spinnerClick(){
        setContentView(R.layout.spinner_demo)
        val array = arrayOf("allama", "edhi", "quaid", "sajjad", "sir")

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)

        spinner.onItemSelectedListener = object: OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?, index: Int, size: Long) {
                val id = resources.getIdentifier(
                    array[index], "drawable", packageName
                )
                findViewById<ImageView>(R.id.imageSpinner).setImageResource(id)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}
```

Layout for radio buttons

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center|top"
    >

    <RadioGroup
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        >
        <RadioButton
            android:id="@+id/allama"
            android:checked="true"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Allama Iqbal"
            android:onClick="radioClick"
            />

        <RadioButton
            android:id="@+id/edhi"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Edhi"
            android:onClick="radioClick"
            />

        <RadioButton
            android:id="@+id/quaid"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Quiad"
            android:onClick="radioClick"
            />

        <RadioButton
            android:id="@+id/sidiqi"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Nisar Sidiqi"
            android:onClick="radioClick"
            />

    </RadioGroup>

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/image"
        android:src="@drawable/allama"
        />
</LinearLayout>
```

layout for spinner demo

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:gravity="center|top"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Spinner
        android:id="@+id/spinner"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/imageSpinner"
        android:src="@drawable/allama"
        />
</LinearLayout>
```

main activity layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Switch
        android:id="@+id/switch1"
        android:checked="true"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="switchClick"
        android:text="Switch" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:visibility="visible"
        android:scaleType="fitXY"
        android:src="@drawable/sajjad" />

</LinearLayout>
```
