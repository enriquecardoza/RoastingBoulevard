package com.careradish.roastingboulevard.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodDeliveryAdapter
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_foodlist_delivery_list.*


class FoodListDeliveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodlist_delivery_list)
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
            if (App.actualDelivery!!.foods.size<=0){
                Toast.makeText(this,TranslationStrings.get(R.string.error_have_food),Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent=Intent(this,AddressListActivity::class.java)
            intent.putExtra(Constants.isAddressListEditOrSelect,false)
            startActivity(intent)
        }
        buttonAddMoreFood.setOnClickListener {
            finish()
            MainActivity.changueSelectedTab(1)
        }
        toolbarDeliveryList.setNavigationOnClickListener { finish() }

    }
}