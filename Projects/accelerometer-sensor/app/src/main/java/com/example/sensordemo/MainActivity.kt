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