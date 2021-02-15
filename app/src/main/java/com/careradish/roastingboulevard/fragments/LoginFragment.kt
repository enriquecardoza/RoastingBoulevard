package com.careradish.roastingboulevard.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.activities.RegisterActivity
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.Tools
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val  tempInflater= inflater.inflate(R.layout.fragment_login, container, false)

        val  button=tempInflater.registerLoginButton
        button.setOnClickListener {

            val intent=Intent(App.context,RegisterActivity::class.java)
            startActivity(intent)
        }

        val buttonLogin=tempInflater.logOnButton
        buttonLogin.setOnClickListener{
            val email=editTextEmailLogin.text.toString()
            val password=editTextPasswordLogin.text.toString()

           FirebaseConnection.LoginUser(email, password, this.activity!!, {
               FirebaseConnection.onReadedUser = {
                   MainActivity.ForceUpdatePagerAdapter(4)
                   FirebaseConnection.onReadedUser = {  }
               }
           },{
MainActivity.showToast(  TranslationStrings.get(R.string.error_Login))
           })

        }
        tempInflater.fogotPasswordButton.setOnClickListener {

            if (Tools.isEmailValid(editTextEmailLogin.text.toString())){
                ShowAletDialog()
            }else{
                Toast.makeText(context,TranslationStrings.get(R.string.error_Email),Toast.LENGTH_LONG).show()
            }



        }
        return tempInflater
    }

    private fun ShowAletDialog() {
        val builder = AlertDialog.Builder(context)

        builder.setMessage(TranslationStrings.get(R.string.send_recover_email))

        builder.setPositiveButton(
            R.string.yes
        ) { dialog, _ ->
            FirebaseConnection.sendRecoverEmail(editTextEmailLogin.text.toString())
            MainActivity.showToast(TranslationStrings.get(R.string.recovery_email_sended))
            dialog.dismiss()
        }

        builder.setNegativeButton(
            R.string.No
        ) { dialog, _ ->
            dialog.dismiss()
        }

        val alert: AlertDialog? = builder.create()
        alert?.setOnShowListener(DialogInterface.OnShowListener {
            alert.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(Tools.getColor(R.color.paleRed))
            alert.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(Tools.getColor(R.color.paleOrange))
        })
        alert!!.show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {

            }
    }

}