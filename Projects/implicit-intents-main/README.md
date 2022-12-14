# Implicit Intents in Android

## Source Code

### Main Activity Kotlin

```kotlin
package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun callClick(view: View){
        val number = Uri.parse("tel:5551234")
        val intent = Intent(Intent.ACTION_DIAL, number)
        startActivity(intent)
    }

    fun webClick(view: View){
        val webPage: Uri = Uri.parse("http://www.stanford.edu/")
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        startActivity(intent)
    }

    fun callMap(view: View){
        val location: Uri = Uri.parse("geo:37.422219,-122.08364?z=14")
        val intent = Intent(Intent.ACTION_VIEW, location)
        startActivity(intent)
    }
}
```

### UI layout xml for main_activity

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:gravity="center|top"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Call"
        android:onClick="callClick"
        />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open Site"
        android:onClick="webClick"
        />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Show Map"
        android:onClick="callMap"
        />

</LinearLayout>
```

That's it. Thanks!
