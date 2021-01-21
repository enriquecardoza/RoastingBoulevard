package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodAdapter
import com.careradish.roastingboulevard.adapters.FoodListPagerAdapter
import com.careradish.roastingboulevard.adapters.MyPagerAdapter
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_foods.*
import kotlinx.android.synthetic.main.fragment_foods.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [FoodsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodsFragment : Fragment() {


    private lateinit var database: FirebaseDatabase
    private lateinit var tabLayout: TabLayout
    private lateinit var listaCategory: MutableList<Category>
    private  lateinit var pager:ViewPager
    private lateinit var listaFoodList: MutableList<FoodListFragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaCategory = mutableListOf<Category>()
        listaFoodList= mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var tempInflater = inflater.inflate(R.layout.fragment_foods, container, false)
        tabLayout = tempInflater.tabsFoods
        pager=tempInflater.pagerFoods
        tabLayout.removeAllTabs()
        database = FirebaseDatabase.getInstance()
        var referenceRoot = database.getReference("").child("Category")
        referenceRoot.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for (i in snapshot.children) {
                    var category: Category = i.getValue(Category::class.java)!!
                    listaCategory.add(category)
                    tabLayout.addTab(tabLayout.newTab().setText(category.name))
                    var listFragment= FoodListFragment.newInstance(category)
                    listaFoodList.add(listFragment)
                }
                //ReadFoodsFromCategory(listaCategory.get(0))
                referenceRoot.removeEventListener(this)

                pager.setPageTransformer(true, ZoomOutPageTransformer())
               var adapterViewPager = FoodListPagerAdapter(activity?.getSupportFragmentManager())
                adapterViewPager.SetArr(listaCategory,listaFoodList)
               pager.adapter = adapterViewPager;
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        /*tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                ReadFoodsFromCategory(listaCategory.get(tab.position))
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })*/

        return tempInflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // tabLayout=tabsFoods


    }

    companion object {
    }



}