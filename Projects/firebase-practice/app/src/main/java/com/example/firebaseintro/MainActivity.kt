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