package com.careradish.roastingboulevard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.AddressSelectAdapter
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import kotlinx.android.synthetic.main.activity_select_address.*
import java.util.*

class SelectAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_address)

        val food= intent.getSerializableExtra(Constants.selectedFood) as Food

        val adapter=AddressSelectAdapter()
        recyclerSelectAddres.adapter=adapter
        adapter.onSelectedAddress={
            App.actualDelivery= Delivery(it!!)
            val inte=Intent(this,FoodSelected::class.java)
            inte.putExtra(Constants.selectedFood,food)
            startActivity(inte)
        }

    }
}