package com.careradish.roastingboulevard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.fragments.BlankFragment
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var miRecicler: RecyclerView
    private lateinit var miAdapter: RecyclerView.Adapter<*>

    private lateinit var tableLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tableLayout = tabsMain
        tableLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                val fragmentManager: android.app.FragmentManager = fragmentManager
                val transaction: android.app.FragmentTransaction = fragmentManager.beginTransaction()
                var bf=BlankFragment()
                transaction.add(R.id.layoutParent,bf)
                transaction.commit()

                //if (tab.position == 0)

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

        /* miRecicler=recliclador
         miRecicler.setHasFixedSize(true)
        // miRecicler.layoutManager = GridLayoutManager(this,2);

         miAdapter = FoodAdapter(DatosClientes())

         miRecicler.adapter = miAdapter;




        // myRef.setValue("Hello, World!")

         myRef.addValueEventListener(object : ValueEventListener {
             override fun onDataChange(dataSnapshot: DataSnapshot) {
                 // This method is called once with the initial value and again
                 // whenever data at this location is updated.
                 val value = dataSnapshot.getValue(String::class.java)
                 Toast.makeText(applicationContext, "Value is: $value", Toast.LENGTH_SHORT).show()
             }

             override fun onCancelled(error: DatabaseError) {
                 Toast.makeText(applicationContext, "Failed to read value.", Toast.LENGTH_SHORT)
                     .show()
             }
         })*/
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

    public fun ShowToast(texto: String) {
        Toast.makeText(applicationContext, texto, Toast.LENGTH_SHORT).show()
    }

}
