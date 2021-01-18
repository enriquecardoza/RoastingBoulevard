package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.fragments.*
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var tableLayout: TabLayout

    var initFragment = InitFragment()
    var foodsFragment = FoodssFragment()
    var combosFragment = CombosFragment()
    var informationFragment = InformationFragment()
    var profileFragment = ProfileFragment()
    private val firebase: FirebaseConnection= FirebaseConnection(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        tableLayout = tabsMain
        changeFragment(0)
        tableLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                changeFragment(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })


        //firebase.writeFood(DatosClientes().get(0))
        //firebase.readFood(0)
    }

    private fun changeFragment(tab: Int) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()


        when (tab) {
            0 -> transaction.replace(R.id.mainFragmentParent, initFragment)
            1 -> {transaction.replace(R.id.mainFragmentParent,combosFragment )}
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



}
