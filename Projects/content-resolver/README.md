# Content Resolver

It is used to read, write and update the data shared by other apps.
It requires five things
-	URI of data
-	Selection of Columns, also called Projections, as String Array
-	Selection Clause OR where clause as String Array
-	Selection Arguments
-	Sort Order: Ascending or Descending

Finally, it returns a Cursor object just like a database select query does.

## Show Contact names in ListView using Content Resolver

Give permissions in Manifest file

```xml
<uses-permission android:name="android.permission.READ_CONTACTS"></uses-permission>
```

Method to read contacts using contentResolver object
```kotlin
    fun readContacts(){
        val contacts = findViewById<ListView>(R.id.contacts)
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
            null, null, null
        )

        var list = ArrayList<String>()
        cursor!!.moveToFirst()
        do{
            list.add(cursor.getString(0))
        }while (cursor.moveToNext())
        contacts.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
    }
```   
It also requires two way permissions if we are targetting android devices >= M version

```kotlin
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
 ```
 After granting the permissions, the following method will be called
 
 ```kotlin
     override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1234 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            readContacts()
    }
```    
That's it! Enjoy!


