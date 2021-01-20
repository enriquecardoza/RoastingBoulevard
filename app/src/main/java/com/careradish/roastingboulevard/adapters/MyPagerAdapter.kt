package com.careradish.roastingboulevard.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.fragments.*
import com.careradish.roastingboulevard.tools.Strings


class MyPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager!!) {

    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }


    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InitFragment()
            1 ->ProfileFragment()// CombosFragment()
            2 -> FoodsFragment()
            3 -> InformationFragment()
            4 -> ProfileFragment()
            else -> InitFragment()
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> Strings.get(R.string.init)
            1 -> Strings.get(R.string.combos)
            2 -> Strings.get(R.string.dishes)
            3 -> Strings.get(R.string.information)
            4 -> Strings.get(R.string.profile)
            else -> Strings.get(R.string.init)
        }
    }

    companion object {
        private const val NUM_ITEMS = 5
    }
}
