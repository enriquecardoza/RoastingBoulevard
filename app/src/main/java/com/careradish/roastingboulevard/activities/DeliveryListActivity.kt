package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodDeliveryAdapter
import com.careradish.roastingboulevard.tools.App
import kotlinx.android.synthetic.main.activity_delivery_list.*


class DeliveryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_list)
        val adapter = FoodDeliveryAdapter()
        recyclerViewDeliveryList.adapter = adapter

        toolbarDeliveryList.subtitle = App.actualDelivery?.address?.simpleString()
        toolbarDeliveryList.setOnMenuItemClickListener {

            when (it.itemId) {
                0 -> {
                }
                1 -> {
                }
                else -> {
                }
            }
            true
        }
    }
}