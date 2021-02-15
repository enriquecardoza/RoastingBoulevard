package com.careradish.roastingboulevard.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_create_address.*
import kotlinx.android.synthetic.main.activity_edit_address.*

class EditAddressActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)


        val address = intent.getSerializableExtra(Constants.addressToEdit) as Address
        toolbarEditAddress.subtitle=address.label
        editTextEditStreetName.setText(address.address)
        editTextEditStreetNumber.setText(address.number.toString())
        editTextEditAddressType.setText(address.zoneType)
        editTextEditPostalCode.setText(address.postalCode.toString())
        editTextEditCity.setText(address.city)

        buttonEditAddress.setOnClickListener {


            val name= editTextEditStreetName.text.toString()
            val number=editTextEditStreetNumber.text.toString()
            val postalCode=editTextEditPostalCode.text.toString()
            val city=editTextEditCity.text.toString()


            val newaddress=Address(
                address.label,
                name,
                number.toInt(),
                editTextEditAddressType.text.toString(),
                postalCode.toInt(),
                city,
            )


            if (name.isNullOrEmpty()||number.isNullOrEmpty()||postalCode.isNullOrEmpty()||city.isNullOrEmpty()){
                textViewErrorCreateAddres.text= TranslationStrings.get(R.string.error_Fields_Filled)
                return@setOnClickListener
            }

            AddressListActivity.adapterUpdated(address)
            FirebaseConnection.writeAddress(newaddress)
            finish()
        }
        toolbarEditAddress.setNavigationOnClickListener { finish() }

    }
}