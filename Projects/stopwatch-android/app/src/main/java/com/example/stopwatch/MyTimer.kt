package com.example.stopwatch

class MyTimer {
    private var seconds: Int = 0
    private var minutes: Int = 0
    private var hours: Int = 0

    fun getTime():String{
        seconds++
        if(seconds == 60){
            seconds  = 0
            minutes++
        }
        if(minutes == 60){
            minutes = 0
            hours++
        }

        return "${format(hours)}:${format(minutes)}:${format(seconds)}"
    }

    fun format(time: Int): String {
        return when(time < 10){
            true -> "0$time"
            else -> "$time"
        }
    }

    fun reset(){
        seconds = 0
        minutes = 0
        hours = 0
    }
}