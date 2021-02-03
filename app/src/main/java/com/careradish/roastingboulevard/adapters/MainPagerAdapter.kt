package com.careradish.roastingboulevard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.fragments.*
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.TranslationStrings


class MainPagerAdapter(fragmentManager: FragmentManager?) : FragmentStatePagerAdapter(fragmentManager!!) {

    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }


    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InitFragment()
            1 -> CombosFragment()
            2 -> DishesFragment()
            3 -> InformationFragment()
            4 -> {
                if (!App.logged)
                    LoginFragment()
                else
                    ProfileFragment()
            }
            else -> InitFragment()
        }
    }

    override fun getItemPosition(`object`: Any): Int {

        return if(`object` is LoginFragment&&App.logged){
            super.getItemPosition(ProfileFragment())
        }else  if(`object` is ProfileFragment&&!App.logged) {
            super.getItemPosition(LoginFragment())
        }else
            super.getItemPosition( `object`)
    }
    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> TranslationStrings.get(R.string.init)
            1 -> TranslationStrings.get(R.string.combos)
            2 -> TranslationStrings.get(R.string.dishes)
            3 -> TranslationStrings.get(R.string.information)
            4 -> TranslationStrings.get(R.string.profile)
            else -> TranslationStrings.get(R.string.init)
        }
    }

    companion object {
        private const val NUM_ITEMS = 5
    }

}
