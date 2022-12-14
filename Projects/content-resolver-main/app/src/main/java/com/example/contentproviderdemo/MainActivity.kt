package com.example.contentproviderdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var list: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permission = checkSelfPermission(Manifest.permission.READ_CONTACTS)
            if(permission == PackageManager.PERMISSION_GRANTED){
                readContacts()
            }
            else
                requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 1234)
        } else {
            readContacts()
        }
    }

    fun readContacts(){
        val contacts = findViewById<ListView>(R.id.contacts)
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
            null, null, null
        )

        list = ArrayList<String>()
        cursor!!.moveToFirst()
        do{
            list.add(cursor.getString(0))
        }while (cursor.moveToNext())
        contacts.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1234 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            readContacts()
    }

}