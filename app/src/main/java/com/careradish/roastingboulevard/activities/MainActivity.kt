package com.careradish.roastingboulevard.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.MainPagerAdapter
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        App.Init(this)
        actionBar?.hide()
        App.contentView = findViewById(android.R.id.content)
        instance=this

        PreparePager()

        orderLayoutButton=seeOrderLayoutButton
        orderLayoutButton.setOnClickListener {

            val inte=Intent(this, FoodDeliveryListActivity::class.java)
            startActivity(inte)
        }

        seeOrderLayoutButton.visibility=View.GONE

    }

    private fun PreparePager() {
        tableLayout = tabsMain
        mPager = findViewById(R.id.pagerMain)
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        adapterViewPager = MainPagerAdapter(supportFragmentManager)
        mPager.adapter = adapterViewPager
        tableLayout.setupWithViewPager(pagerMain)
        App.tabLayout = tableLayout
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
            tableLayout.setScrollPosition(pos, 0f, true)
        }

        fun changueSelectedTab(pos: Int){
            mPager.currentItem = pos
            tableLayout.setScrollPosition(pos, 0f, true)
            adapterViewPager.notifyDataSetChanged()
        }
        fun setVisibleSeeOrderButton(){
            orderLayoutButton.visibility=View.VISIBLE
            val anim: Animation = ScaleAnimation(
                1f, 1f,  // Start and end values for the X axis scaling
                0f, 1f,  // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f,  // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f
            )

            anim.fillAfter = true // Needed to keep the result of the animation

            anim.duration = 200
            orderLayoutButton.startAnimation(anim)
        }
        fun setInvisibleSeeOrderButton(){
            orderLayoutButton.visibility=View.GONE

        }
        fun showToast(text: String) {
            Toast.makeText(App.context, text, Toast.LENGTH_SHORT).show()
        }
        fun LockTabs() {
            tableLayout.visibility = View.GONE
            orderLayoutButton.visibility=View.GONE
        }

        fun UnlockTabs() {
            tableLayout.visibility = View.VISIBLE
            if (App.actualDelivery!=null)
                orderLayoutButton.visibility=View.VISIBLE
        }
    }


}
