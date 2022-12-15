package com.example.insertartistsdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.*

class ArtistsList : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, ValueEventListener {
    val artistName = "net.simplifiedcoding.firebasedatabaseexample.artistname"
    val artistId = "net.simplifiedcoding.firebasedatabaseexample.artistid"
    val artistsData = "artists"
    val tracksData = "tracks"

    lateinit var editTextTrackName: EditText
    lateinit var seekBarRating: SeekBar
    lateinit var textViewRating: TextView
    lateinit var textViewArtist: TextView
    lateinit var listViewTracks: ListView

    lateinit var databaseTracks: DatabaseReference

    lateinit var tracks: ArrayList<Track>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)

        databaseTracks = FirebaseDatabase
            .getInstance()
            .getReference(tracksData)
            .child(intent.getStringExtra(artistId).toString())

        editTextTrackName = findViewById(R.id.editTextName)
        seekBarRating = findViewById(R.id.seekbarRating)
        textViewRating = findViewById(R.id.textviewRating)
        textViewArtist = findViewById(R.id.textviewArtist)
        listViewTracks = findViewById(R.id.listviewTracks)

        tracks = arrayListOf()

        textViewArtist.text = intent.getStringExtra(artistName)

        seekBarRating.setOnSeekBarChangeListener(this)
    }

    fun handleAddTrack(view: View){
        val name = editTextTrackName.text.toString()
        val rating = seekBarRating.progress

        if(name.isNotEmpty()){
            val id = databaseTracks.push().key.toString()
            val track = Track(id, name, rating)
            databaseTracks.child(id).setValue(track)

            Toast.makeText(this, "Track added successfully", Toast.LENGTH_LONG).show()
            editTextTrackName.text.clear()
        }
        else{
            Toast.makeText(this, "Insert data", Toast.LENGTH_LONG).show()
        }
    }

    override fun onProgressChanged(bar: SeekBar?, value: Int, p2: Boolean) {
        textViewRating.text = value.toString()
    }

    override fun onStart() {
        super.onStart()

        databaseTracks.addValueEventListener(this)
    }

    override fun onStartTrackingTouch(bar: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }


    override fun onDataChange(snapshot: DataSnapshot) {
        tracks.clear()
        for(dataSnapshot in snapshot.children){
            val track = dataSnapshot.getValue(Track::class.java) as Track
            tracks.add(track)
        }
        listViewTracks.adapter = TrackList(this, tracks)
    }

    override fun onCancelled(error: DatabaseError) {


    }
}