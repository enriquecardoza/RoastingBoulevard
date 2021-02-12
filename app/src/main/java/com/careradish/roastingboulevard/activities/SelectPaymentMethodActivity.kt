package com.careradish.roastingboulevard.activities

import  android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.PaymentMethod
import com.careradish.roastingboulevard.fragments.DatePickerFragment
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.DebufAsynDelivering
import com.careradish.roastingboulevard.tools.FirebaseConnection
import kotlinx.android.synthetic.main.activity_select_payment_method.*


class SelectPaymentMethodActivity : AppCompatActivity() {


    var method = PaymentMethod.Method.Cash
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_payment_method)

        PrepareSpinnerSelectType()

        editTextExpirationDate.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                showDatePickerDialog()
        }
        editTextExpirationDate.setOnClickListener {
                showDatePickerDialog()
        }
        textViewAmountPayment.text = App.actualDelivery?.totalPrice.toString()+" €"

        buttonFinishPayment.setOnClickListener {
            FinishPayment()
        }
    }

    private fun FinishPayment() {
        var payment = PaymentMethod()
        if (method != PaymentMethod.Method.Cash) {
            payment.creditCardNumber = editTextCardNumber.text.toString().toInt()
            payment.expirationDate = editTextExpirationDate.text.toString()
            payment.cvv = editTextCVV.text.toString().toInt()
        }
        App.user?.deliveries?.put(App.actualDelivery!!.id!!,App.actualDelivery!!)
        FirebaseConnection.writeDelivery(App.actualDelivery!!)
        App.delivering=true
        App.deliveringDelivery=App.actualDelivery
        App.actualDelivery = null
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        MainActivity.hideOrderButton()
        val mitarea = DebufAsynDelivering()
        mitarea.execute()
    }

    private fun PrepareSpinnerSelectType() {

        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            PaymentMethod.getArrMethodsString()
        )
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerPaymentMethod.adapter = arrayAdapter
        spinnerPaymentMethod.setSelection(0)
        spinnerPaymentMethod.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    layoutCreditCardData.visibility = View.GONE
                } else {
                    layoutCreditCardData.visibility = View.VISIBLE
                }
                spinnerPaymentMethod.setSelection(position)
                method = PaymentMethod.getArrMethods()[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
        newFragment.dateSet = { dateResult ->
            editTextExpirationDate.setText(dateResult)
        }
    }
}