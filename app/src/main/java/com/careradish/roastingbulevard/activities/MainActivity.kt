package com.careradish.roastingbulevard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

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
        })
    }



    private fun DatosClientes(): List<Food> {
        val Lista: MutableList<Food> = ArrayList()
        Lista.add(Food(0, "Rey", R.mipmap.shrek))
        Lista.add(Food(0, "Gil", R.mipmap.shrek))
        Lista.add(Food(0, "Alonso", R.mipmap.shrek))
        return Lista
    }

    public fun ShowToast(texto: String) {
        Toast.makeText(applicationContext, texto, Toast.LENGTH_SHORT).show()
    }
}

