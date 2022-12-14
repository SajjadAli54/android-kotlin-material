package com.example.fragmentcommunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    lateinit var listFragment: ListFragment
    lateinit var dataFragment: DataFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listFragment = ListFragment()
        dataFragment = DataFragment()

        switchFragment(R.id.list_item, listFragment)

        switchFragment(R.id.data_fragment, dataFragment)

    }

    fun switchFragment(id: Int, fragment: Fragment){
        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(id, fragment)
        transaction.commit()
    }

    fun changeData(str: String){
        dataFragment.changeData(str)
    }
}