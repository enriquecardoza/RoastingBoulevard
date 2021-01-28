package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    lateinit var textWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var tempInflete = inflater.inflate(R.layout.fragment_profile, container, false)
        textWelcome = tempInflete.textViewProfileWelcome
        textWelcome.text=TranslationStrings.get(R.string.profile_welcome)+" "+App.user.name
        return tempInflete
    }

    companion object {
    }
}