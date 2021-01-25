package com.careradish.roastingboulevard.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.RegisterActivity
import com.careradish.roastingboulevard.tools.App
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


        return tempInflater
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {

            }
    }
}