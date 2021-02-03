package com.careradish.roastingboulevard.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.AddressEditAdapter
import com.careradish.roastingboulevard.classes.Address
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
            val intent = Intent(this, CreateAddressActivity::class.java)
            startActivity(intent)

        }

        loadAddresses({
            addressAdapter = AddressEditAdapter(App.user.addresses!!)
            recyclerView.adapter = addressAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
        })
    }

    companion object{
        lateinit var addressAdapter:AddressEditAdapter
        fun dataCreated(address:Address){
            val pos=App.user.addresses!!.size - 1
            App.user.addresses!![pos] = address
            addressAdapter.notifyItemInserted(pos)
        }
        fun adapterUpdated(address:Address){
            val pos=App.user.addresses!!.indexOf(address)
            App.user.addresses!![pos] = address
            addressAdapter.notifyItemChanged(pos)
        }
        fun adapterErased(address:Address){
            val pos=App.user.addresses!!.indexOf(address)
            App.user.addresses?.removeAt(pos)
            addressAdapter.notifyItemRemoved(pos)
        }
    }
}