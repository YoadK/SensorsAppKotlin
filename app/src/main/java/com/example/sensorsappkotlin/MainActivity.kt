package com.example.sensorsappkotlin

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var sm: SensorManager? = null
    private var list: MutableList<Sensor>? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    override fun onStop() {
        super.onStop()

        if (list!!.size > 0) {
            sm!!.unregisterListener(sel)
        }
    }

    private fun getData() {
        sm = getSystemService(SENSOR_SERVICE) as SensorManager

        list = sm!!.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if (list!!.size > 0) {
            sm!!.registerListener(sel, list!![0], SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Toast.makeText(baseContext, "Error: No Accelerometer.", Toast.LENGTH_LONG).show()
        }
    }

    private var sel: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

        }

        override fun onSensorChanged(event: SensorEvent) {
            val values = event.values
            textView1.text = "x: ${values[0]} y: ${values[1]}z: ${values[2]}"
        }
    }

}
