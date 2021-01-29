package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.tools.FirebaseConnection
import kotlinx.android.synthetic.main.activity_create_address.*

class CreateAddressActivity : AppCompatActivity() {

    lateinit var editTextLabel: EditText
    lateinit var editTextName: EditText
    lateinit var editTextNumber: EditText
    lateinit var editTextType: EditText
    lateinit var editTextPostalCode: EditText
    lateinit var editTextCity: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_address)

        editTextLabel = editTextNewLabel
        editTextName = editTextNewStreetName
        editTextNumber = editTextNewStreetNumber
        editTextType = editTextNewAddressType
        editTextPostalCode = editTextNewPostalCode
        editTextCity = editTextNewCity



        buttonCreateNewAddress.setOnClickListener {
            val address = Address(
                editTextLabel.text.toString(),
                editTextName.text.toString(),
                editTextNumber.text.toString().toInt(),
                editTextType.text.toString(),
                editTextPostalCode.text.toString().toInt(),
                editTextCity.text.toString()
            )
            DirectionListActivity.dataCreated(address)
            FirebaseConnection.writeAddress(address)
            finish()
        }


    }
}