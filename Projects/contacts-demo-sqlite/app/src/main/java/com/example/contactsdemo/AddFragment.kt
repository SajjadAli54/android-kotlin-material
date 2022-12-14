package com.example.contactsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class AddFragment() : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val btn = view.findViewById<Button>(R.id.btnSave)
        btn.setOnClickListener(this::onSaveCLick)
        return view
    }

    private fun onSaveCLick(btnView: View){
        val name = view?.findViewById<TextView>(R.id.username)?.text.toString()
        val phone = view?.findViewById<TextView>(R.id.phone)?.text.toString()
        val street = view?.findViewById<TextView>(R.id.street)?.text.toString()
        val email = view?.findViewById<TextView>(R.id.email)?.text.toString()
        val city = view?.findViewById<TextView>(R.id.city)?.text.toString()

        val contact = Contacts(name, phone, street, email, city)
        val act = activity as MainActivity
        act.add(contact)
    }
}