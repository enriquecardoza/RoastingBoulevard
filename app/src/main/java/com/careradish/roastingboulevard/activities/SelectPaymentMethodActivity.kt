package com.careradish.roastingboulevard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.PaymentMethod
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_select_payment_method.*

class SelectPaymentMethodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_payment_method)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PaymentMethod.getArrMethods())
        spinnerPaymentMethod.adapter = arrayAdapter
    }
}