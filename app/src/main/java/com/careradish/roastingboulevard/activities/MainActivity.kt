package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.MyPagerAdapter
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var tableLayout: TabLayout

    private lateinit var mPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.context = this
        App.contentView = findViewById(android.R.id.content)
        actionBar?.hide()
        tableLayout = tabsMain
        mPager = findViewById(R.id.pager)
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        val adapterViewPager = MyPagerAdapter(supportFragmentManager)
        mPager.adapter = adapterViewPager
        tableLayout.setupWithViewPager(pager)
        App.tabLayout=tableLayout
    }


    fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }


}
