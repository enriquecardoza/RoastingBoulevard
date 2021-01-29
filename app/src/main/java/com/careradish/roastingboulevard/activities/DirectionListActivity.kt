package com.careradish.roastingboulevard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.AddressAdapter
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection.Companion.loadAddresses
import kotlinx.android.synthetic.main.activity_addresses_list.*

class DirectionListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var buttCreateAddress: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addresses_list)

        recyclerView = recyclerViewAddressList

        buttCreateAddress = buttonGoCreateAddress

        buttCreateAddress.setOnClickListener {
            val intent = Intent(this, CreateDirectionActivity::class.java)
            startActivity(intent)

        }

        loadAddresses({
            val addressAdapter = AddressAdapter(App.user.addresses!!)
            recyclerView.adapter = addressAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
        })
    }

}