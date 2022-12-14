package com.example.memorygame

import android.os.CountDownTimer
import android.widget.TextView

class Timer(private val textView: TextView) :
    CountDownTimer(30000, 1000) {

    private var seconds = 0
    private var minutes = 0
    private var hours = 0

    override fun onTick(p0: Long) {
        textView.text = getTime();
    }

    override fun onFinish() {
        textView.text = getTime()
    }

    fun startTimer(){
        hours = 0
        minutes = 0
        seconds = 0

        this.start()

    }

    private fun getTime(): String{
        seconds++
        if(seconds == 60){
            minutes++
            seconds = 0
        }
        if(minutes == 60){
            hours++
            minutes = 0
        }

        return "${format(hours)}:${format(minutes)}:${format(seconds)}"
    }

    fun format(time: Int): String{
        if(time < 10)
            return "0${time}"
        else
            return "$time"
    }


}