package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.MyPagerAdapter
import com.careradish.roastingboulevard.fragments.*
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


private const val NUM_PAGES = 5
class MainActivity : AppCompatActivity() {


    private lateinit var tableLayout: TabLayout

    var initFragment = InitFragment()
    var foodsFragment = FoodsFragment()
    var combosFragment = CombosFragment()
    var informationFragment = InformationFragment()
    var profileFragment = ProfileFragment()
    private lateinit var mPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var app:App=App()
        app.setContext(this)
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        tableLayout = tabsMain
        //changeFragment(0)
        /*tableLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                changeFragment(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })*/
        mPager = findViewById(R.id.pager)
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        var adapterViewPager = MyPagerAdapter(supportFragmentManager)
        mPager.adapter = adapterViewPager;
        /*mPager.addOnPageChangeListener(object : OnPageChangeListener {
            // This method will be invoked when a new page becomes selected.
            override fun onPageSelected(position: Int) {
            }

            // This method will be invoked when the current page is scrolled
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            override fun onPageScrollStateChanged(state: Int) {
                // Code goes here
            }
        })*/
        tableLayout.setupWithViewPager(mPager)
    }

    private fun changeFragment(tab: Int) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()


        when (tab) {
            0 -> transaction.replace(R.id.mainFragmentParent, initFragment)
            1 -> {
                transaction.replace(R.id.mainFragmentParent, combosFragment)
            }
            2 -> transaction.replace(R.id.mainFragmentParent, foodsFragment)
            3 -> transaction.replace(R.id.mainFragmentParent, informationFragment)
            4 -> transaction.replace(R.id.mainFragmentParent, profileFragment)
            else -> transaction.replace(R.id.mainFragmentParent, initFragment)
        }

        transaction.commit()
    }


    public fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> initFragment
                1 -> combosFragment
                2 -> foodsFragment
                3 -> informationFragment
                4 -> profileFragment
                else -> initFragment
            }
        }
    }

}
