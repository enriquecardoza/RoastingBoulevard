package com.careradish.roastingboulevard.fragments

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
import com.careradish.roastingboulevard.classes.User
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

        return tempInflater
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {

            }
    }
}