# Android Firebase to store Artists data

Sample Outputs

![image](https://user-images.githubusercontent.com/58862894/205147254-5c23dd81-f02a-4124-a003-f83a6202d6e4.png)

![image](https://user-images.githubusercontent.com/58862894/205147369-6fbda825-38e6-4798-9243-f3455e4ea32c.png)

![image](https://user-images.githubusercontent.com/58862894/205147396-b1fa43a9-0376-42fe-90a4-6914d82d0b50.png)

![image](https://user-images.githubusercontent.com/58862894/205147473-c8984bc7-b720-4ef8-99a9-dd936d27d3f6.png)

![image](https://user-images.githubusercontent.com/58862894/205147529-f6d8df5a-11a7-41e5-b6bd-18f34a596574.png)

![image](https://user-images.githubusercontent.com/58862894/205147558-1c0864d0-5354-4eeb-ba7a-1977863e2437.png)

![image](https://user-images.githubusercontent.com/58862894/205147631-4a0b6ec8-b848-4a42-8e52-9816a069e8d9.png)


# Kotlin Code

## Artist data class

```kotlin
package com.example.insertartistsdata

class Artist() {
    private lateinit var _artistId: String
    private lateinit var _artistName: String

    private lateinit var _artistGenre: String

    constructor(artistId: String, artistName: String, artistGenre: String) : this() {
        this.artistId = artistId
        this.artistName = artistName
        this.artistGenre = artistGenre
    }

    var artistId: String
        get() = _artistId
        set(value) {
            _artistId = value
        }

    var artistName: String
        get() = _artistName
        set(value) {
            _artistName = value
        }

    var artistGenre: String
        get() = _artistGenre
        set(value) {
            _artistGenre = value
        }
}
```

## Track data class

```kotlin
package com.example.insertartistsdata

class Track() {

    private var _id: String = ""
    var id: String
        get() = _id
        set(value) {
            _id = value
        }
    private var _trackName: String = ""
    var trackName: String
        get() = _trackName
        set(value) {
            _trackName = value
        }
    private var _rating: Int = 0
    var trackRating: Int
        get() = _rating
        set(value) {
            _rating = value
        }

    constructor(id: String, name: String, rating: Int): this(){
        this.id = id
        this.trackName = name
        this.trackRating = rating
    }
}
```

## Adapter for artists

```kotlin
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
```

## Adapter for Tracks

```kotlin
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
```

## ArtistsList Activity

```kotlin
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
```


## Main Activity 

```kotlin
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
```

# UI xml

## activity artists

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ArtistsList">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAlignment="center"
        android:id="@+id/textviewArtist"
        android:textAppearance="@style/Base.CardView"
        android:textStyle="bold"
        />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/editTextName"
        android:hint="Enter Track Name"
        />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        >

        <SeekBar
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/seekbarRating"
            android:layout_weight="1"
            android:max="5"
            />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="1"
            android:id="@+id/textviewRating"
            />
    </LinearLayout>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Add Track"
        android:onClick="handleAddTrack"
        />

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Artists"
        android:textAlignment="center"
        android:textAppearance="@style/Base.TextAppearance.AppCompat.Large"/>

    <ListView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/listviewTracks"
        />

</LinearLayout>
```

## activity main

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
        android:id="@+id/editTextName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:hint="Enter name"/>

    <Spinner
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/spinnerGenres"
        android:layout_margin="10dp"
        android:entries="@array/data"
        />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Add Artist"
        android:onClick="handleAddArtist"
        android:layout_margin="10dp"
        />

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Artists"
        android:textAlignment="center"
        android:textAppearance="@style/Base.TextAppearance.AppCompat.Large"/>

    <TextView
        android:id="@+id/textView1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Tap on an Artist to add or view tracks"
        android:textAlignment="center"/>

    <ListView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/listviewArtists"
        />
</LinearLayout>
```

## layout_artist_list

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textviewName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Atif Aslam"
        android:textAppearance="@style/Base.TextAppearance.AppCompat.Large"
        />

    <TextView
        android:id="@+id/textviewGenre"
        android:textAppearance="@style/Base.TextAppearance.AppCompat.Medium"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Rock"

        />

</LinearLayout>
```

## update_dialog

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="10dp">

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/editTextName"
        android:hint="Enter Name"
        />

    <Spinner
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/spinnerGenres"
        android:entries="@array/data"
        />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        >

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/buttonUpdateArtist"
            android:text="Update"
            android:layout_margin="10dp"
            android:layout_weight="1"
            />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/buttonDeleteArtist"
            android:text="Delete"
            android:layout_margin="10dp"
            android:layout_weight="1"
            />
    </LinearLayout>

</LinearLayout>
```

## values file

```xml
<resources>
    <string name="app_name">InsertArtistsData</string>
    <string-array name="data">
        <item>Rock</item>
        <item>Pop</item>
        <item>Jazz</item>
        <item>Sufi</item>
        <item>Folk</item>
    </string-array>
</resources>
```

That's it!
