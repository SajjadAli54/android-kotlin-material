# Sensors
In Android, it is a 3D object. They are categorized into three categories:

## Motion Sensors
Returns when the device is in motion. They return all three values.

### Accelerometer
Most applications that run as moving devices use accelerometers.
It returns all values of x, y, and z.

## Positional Sensors
They only return two values.
### Gravity sensors.

##Environmental Sensors
They only return one value. Measures light intensity, temperature, humidity, etc.
Proximity Sensor
When gets closed to the ear or hand, make mobile sleep. It returns only one value.

Steps to work with Sensors
1.	Create a Sensor Manager
2.	Create an event listener
3.	Register the listener
4.	Do work based on the output

# Code

## Activity Main

```kotlin
package com.example.sensordemo

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {

    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = R.drawable.off
        findViewById<ImageView>(R.id.imageView).setImageResource(id)

        // Step 01 - Create the sensor manager
        val sm = getSystemService(SENSOR_SERVICE) as SensorManager
        // Step 03 - Register th listener
        val sensor: Sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        val values: FloatArray = sensorEvent!!.values
        val x = values[0]
        val y = values[1]
        val z = values[2]

        val imageView = findViewById<ImageView>(R.id.imageView)
        if(x < 0 && y < 0 && z < 1){
            id = if(id == R.drawable.on)
                R.drawable.off
            else
                R.drawable.on
            imageView.setImageResource(id)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

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
    android:gravity="top"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:scaleType="center"
        tools:srcCompat="@tools:sample/backgrounds/scenic" />
</LinearLayout>
```

That's it. Thanks!
