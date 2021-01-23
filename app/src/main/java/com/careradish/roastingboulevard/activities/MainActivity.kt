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


private const val NUM_PAGES = 5
class MainActivity : AppCompatActivity() {


    private lateinit var tableLayout: TabLayout

    private lateinit var mPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app =App()
        app.setContext(this)
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        tableLayout = tabsMain
        mPager = findViewById(R.id.pager)
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        val adapterViewPager = MyPagerAdapter(supportFragmentManager)
        mPager.adapter = adapterViewPager
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


        /*CustomSnackbar.make(
            findViewById(android.R.id.content),
            "R.string.title1",
            "R.string.action1").show()*/
        //tableLayout.visibility=View.GONE

    }



    fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }


}
