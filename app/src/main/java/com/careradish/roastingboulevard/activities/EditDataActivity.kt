package com.careradish.roastingboulevard.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_edit_data.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class EditDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data)

        info_editUser.text = ""
        toolbarRegisterEditUser.setNavigationOnClickListener {
            finish()
            MainActivity.changueSelectedTab(2)
        }
        buttonEdituser.setOnClickListener {

            if (!allFieldFilled()) {
                info_editUser.text = TranslationStrings.get(R.string.errorFieldsFilled)

            } else if (!isEmailValid(editTextEmail_editUser.text.toString())) {

                info_editUser.text = TranslationStrings.get(R.string.errorEmail)
            } else if (!passwordSizeCorrect()) {
                info_editUser.text = TranslationStrings.get(R.string.errorLenghtPassword)
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

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun passwordSizeCorrect(): Boolean {
        val comp = editTextPassword.text.toString().length >= 6
        return comp
    }
}