package com.example.contactsdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter(context: Context, resource: Int, objects: Array<out Contacts>) :
    ArrayAdapter<Contacts>(context, resource, objects) {

    val res = resource
    val data = objects
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null)
            view = LayoutInflater.from(context).inflate(res, parent, false)
        view!!.findViewById<TextView>(R.id.name).text = data[position].name
        view.findViewById<TextView>(R.id.phone).text = data[position].phone

        return view
    }


}