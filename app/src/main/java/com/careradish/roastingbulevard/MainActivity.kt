package com.careradish.roastingbulevard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var miRecicler: RecyclerView
    private lateinit var miAdapter: RecyclerView.Adapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        miRecicler=recliclador
        miRecicler.setHasFixedSize(true)
       // miRecicler.layoutManager = GridLayoutManager(this,2);

        miAdapter = FoodAdapter(DatosClientes())

        miRecicler.adapter = miAdapter;
    }



    private fun DatosClientes(): List<Food> {
        val Lista: MutableList<Food> = ArrayList()
        Lista.add(Food(0,"Rey",R.mipmap.shrek))
        Lista.add(Food(0,"Gil",R.mipmap.shrek))
        Lista.add(Food(0,"Alonso",R.mipmap.shrek))
        return Lista
    }
}

