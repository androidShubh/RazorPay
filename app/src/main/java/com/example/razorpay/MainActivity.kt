package com.example.razorpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(),PaymentResultListener {
    private lateinit var amount:TextInputEditText
    private lateinit var payChip:Chip
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amount = findViewById(R.id.amount)
        payChip = findViewById(R.id.payChip)

        payChip.setOnClickListener {
            val amountTxt = amount.text.toString()

            val amt = (amountTxt.toFloat() * 100).roundToInt()
            val checkout = Checkout()

            checkout.setImage(R.drawable.ic_launcher_foreground)
            val obj = JSONObject()
            try {
                obj.put("name", "Shubham kumar ")

                // put description
                obj.put("description", "Test payment")

                // to set theme color
                obj.put("theme.color", "")

                // put the currency
                obj.put("currency", "INR")

                // put amount
                obj.put("amount", amt)

                // put mobile number
                obj.put("prefill.contact", "7759870382")

                // put email
                obj.put("prefill.email", "akp88@gmail.com")

                // open razorpay to checkout activity
                checkout.open(this@MainActivity, obj)
            }catch (e:JSONException){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment done SuccessFully", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Error"+p1.toString(), Toast.LENGTH_SHORT).show()
    }
}