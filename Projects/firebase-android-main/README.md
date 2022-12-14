# Android firebase and RecyclerView

Add following permissions first in Manifest file

```xml
 <uses-permission android:name="android.permission.INTERNET"/>
 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```



Create data class

```kotlin
package com.example.firebaseintro

data class EmployeeInfo(var employeeName: String,
                        var employeePhone: String,
                        var employeeAdress: String)
```

Code for Main Activity

```kotlin
package com.example.firebaseintro

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class MainActivity : AppCompatActivity(), ValueEventListener {

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private lateinit var employeeInfo: EmployeeInfo
    lateinit var employees: ArrayList<EmployeeInfo?>

    lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employees = arrayListOf()
        employeeAdapter = EmployeeAdapter(employees)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = employeeAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val url = "https://fir-intro-fe049-default-rtdb.firebaseio.com/"
        firebaseDatabase = FirebaseDatabase.getInstance(url)
        databaseReference = firebaseDatabase.getReference("EmployeeInfo")
    }

    fun handleAdd(view: View){
        val name = findViewById<EditText>(R.id.textName).text.toString()
        val phone = findViewById<EditText>(R.id.textPhone).text.toString()
        val address = findViewById<EditText>(R.id.textAddress).text.toString()

        if(name.isEmpty() && phone.isEmpty() && address.isEmpty())
            Toast.makeText(this, "Please insert some data", Toast.LENGTH_SHORT).show()
        else {
            employeeInfo = EmployeeInfo(name, phone, address)
            databaseReference.addValueEventListener(this)
        }

    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        val ref = databaseReference.child(employeeInfo.employeePhone)
        ref.setValue(employeeInfo)
        Toast.makeText(this, "Record Added successfully", Toast.LENGTH_SHORT).show()

        employees.add(employeeInfo)
        employeeAdapter.notifyDataSetChanged()
    }

    override fun onCancelled(p0: DatabaseError) {
        Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show()

    }
}
```

Code for Adapter class

```kotlin
package com.example.firebaseintro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(val employees: ArrayList<EmployeeInfo?>): RecyclerView.Adapter<MyViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_employee, parent, false)

        return MyViewholder(view)
    }


    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val employeeInfo = employees[position]
        holder.textName.text = employeeInfo!!.employeeName
        holder.textPhone.text = employeeInfo.employeePhone
    }


    override fun getItemCount(): Int {
        return employees.size
    }
}
```

Code for View Holder class

```kotlin
package com.example.firebaseintro

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textName = itemView.findViewById<TextView>(R.id.employee_name)
    val textPhone = itemView.findViewById<TextView>(R.id.employee_phone)

}
```

Layout code for row in recycle view

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingTop="10dp"
    android:paddingBottom="10dp"
    android:orientation="horizontal">

    <TextView
        android:id="@+id/employee_name"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        />

    <TextView
        android:id="@+id/employee_phone"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        />
</LinearLayout>
```

Layout code for main activity

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:gravity="center"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:id="@+id/textName"
        android:hint="Employee Name"
        android:inputType="textPersonName"
        />

    <EditText
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:id="@+id/textPhone"
        android:hint="Phone Number"
        android:inputType="phone"
        />

    <EditText
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:id="@+id/textAddress"
        android:hint="Employee Address"
        android:inputType="textPostalAddress"
        />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Add"
        android:onClick="handleAdd"
        />

    <androidx.recyclerview.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/recyclerview"
        />

</LinearLayout>
```

For further details, follow

https://www.geeksforgeeks.org/how-to-retrieve-data-from-the-firebase-realtime-database-in-android/?ref=rp
