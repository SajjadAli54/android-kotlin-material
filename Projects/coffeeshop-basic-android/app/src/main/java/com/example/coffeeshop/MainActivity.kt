package com.example.coffeeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    public  val COFFEE_PRICE = 4.00;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onIncrement(view: View){
        val textView = findViewById<TextView>(R.id.txtNumber)
        textView.text = (Integer.parseInt(textView.text.toString()) + 1).toString()
    }

    fun onDecrement(view: View){
        val textView = findViewById<TextView>(R.id.txtNumber)
        var num = Integer.parseInt(textView.text.toString()) - 1
        if(num < 0)
            num = 0
        textView.text = num.toString()
    }

    fun onOrder(view: View){
        val cream = findViewById<CheckBox>(R.id.checkCream).isChecked
        val chocolate = findViewById<CheckBox>(R.id.checkChocolate).isChecked
        val number = Integer.parseInt(findViewById<TextView>(R.id.txtNumber).text.toString())

        val textSummary = findViewById<TextView>(R.id.txtSummary)
        var price = number * COFFEE_PRICE;
        if(cream)
            price += 0.5
        if(chocolate)
            price += 0.5
        if(number == 0)
            textSummary.text = "Please place an order quantity first"
        else{
            val summary = """
                Add whipped cream : ${ if(cream) "yes" else "no"}
                Add Chocolate : ${ if(chocolate) "yes" else "no"}
                Quantity : $number
                
                Price : $${price}
                THANK YOU!
            """.trimIndent()

            textSummary.text = summary
        }
    }
}