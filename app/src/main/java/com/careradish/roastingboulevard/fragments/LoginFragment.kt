package com.careradish.roastingboulevard.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.AddressListActivity
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.activities.RegisterActivity
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection
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
               FirebaseConnection.readUser(App.user!!.id, {
                   MainActivity.ForceUpdatePagerAdapter(4)
               } )

           })

        }
        fogotPasswordButton.setOnClickListener {


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
            alert!!.show()

        }
        return tempInflater
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {

            }
    }

}