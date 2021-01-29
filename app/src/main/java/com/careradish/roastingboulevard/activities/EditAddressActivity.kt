package com.careradish.roastingboulevard.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.FirebaseConnection
import kotlinx.android.synthetic.main.activity_edit_address.*

class EditAddressActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)


        val address = intent.getSerializableExtra(Constants.addressToEdit) as Address

        editTextEditLabel.setText(address.label)
        editTextEditStreetName.setText(address.address)
        editTextEditStreetNumber.setText(address.number.toString())
        editTextEditAddressType.setText(address.zoneType)
        editTextEditPostalCode.setText(address.postalCode.toString())
        editTextEditCity.setText(address.city)

        buttonEditAddress.setOnClickListener {

            val newaddress=Address(
                editTextEditLabel.text.toString(),
                editTextEditStreetName.text.toString(),
                editTextEditStreetNumber.text.toString().toInt(),
                editTextEditAddressType.text.toString(),
                editTextEditPostalCode.text.toString().toInt(),
                editTextEditCity.text.toString(),
            )

            DirectionListActivity.adapterUpdated(address)
            FirebaseConnection.writeAddress(newaddress)
            finish()
        }
        returnFromEditAddress.setOnClickListener { finish() }

    }
}