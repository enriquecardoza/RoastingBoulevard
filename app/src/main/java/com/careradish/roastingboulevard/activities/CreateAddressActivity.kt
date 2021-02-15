package com.careradish.roastingboulevard.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.TranslationStrings
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



            val name= editTextName.text.toString()
            val number=editTextNumber.text.toString()
            val postalCode=editTextPostalCode.text.toString()
            val city=editTextCity.text.toString()

            if (editTextLabel.text.toString().isNullOrEmpty()||name.isNullOrEmpty()||number.isNullOrEmpty()||postalCode.isNullOrEmpty()||city.isNullOrEmpty()){
                textViewErrorCreateAddres.text=TranslationStrings.get(R.string.error_Fields_Filled)
                return@setOnClickListener
            }
            val labelFirstUpperCase=  editTextLabel.text.toString().substring(0, 1).toUpperCase() +  editTextLabel.text.toString().substring(1);
            val address = Address(
                labelFirstUpperCase,
                name,
                number.toInt(),
                editTextType.text.toString(),
                postalCode.toInt(),
                city
            )


            if (App.user?.addresses?.containsKey(labelFirstUpperCase) == true){

                textViewErrorCreateAddres.text=TranslationStrings.get(R.string.error_address)
                return@setOnClickListener
            }

            FirebaseConnection.writeAddress(address, {
                App.user?.addresses?.put(address.label,address)
                val returnIntent = Intent(this,AddressListActivity::class.java)
                setResult(RESULT_OK, returnIntent)
                //AddressListActivity.dataCreated(address)
                finish()
            })

        }
        toolbarCreateAddress.setNavigationOnClickListener { finish() }
    }
}