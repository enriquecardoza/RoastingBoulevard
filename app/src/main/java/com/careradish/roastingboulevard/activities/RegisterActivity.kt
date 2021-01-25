package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {


    private val names = arrayOf(
        "MasterCard", "Paypal", "Efectivo",
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val adapter: ArrayAdapter<*> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, names
        )

        val spinner:Spinner=spinnerPaymentMethod
        spinner.adapter=adapter
    }
}