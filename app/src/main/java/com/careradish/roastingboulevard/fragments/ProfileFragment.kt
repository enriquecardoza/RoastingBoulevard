package com.careradish.roastingboulevard.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.AddressListActivity
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    lateinit var textWelcome: TextView
lateinit var buttAddress:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var tempInflete = inflater.inflate(R.layout.fragment_profile, container, false)
        textWelcome = tempInflete.textViewProfileWelcome
        buttAddress=tempInflete.buttonAddresses
        textWelcome.text=TranslationStrings.get(R.string.profile_welcome)+" "+App.user.name
        buttAddress.setOnClickListener {
            val intent=Intent(context,AddressListActivity::class.java)
            intent.putExtra(Constants.addressListEdit,true)
            startActivity(intent)
        }
        return tempInflete
    }

    companion object {
    }
}