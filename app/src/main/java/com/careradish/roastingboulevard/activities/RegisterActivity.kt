package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.User
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {


    private lateinit var textName:EditText
    private  lateinit var textSurname:EditText
    private  lateinit var textAddress:EditText
    private  lateinit var textCity:EditText
    private  lateinit var textPostalCode:EditText
    private  lateinit var textEmail:EditText
    private  lateinit var textPassword:EditText
    private  lateinit var textConfirmPassword:EditText
    private  lateinit var textPhone:EditText

    private lateinit var buttonCreate: Button
    private lateinit var buttonBack: ImageButton

    private lateinit var textInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        AsingVariables()
        textInfo.text = ""
        buttonBack.setOnClickListener { finish() }
        buttonCreate.setOnClickListener {

            if (!allFieldFilled()){
                textInfo.text = TranslationStrings.get(R.string.ErrorFieldsFilled)

            }else if(!isEmailValid(textEmail.text.toString())){

                textInfo.text = TranslationStrings.get(R.string.ErrorEmail)
            }
            else if (!passwordConfirmed()){

                textInfo.text = TranslationStrings.get(R.string.ErrorPasswordNotMatch)
            }else{
                val user=User(
                    0,
                    textName.text.toString(),
                    textSurname.text.toString(),
                    textAddress.text.toString(),
                    textCity.text.toString(),textPostalCode.text.toString().toInt(),
                    textEmail.text.toString(),
                    textPhone.text.toString().toInt(),
                    textPassword.text.toString())
                App.user=user
                finish()
                Toast.makeText(App.context, TranslationStrings.get(R.string.registered),Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun AsingVariables() {
        textName = editTextName
        textSurname = editTextSurname
        textAddress = editTextAddress
        textPostalCode = editTextPostalCode
        textEmail = editTextEmail
        textCity=editTextCity
        textPassword = editTextPassword
        textConfirmPassword = editTextPasswordRepeat
        textPhone = editTextPhone
        buttonCreate = buttonRegister
        buttonBack = closeRegister
        textInfo=info_register
    }

    fun allFieldFilled():Boolean{
        var comp=true

        when {
            textName.text.isNullOrEmpty() -> comp=false
            textSurname.text.isNullOrEmpty() -> comp=false
            textAddress.text.isNullOrEmpty() -> comp=false
            textCity.text.isNullOrEmpty() -> comp=false
            textPostalCode.text.isNullOrEmpty() -> comp=false
            textEmail.text.isNullOrEmpty() -> comp=false
            textPassword.text.isNullOrEmpty() -> comp=false
            textConfirmPassword.text.isNullOrEmpty() -> comp=false
            textPhone.text.isNullOrEmpty() -> comp=false
        }
        return  comp
    }

    fun passwordConfirmed():Boolean{
        val comp=textPassword.text.toString().equals(textConfirmPassword.text.toString())

        return comp
    }

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }
}