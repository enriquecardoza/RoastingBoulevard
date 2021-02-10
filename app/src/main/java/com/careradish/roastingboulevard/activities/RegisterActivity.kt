package com.careradish.roastingboulevard.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.User
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.Tools
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        info_register.text = ""
        toolbarRegister.setNavigationOnClickListener {
            finish()
            MainActivity.changueSelectedTab(2)
        }
        buttonRegister.setOnClickListener {

            if (!allFieldFilled()) {
                info_register.text = TranslationStrings.get(R.string.error_Fields_Filled)
            } else if (!Tools.isEmailValid(createUser.text.toString())) {
                info_register.text = TranslationStrings.get(R.string.error_Email)
            } else if (!passwordSizeCorrect()) {
                info_register.text = TranslationStrings.get(R.string.error_Lenght_Password)
            }else if (!passwordConfirmed()) {
                info_register.text = TranslationStrings.get(R.string.error_Password_Not_Match)
            } else {
                createUser()
            }
        }

    }

    private fun createUser() {
        val user = User(
            "0",
            editTextName.text.toString(),
            editTextSurname.text.toString(),
            createUser.text.toString(),
            editTextPhone.text.toString().toInt(),
        )
        info_register.text = ""


        FirebaseConnection.createUser(user, editTextPassword.text.toString(), this, {

            MainActivity.ForceUpdatePagerAdapter(2)
            finish()
        })
    }


    fun allFieldFilled(): Boolean {
        var comp = true

        when {
            editTextName.text.isNullOrEmpty() -> comp = false
            editTextSurname.text.isNullOrEmpty() -> comp = false
            createUser.text.isNullOrEmpty() -> comp = false
            editTextPassword.text.isNullOrEmpty() -> comp = false
            editTextPasswordRepeat.text.isNullOrEmpty() -> comp = false
            editTextPhone.text.isNullOrEmpty() -> comp = false
        }
        return comp
    }

    fun passwordConfirmed(): Boolean {
        val comp = editTextPassword.text.toString().equals(editTextPasswordRepeat.text.toString())
        return comp
    }

    fun passwordSizeCorrect(): Boolean {
        val comp = editTextPassword.text.toString().length>=6
        return comp
    }
}