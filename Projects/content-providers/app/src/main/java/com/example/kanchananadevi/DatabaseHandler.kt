package com.example.kanchananadevi

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context?) :
    SQLiteOpenHelper(context, "students", null, 1) {

    val TABLE = "login"

    override fun onCreate(database: SQLiteDatabase?) {
        database!!.execSQL("CREATE TABLE $TABLE(username VARCHAR(30), password VARCHAR(10))")

        writableDatabase.execSQL("""
            INSERT INTO $TABLE(username, password)
            VALUES
                ('Sajjad Ali', 'sajjadali'),
                ('Shayan ALi', 'shayanali'),
                ('Qasim Ali Shah', 'qasim ali')
        """.trimIndent())
    }

    fun getData(projectionList: Array<String>?, selection: String?, args: Array<String>?, sortOrder: String?): Cursor?{
        return readableDatabase.query(TABLE, projectionList, selection, args, null, null, sortOrder)
    }

    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}