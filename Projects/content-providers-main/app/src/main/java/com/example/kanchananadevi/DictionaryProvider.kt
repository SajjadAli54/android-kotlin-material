package com.example.kanchananadevi

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import java.net.URI

class DictionaryProvider : ContentProvider() {

    val databaseHandler = DatabaseHandler(context)
    val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(Dictionary.AUTHORITIES, Dictionary.PATH, 12)
    }

    class Dictionary{
        companion object {
            val SCHEME = "content://"
            val AUTHORITIES = "Students.MyProvider"
            val PATH = "/login"

            val CONTENT_URI: Uri = Uri.parse(SCHEME + AUTHORITIES+ PATH)
        }
    }
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if(uriMatcher.match(uri) == 12)
            databaseHandler.writableDatabase.insert("login", null, values)
        return Dictionary.CONTENT_URI
    }

    override fun onCreate(): Boolean {
        TODO("Implement this to initialize your content provider on startup.")
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        if(uriMatcher.match(uri) == 12){
            return databaseHandler.getData(projection, selection, selectionArgs, sortOrder)
        }
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}