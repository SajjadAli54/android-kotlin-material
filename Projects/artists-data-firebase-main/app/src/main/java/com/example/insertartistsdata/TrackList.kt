package com.example.insertartistsdata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TrackList(context: Context, objects: MutableList<Track>) :
    ArrayAdapter<Track>(context, R.layout.layout_artist_list, objects) {

        val tracks: ArrayList<Track> = objects as ArrayList<Track>

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null)
            view = LayoutInflater.from(context)
                .inflate(R.layout.layout_artist_list, parent, false)

        view!!.findViewById<TextView>(R.id.textviewName).text = tracks[position].trackName
        view.findViewById<TextView>(R.id.textviewGenre).text = tracks[position].trackRating.toString()
        return view
    }
}