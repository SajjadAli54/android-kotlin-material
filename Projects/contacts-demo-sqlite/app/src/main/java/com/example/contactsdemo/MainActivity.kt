package com.example.contactsdemo

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null)

        database.execSQL(
            """CREATE TABLE IF NOT EXISTS $DATABASE_TABLE_NAME(
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                $NAME TEXT, $PHONE TEXT, $STREET TEXT, $EMAIL TEXT, $CITY TEXT 
                ) """.trimMargin()
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addContact -> switchFragments(AddFragment())
            R.id.viewContacts -> switchFragments(ViewFragment())
        }
        return true
    }

    fun add(contact: Contacts){
        val cv = ContentValues()
        cv.put(NAME, contact.name)
        cv.put(PHONE, contact.phone)
        cv.put(STREET, contact.street)
        cv.put(EMAIL, contact.email)
        cv.put(CITY, contact.city)

        database.insert(DATABASE_TABLE_NAME, null, cv)
        Toast.makeText(this, "Record added successfully", Toast.LENGTH_LONG).show()
    }

    fun getData(): Array<Contacts>{
        val cursor = database.rawQuery("SELECT * FROM $DATABASE_TABLE_NAME", null)
        var data = arrayOf<Contacts>()
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            while (cursor.moveToNext()){
                val contact = Contacts(
                    cursor.getString(1).toString(),
                    cursor.getString(2).toString(),
                    cursor.getString(3).toString(),
                    cursor.getString(4).toString(),
                    cursor.getString(5).toString(),
                )
                data += contact
            }

            cursor.close()
        }
        return data;
    }

    private fun switchFragments(fragment: Fragment){
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.placeholder, fragment)
        transaction.commit()
    }

    val NAME = "NAME"
    val PHONE = "PHONE"
    val STREET = "STREET "
    val EMAIL = "EMAIL"
    val CITY = "CITY"

    val DATABASE_NAME = "contacts"
    val DATABASE_TABLE_NAME = "contacts"
}