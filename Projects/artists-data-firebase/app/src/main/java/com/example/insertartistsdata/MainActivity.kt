package com.example.insertartistsdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(), ValueEventListener {

    val artistName = "net.simplifiedcoding.firebasedatabaseexample.artistname"
    val artistId = "net.simplifiedcoding.firebasedatabaseexample.artistid"
    val artistsData = "artists"
    val tracksData = "tracks"

    lateinit var editText: EditText
    lateinit var spinnerGenre: Spinner
    lateinit var listViewArtists: ListView

    lateinit var artists: ArrayList<Artist>

    private lateinit var databaseReferenceArtists: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseReferenceArtists = FirebaseDatabase.getInstance().getReference(artistsData)

        editText = findViewById(R.id.editTextName)
        spinnerGenre = findViewById(R.id.spinnerGenres)
        listViewArtists = findViewById(R.id.listviewArtists)

        artists = arrayListOf()

        listViewArtists.onItemClickListener = AdapterView.OnItemClickListener(this::onItemClick)
        listViewArtists.onItemLongClickListener = AdapterView.OnItemLongClickListener(this::onItemLongClick)
    }

    private fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long){
        var artist: Artist = artists[i]
        var intent = Intent(applicationContext, ArtistsList::class.java)
        intent.putExtra(artistId, artist.artistId)
        intent.putExtra(artistName, artist.artistName)
        startActivity(intent)
    }

    private fun onItemLongClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long): Boolean{
        showUpdateDeleteDialog(artists[i].artistId, artists[i].artistName)
        return true
    }

    fun handleAddArtist(view: View){
        addArtist()
    }

    private fun addArtist(){
        val name = editText.text.toString()
        val genre = spinnerGenre.selectedItem.toString()
        if(name.isNotEmpty()){
            val id = databaseReferenceArtists.push().key.toString()
            val artist = Artist(id, name, genre)
            databaseReferenceArtists.child(id).setValue(artist)
            editText.text.clear()

            Toast.makeText(this, "Added record successfully", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Please insert name", Toast.LENGTH_LONG).show()
    }

    private fun showUpdateDeleteDialog(id: String, name: String){
        val dialogBuilder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.update_dialogue, null)
        dialogBuilder.setView(view)

        val editTextName = view.findViewById<EditText>(R.id.editTextName)
        val spinnerGenre = view.findViewById<Spinner>(R.id.spinnerGenres)
        val btnUpdate = view.findViewById<Button>(R.id.buttonUpdateArtist)
        val btnDelete = view.findViewById<Button>(R.id.buttonDeleteArtist)

        dialogBuilder.setTitle(name)
        val alertDialog: AlertDialog = dialogBuilder.create()
        alertDialog.show()

        btnUpdate.setOnClickListener{view ->
            val name = editTextName.text.toString().trim()
            val genre = spinnerGenre.selectedItem.toString()
            if(name.isNotEmpty()){
                updateArtist(id, name, genre)
                alertDialog.dismiss()
            }
        }

        btnDelete.setOnClickListener{view ->
            deleteArtist(id)
            alertDialog.dismiss()
        }
    }

    private fun updateArtist(id: String, name: String, genre: String): Boolean{
        val dr = FirebaseDatabase.getInstance().getReference(artistsData).child(id)
        val artist = Artist(id, name, genre)
        dr.setValue(artist)

        Toast.makeText(applicationContext, "Artist updated", Toast.LENGTH_LONG).show()
        return true
    }

    private fun deleteArtist(id: String): Boolean{
        val dr = FirebaseDatabase.getInstance().getReference(artistsData).child(id)
        dr.removeValue()

        val drTracks = FirebaseDatabase.getInstance().getReference(tracksData).child(id)
        drTracks.removeValue()

        Toast.makeText(applicationContext, "Artist Deleted", Toast.LENGTH_LONG).show()
        return true
    }

    override fun onStart() {
        super.onStart()
        databaseReferenceArtists.addValueEventListener(this)
    }


    override fun onDataChange(snapshot: DataSnapshot) {
        artists.clear()
        val children = snapshot.children
        for(s in children){
            val artist: Artist = s.getValue(Artist::class.java) as Artist
            artists.add(artist)
        }
        listViewArtists.adapter = MyAdapter(this, artists)
    }


    override fun onCancelled(error: DatabaseError) {

    }
}