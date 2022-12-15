package com.example.insertartistsdata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter(con: Context, objects: MutableList<Artist>) :
    ArrayAdapter<Artist>(con, R.layout.layout_artist_list, objects) {

    val artists: ArrayList<Artist> = objects as ArrayList<Artist>

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null)
            view = LayoutInflater.from(context)
                .inflate(R.layout.layout_artist_list, parent, false)

        view!!.findViewById<TextView>(R.id.textviewName).text = artists[position].artistName
        view.findViewById<TextView>(R.id.textviewGenre).text = artists[position].artistGenre
        return view
    }
}