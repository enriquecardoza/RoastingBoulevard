package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodListPagerAdapter
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.tools.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_foods.*
import kotlinx.android.synthetic.main.fragment_foods.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [DishesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DishesFragment : Fragment() {


    private lateinit var database: FirebaseDatabase
    private lateinit var tabLayout: TabLayout
    private lateinit var listaCategory: MutableList<Category>
    private  lateinit var pager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaCategory = mutableListOf<Category>()
        database = FirebaseDatabase.getInstance()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var tempInflater = inflater.inflate(R.layout.fragment_dishes, container, false)
        tabLayout = tempInflater.tabsFoods
        pager=tempInflater.pagerFoods
        tabLayout.removeAllTabs()

        CreateTabs()

        return tempInflater
    }

    private fun CreateTabs() {
        var referenceRoot = database.getReference("").child("Category")
        referenceRoot.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {


                referenceRoot.removeEventListener(this)
                listaCategory.clear()
                for (i in snapshot.children) {
                    var category: Category = i.getValue(Category::class.java)!!
                    listaCategory.add(category)
                }

                var adapterViewPager = FoodListPagerAdapter(
                    listaCategory, childFragmentManager
                )
                pager.adapter = adapterViewPager
                pager.setPageTransformer(true, ZoomOutPageTransformer())
                tabLayout.setupWithViewPager(pager)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
    }



}