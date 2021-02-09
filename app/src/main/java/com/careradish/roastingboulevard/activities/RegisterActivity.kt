package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.User
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {



    private lateinit var buttonCreate: Button

    private lateinit var textInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        textInfo.text = ""
        toolbarRegister.setNavigationOnClickListener {
            finish()
            MainActivity.changueSelectedTab(2)
        }
        buttonCreate.setOnClickListener {

            if (!allFieldFilled()) {
                textInfo.text = TranslationStrings.get(R.string.errorFieldsFilled)

            } else if (!isEmailValid(createUser.text.toString())) {

                textInfo.text = TranslationStrings.get(R.string.errorEmail)
            } else if (!passwordSizeCorrect()) {
                textInfo.text = TranslationStrings.get(R.string.errorLenghtPassword)
            }else if (!passwordConfirmed()) {

                textInfo.text = TranslationStrings.get(R.string.errorPasswordNotMatch)
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
        textInfo.text = ""


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

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }
    fun passwordSizeCorrect(): Boolean {
        val comp = editTextPassword.text.toString().length>=6
        return comp
    }
}