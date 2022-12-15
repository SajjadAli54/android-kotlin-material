package com.example.firebaseintro

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textName = itemView.findViewById<TextView>(R.id.employee_name)
    val textPhone = itemView.findViewById<TextView>(R.id.employee_phone)

}