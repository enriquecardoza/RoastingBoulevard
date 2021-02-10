package com.careradish.roastingboulevard.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.DeliveryInfoAdapter
import kotlinx.android.synthetic.main.activity_deliveries_list.*

class DeliveriesList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deliveries_list)


        val adapter = DeliveryInfoAdapter()
        recyclerViewDeliveriesList.adapter = adapter
        recyclerViewDeliveriesList.layoutManager = LinearLayoutManager(this)
        recyclerViewDeliveriesList.setHasFixedSize(true)
    }
}