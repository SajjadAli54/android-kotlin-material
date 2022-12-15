# adapters-android

ListView with custome Array Adapter

```kotlin
package com.example.adapterdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    data class Personality(val dp: Int, val name: String, val dob: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val adapter = PersonalityAdapter(this, R.layout.row_layout, data)
        findViewById<ListView>(R.id.listview).adapter = adapter
    }

    class PersonalityAdapter(context: Context, resource: Int, objects: Array<out Personality>) :
        ArrayAdapter<Personality>(context, resource, objects) {

        val cont = context
        val res = resource
        val data= objects

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView!!
            if(view == null)
                view = LayoutInflater.from(cont).inflate(res, parent, false)

            val dp = view.findViewById<ImageView>(R.id.imageView)
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
