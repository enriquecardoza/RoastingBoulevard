package com.careradish.roastingboulevard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.fragments.FoodListFragment
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Tools

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
    override fun getPageTitle(position: Int): CharSequence {
        val l=Tools.getDisplayLanguage()
        val v=listaCategory[position].name[App.getCategoryLanguagePos(listaCategory[position])]
        "TranslationStrings.get(listaCategory[position].name)"
        return v //TranslationStrings.get(listaCategory[position].name)
    }

}