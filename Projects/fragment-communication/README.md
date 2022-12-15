# Fragment Communication in Android

# Kotlin Source Code

## Data Fragment Code

```kotlin
package com.example.fragmentcommunication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView


class DataFragment : Fragment() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_data, container, false)
        textView = view.findViewById(R.id.textView)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val thread: Thread = Thread(Runnable {
            for(i in 1..10){
                progressBar.progress = i
                activity?.runOnUiThread(Runnable {
                    textView.text = "Value $i"
                })
                Thread.sleep(500)

            }
        })

        thread.start()
        return view
    }

    fun changeData(str: String){
        textView.text = str
    }
}
```

## ListFragment Code

```kotlin
package com.example.fragmentcommunication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class ListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val data = resources.getStringArray(R.array.OS)

        val listView = view.findViewById<ListView>(R.id.listview)
        listView.setOnItemClickListener{adapterView, view, i, l ->
            val act: MainActivity = activity as MainActivity
            act.changeData(data[i])
        }
        return view;
    }
}
```

## Main Activity Code

```kotlin
package com.example.fragmentcommunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    lateinit var listFragment: ListFragment
    lateinit var dataFragment: DataFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listFragment = ListFragment()
        dataFragment = DataFragment()

        switchFragment(R.id.list_item, listFragment)

        switchFragment(R.id.data_fragment, dataFragment)

    }

    fun switchFragment(id: Int, fragment: Fragment){
        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(id, fragment)
        transaction.commit()
    }

    fun changeData(str: String){
        dataFragment.changeData(str)
    }
}
```

# UI Layouts Code

## fragmen_data layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="10dp"
    tools:context=".DataFragment">

    <!-- TODO: Update blank fragment layout -->
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_blank_fragment" />

    <ProgressBar
        android:id="@+id/progressBar"
        style="?android:attr/progressBarStyleHorizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:max="10" />

</LinearLayout>
```

## fragment_list layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ListFragment">

    <ListView
        android:id="@+id/listview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:entries="@array/OS" />
</FrameLayout>
```

## main_activity layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:weightSum="5"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:id="@+id/list_item"
        android:orientation="vertical"
        android:layout_width="0dp"
        android:layout_height="match_parent"
        android:layout_weight="2"
        />

    <LinearLayout
        android:id="@+id/data_fragment"
        android:orientation="vertical"
        android:layout_width="0dp"
        android:layout_height="match_parent"
        android:layout_weight="3"
        />
</LinearLayout>
```

That's it. Thanks!
