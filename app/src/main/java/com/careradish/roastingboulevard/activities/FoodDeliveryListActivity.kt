package com.careradish.roastingboulevard.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodDeliveryAdapter
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import kotlinx.android.synthetic.main.activity_delivery_list.*


class FoodDeliveryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_list)
        val adapter = FoodDeliveryAdapter()
        recyclerViewDeliveryList.adapter = adapter
        recyclerViewDeliveryList.layoutManager = LinearLayoutManager(this)
        recyclerViewDeliveryList.setHasFixedSize(true)

        var totalAccount:Float=0f
        for (i in App.actualDelivery!!.foods){
            totalAccount+=i.price
        }
        textViewFullPrice.text=totalAccount.toString()+" €"

        buttonProcessOrder.setOnClickListener {
            val intent=Intent(this,AddressListActivity::class.java)
            intent.putExtra(Constants.isAddressListEditOrSelect,false)
            startActivity(intent)
        }
        buttonAddMoreFood.setOnClickListener {
            finish()
            MainActivity.changueSelectedTab(1)
        }
    }
}