package com.careradish.roastingboulevard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.fragments.*
import com.careradish.roastingboulevard.tools.Strings

class FoodListPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager!!)  {


    public var NUM_ITEMS = 5
    private var listaFoodList= mutableListOf<FoodListFragment>()
    private var listaCategory= mutableListOf<Category>()
    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }


    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return listaFoodList.get(position)
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return listaCategory.get(position).name
    }

    public  fun SetArr(listaCategory:MutableList<Category>,list:MutableList<FoodListFragment>){
        this.listaCategory=listaCategory
        listaFoodList=list

    }
}