package com.careradish.roastingboulevard.activities


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.MainPagerAdapter
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.DeveloperFreshData
import com.careradish.roastingboulevard.tools.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        App.Init(this)
        App.contentView = findViewById(android.R.id.content)
        instance=this
        actionBar?.hide()
        tableLayout = tabsMain
        mPager = findViewById(R.id.pagerMain)
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        adapterViewPager = MainPagerAdapter(supportFragmentManager)
        mPager.adapter = adapterViewPager
        tableLayout.setupWithViewPager(pagerMain)
        App.tabLayout = tableLayout
        orderLayoutButton=seeOrderLayoutButton
        orderLayoutButton.setOnClickListener {
            DeveloperFreshData.UploadAllData()
        }
    }



    fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private lateinit var adapterViewPager: MainPagerAdapter
        private lateinit var mPager: ViewPager
        lateinit var instance:MainActivity
        private lateinit var orderLayoutButton: ConstraintLayout
        private lateinit var tableLayout: TabLayout
        fun ForceUpdatePagerAdapter() {
            adapterViewPager.notifyDataSetChanged()
        }

        fun ForceUpdatePagerAdapter(pos: Int) {

            mPager.adapter = adapterViewPager
            adapterViewPager.notifyDataSetChanged()
            mPager.currentItem = pos
            tableLayout.setScrollPosition(pos,0f,true)
        }

        fun changueSelectedTab(pos: Int){
            mPager.currentItem = pos
            tableLayout.setScrollPosition(pos,0f,true)
            adapterViewPager.notifyDataSetChanged()
        }
        fun setVisibleSeeOrderButton(){
            orderLayoutButton.visibility=View.VISIBLE
        }
        fun setInvisibleSeeOrderButton(){
            orderLayoutButton.visibility=View.GONE
        }
    }


}
