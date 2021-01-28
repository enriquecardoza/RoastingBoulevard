package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.viewpager.widget.ViewPager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodListPagerAdapter
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dishes.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [DishesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DishesFragment : Fragment() {


    private lateinit var tabLayout: TabLayout
    private lateinit var listaCategory: MutableList<Category>
    private  lateinit var pager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaCategory = mutableListOf<Category>()

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
        var referenceRoot = FirebaseConnection.referenceRoot.child(Constants.categoryTittle)
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


                val vg = tabLayout.getChildAt(0) as ViewGroup
                val tabsCount = vg.childCount

                for (i in 0 until tabsCount) {
                    val delay = i * Constants.DELAY_INCREMENT
                    val vgTab = vg.getChildAt(i) as ViewGroup
                    vgTab.scaleX = 0f
                    vgTab.scaleY = 0f
                    vgTab.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setStartDelay(delay.toLong())
                        .setInterpolator(FastOutSlowInInterpolator())
                        .setDuration(Constants.ANIM_DURATION)
                        .start()
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
               App.hideFoodSnackbar()

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
    }



}