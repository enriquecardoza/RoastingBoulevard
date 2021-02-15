package com.careradish.roastingboulevard.activities


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.AddressEditAdapter
import com.careradish.roastingboulevard.adapters.AddressSelectAdapter
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_addresses_list.*

class AddressListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var buttCreateAddress: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addresses_list)

        recyclerView = recyclerViewAddressList

        buttCreateAddress = buttonGoCreateAddress

        buttCreateAddress.setOnClickListener {
            val intent = Intent(this, CreateAddressActivity::class.java)
            startActivityForResult(intent, Constants.codeRequestCreateAddress)
        }

        val bundle = intent.extras
        if (bundle != null) {
            canEditAddress = bundle.getBoolean(Constants.isAddressListEditOrSelect, false)
        }

        toolbarAddressList.setNavigationOnClickListener { finish() }

        if (canEditAddress)
            toolbarAddressList?.title = TranslationStrings.get(R.string.create_edit_address)
        else {
            toolbarAddressList?.title = TranslationStrings.get(R.string.select_address)
        }

        if (canEditAddress) {
            addressEditAdapter = AddressEditAdapter()
            recyclerView.adapter = addressEditAdapter
        } else {
            addressSelectAdapter = AddressSelectAdapter()
            recyclerView.adapter = addressSelectAdapter
            addressSelectAdapter.onSelectedAddress = {
                if (it != null) {
                    App.actualDelivery?.address = it
                }
                val inte = Intent(this, SelectPaymentMethodAndFinishActivity::class.java)
                startActivity(inte)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {

            if (canEditAddress) {
                addressEditAdapter.notifyItemInserted(App.user?.addresses!!.count() - 1)
            } else {
                addressSelectAdapter.notifyItemInserted(App.user?.addresses!!.count() - 1)
            }
        }
    }

    companion object {
        var canEditAddress: Boolean = false
        lateinit var addressEditAdapter: AddressEditAdapter
        lateinit var addressSelectAdapter: AddressSelectAdapter
/*        fun dataCreated(address: Address) {
            val pos = App.user.addresses!!.size
            App.user.addresses!![pos] = address
            addressEditAdapter.notifyItemInserted(pos)
        }*/

        fun adapterUpdated(address: Address) {
            val keys: MutableCollection<String>? = App.user?.addresses?.keys
            val pos = keys?.indexOf(address.label)
            App.user!!.addresses!![address.label] = address
            addressEditAdapter.notifyItemChanged(pos!!)
        }

        fun adapterErased(address: Address) {
            val keys: MutableCollection<String>? = App.user?.addresses?.keys
            val pos = keys?.indexOf(address.label)
            App.user!!.addresses?.remove(address.label)
            addressEditAdapter.notifyItemRemoved(pos!!)
        }
    }
}