package com.example.fragmentcommunication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView


class DataFragment : Fragment() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_data, container, false)
        textView = view.findViewById(R.id.textView)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val thread: Thread = Thread(Runnable {
            for(i in 1..10){
                progressBar.progress = i
                activity?.runOnUiThread(Runnable {
                    textView.text = "Value $i"
                })
                Thread.sleep(500)

            }
        })

        thread.start()
        return view
    }

    fun changeData(str: String){
        textView.text = str
    }
}