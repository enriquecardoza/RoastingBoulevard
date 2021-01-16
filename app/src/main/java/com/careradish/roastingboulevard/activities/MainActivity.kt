package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.fragments.BlankFragment
import com.careradish.roastingboulevard.fragments.InitFragment
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    private lateinit var tableLayout: TabLayout
    var blankFragment = BlankFragment()
    var initFragment = InitFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        val firebase: FirebaseConnection = FirebaseConnection()
        //firebase.writeFood(DatosClientes().get(0))


/*
        val es: ExecutorService = Executors.newCachedThreadPool()
        es.execute(EjemploRunnable(firebase))
        es.execute(EjemploRunnable(this.applicationContext))
        es.shutdown()
*/

    }

    private fun changeFragment(tab: Int) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()


        when (tab) {
            0 -> transaction.replace(R.id.layoutParent, initFragment)
            1 -> transaction.replace(R.id.layoutParent, blankFragment)
            2 -> {

            }
            else -> transaction.replace(R.id.layoutParent, initFragment)
        }

        transaction.commit()
    }

    private fun DatosClientes(): List<Food> {
        val Lista: MutableList<Food> = ArrayList()
        Lista.add(
            Food(
                0, "Rey", "a", arrayListOf("patatas", "huevo", "leche"), arrayListOf(
                    "huevos",
                    "lacteos"
                ), R.mipmap.shrek, 0f
            )
        )
        return Lista
    }

    public fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

}
