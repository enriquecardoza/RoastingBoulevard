package com.careradish.roastingboulevard.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.AddressListActivity
import com.careradish.roastingboulevard.activities.DeliveriesList
import com.careradish.roastingboulevard.activities.EditDataActivity
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    lateinit var textWelcome: TextView
    lateinit var buttAddress: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var tempInflete = inflater.inflate(R.layout.fragment_profile, container, false)
        textWelcome = tempInflete.textViewProfileWelcome
        buttAddress = tempInflete.buttonAddresses
        textWelcome.text = TranslationStrings.get(R.string.profile_welcome) + " " + App.user?.name
        buttAddress.setOnClickListener {
            val intent = Intent(context, AddressListActivity::class.java)
            intent.putExtra(Constants.isAddressListEditOrSelect, true)
            startActivity(intent)
        }
        tempInflete.buttonCloseSession.setOnClickListener {
            FirebaseConnection.logoutUser()
            App.user = null
            App.erasePrefUser()
            MainActivity.ForceUpdatePagerAdapter(2)
            Toast.makeText(context,TranslationStrings.get(R.string.session_closed),Toast.LENGTH_LONG).show()
        }
        tempInflete.buttonPersonalData.setOnClickListener {
            val intent=Intent(context,EditDataActivity::class.java)
            startActivity(intent)
        }
        tempInflete.buttonMyOrders.setOnClickListener {
            val intent=Intent(context,DeliveriesList::class.java)
            startActivity(intent)
        }
        return tempInflete
    }

    companion object {
    }
}