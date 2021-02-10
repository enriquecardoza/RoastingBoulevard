package com.careradish.roastingboulevard.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.Tools
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_edit_data.*


class EditDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data)

        info_editUser.text = ""
            editTextName_editUser.setText(App.user!!.name)
            editTextSurname_editUser.setText(App.user!!.surname)
            editTextEmail_editUser.setText(App.user!!.email)
            editTextPhone_editUser.setText(App.user!!.phone.toString())

        toolbarRegisterEditUser.setNavigationOnClickListener {
            finish()
            MainActivity.changueSelectedTab(2)
        }
        buttonEdituser.setOnClickListener {

            if (!allFieldFilled()) {
                info_editUser.text = TranslationStrings.get(R.string.error_Fields_Filled)

            } else if (!Tools.isEmailValid(editTextEmail_editUser.text.toString())) {

                info_editUser.text = TranslationStrings.get(R.string.error_Email)
            } else {

                editUser()

            }
        }
    }

    private fun editUser() {

        val email= editTextEmail_editUser.text.toString()
        if (App.user?.email!=email)
            FirebaseConnection.updateEmailUser(email)

        App.user?.name = editTextName_editUser.text.toString()
        App.user?.surname = editTextSurname_editUser.text.toString()
        App.user?.email = email
        App.user?.phone = editTextPhone_editUser.text.toString().toInt()

        FirebaseConnection.writeUser(App.user!!)
        MainActivity.ForceUpdatePagerAdapter(2)
        finish()

    }

    fun allFieldFilled(): Boolean {
        var comp = true

        when {
            editTextName_editUser.text.isNullOrEmpty() -> comp = false
            editTextSurname_editUser.text.isNullOrEmpty() -> comp = false
            editTextEmail_editUser.text.isNullOrEmpty() -> comp = false
            editTextPhone_editUser.text.isNullOrEmpty() -> comp = false
        }
        return comp
    }

}