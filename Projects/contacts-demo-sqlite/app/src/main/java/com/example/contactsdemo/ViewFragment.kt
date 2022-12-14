package com.example.contactsdemo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment


class ViewFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view, container, false)

        val act = activity as MainActivity

        val listView = view?.findViewById<ListView>(R.id.listview)
        val data = act.getData()
        listView!!.adapter = MyAdapter(act, R.layout.cursor_row, data)
        return view
    }

}