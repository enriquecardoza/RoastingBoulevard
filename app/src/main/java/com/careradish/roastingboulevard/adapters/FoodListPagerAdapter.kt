package com.careradish.roastingboulevard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.fragments.*
import com.careradish.roastingboulevard.tools.Strings

class FoodListPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager!!)  {

    private var listaCategory= mutableListOf<Category>()
    // Returns total number of pages

    override fun getCount(): Int {
        return listaCategory.count()
    }

    constructor(listaCategory: MutableList<Category>,fragmentManager: FragmentManager?):this(fragmentManager){
        this.listaCategory=listaCategory
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        var fragment: FoodListFragment? =null
        for (i in 0..listaCategory.count()){
            if (i == position){
                fragment = FoodListFragment.newInstance(listaCategory[position])
                break
            }
        }
        return fragment!!
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return listaCategory.get(position).name
    }

}