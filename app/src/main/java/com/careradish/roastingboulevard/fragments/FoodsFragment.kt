package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodAdapter
import com.careradish.roastingboulevard.classes.Food
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass.
 * Use the [FoodssFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodssFragment : Fragment() {

    private lateinit var miRecycler: RecyclerView
    private lateinit var miAdapter: RecyclerView.Adapter<*>
    private  lateinit var lista:MutableList<Food>
    private lateinit var database: FirebaseDatabase
    lateinit var referenceRoot: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lista= mutableListOf()
        database = FirebaseDatabase.getInstance()
        referenceRoot = database.getReference("").child("Foods")
        referenceRoot.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    lista.add(i.getValue(Food::class.java)!!)
                }
                miAdapter = FoodAdapter(lista)
                miRecycler.adapter = miAdapter;
                // miAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var tempInflater= inflater.inflate(R.layout.fragment_foods, container, false)
        miRecycler=tempInflater.findViewById(R.id.recyclerViewFoods)
        miRecycler.setHasFixedSize(true)

        return tempInflater
    }

    companion object {
    }
}