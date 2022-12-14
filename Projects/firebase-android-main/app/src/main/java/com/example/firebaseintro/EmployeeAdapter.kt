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