package com.careradish.roastingboulevard.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.DeliveringSnackBar
import com.careradish.roastingboulevard.adapters.MainPagerAdapter
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.ZoomOutPageTransformer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_RoastingBoulevard)
        setContentView(R.layout.activity_main)
        App.Init(this)
        actionBar?.hide()

        //DeveloperFreshData.UploadAllData()
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        App.contentView = findViewById(android.R.id.content)
        instance = this
        App.recoverPrefUser(this)
        PreparePager()
        floatingButton = ActionButtonDelivering
        orderLayoutButton = seeOrderLayoutButton
        orderLayoutButton.setOnClickListener {

            val inte = Intent(this, FoodListDeliveryActivity::class.java)
            startActivity(inte)

        }

        orderLayoutButton.visibility = View.GONE

        floatingButton.setOnClickListener {
            if (App.delivering) {
                val snackbar = DeliveringSnackBar.makeSnackbarDeliveing(App.contentView)
                snackbar.show()
                floatingButton.visibility = View.GONE
            }
        }
        checkFloatingDeliveringButtonVisibility()
        if (App.delivering)
            AttachToDelivering()
    }
    fun AttachToDelivering() {

        FirebaseConnection.attachToDeliveryState( {
            if (it == Delivery.DeliveryState.delivered) {
                App.sendNotification()
                App.actualDelivery = null
                App.delivering = false
                App.deliveringDelivery = null
                HideFloatingActionButton()
            }
        })

    }

    private fun checkFloatingDeliveringButtonVisibility() {
        if (!App.delivering)
            HideFloatingActionButton()
        else {
            SetvisibleFloatingButton()
        }
    }


    private fun SetvisibleFloatingButton() {
        floatingButton.visibility = View.VISIBLE
       //floatingButton.startAnimation(AnimationUtils.loadAnimation(floatingButton.context, R.anim.bounce))
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

    override fun onResume() {
        super.onResume()
        if (App.actualDelivery != null && App.actualDelivery?.foods?.size!! <= 0)
            hideOrderButton()
    }



    companion object {
        private lateinit var adapterViewPager: MainPagerAdapter
        private lateinit var mPager: ViewPager
        lateinit var instance: MainActivity
        private lateinit var orderLayoutButton: ConstraintLayout
        private lateinit var tableLayout: TabLayout
        private lateinit var floatingButton: FloatingActionButton

        fun HideFloatingActionButton() {
            floatingButton.visibility = View.GONE
        }

        fun ForceUpdatePagerAdapter() {
            adapterViewPager.notifyDataSetChanged()
        }

        fun ForceUpdatePagerAdapter(pos: Int) {

            mPager.adapter = adapterViewPager
            adapterViewPager.notifyDataSetChanged()
            mPager.currentItem = pos
            tableLayout.setScrollPosition(pos, 0f, true)
        }

        fun changueSelectedTab(pos: Int) {
            mPager.currentItem = pos
            tableLayout.setScrollPosition(pos, 0f, true)
            adapterViewPager.notifyDataSetChanged()
        }

        fun showOrderButton() {
            orderLayoutButton.visibility = View.VISIBLE
            //val anim: Animation = ScaleAnimation(
            //    1f, 1f,  // Start and end values for the X axis scaling
            //    0f, 1f,  // Start and end values for the Y axis scaling
            //    Animation.RELATIVE_TO_SELF, 0f,  // Pivot point of X scaling
            //    Animation.RELATIVE_TO_SELF, 1f
            //)
//
            //anim.fillAfter = true // Needed to keep the result of the animation
            //anim.duration = 200
            //orderLayoutButton.startAnimation(anim)
        }

        fun hideOrderButton() {
            orderLayoutButton.visibility = View.GONE
        }

        fun showToast(text: String) {
            Toast.makeText(App.context, text, Toast.LENGTH_LONG).show()
        }

        fun LockTabs() {
            tableLayout.visibility = View.INVISIBLE
            orderLayoutButton.visibility = View.GONE
        }

        fun UnlockTabs() {
            tableLayout.visibility = View.VISIBLE
            if (App.actualDelivery != null)
                orderLayoutButton.visibility = View.VISIBLE

            if (App.deliveringDelivery!=null)
                floatingButton.visibility = View.VISIBLE
            else
                floatingButton.visibility = View.GONE
        }


    }


}
