package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodAdapter
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Food
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_foods.*

/**
 * A simple [Fragment] subclass.
 * Use the [FoodsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodsFragment : Fragment() {

    private lateinit var miRecycler: RecyclerView
    private lateinit var miAdapter: RecyclerView.Adapter<*>
    private  lateinit var lista:MutableList<Food>
    private lateinit var database: FirebaseDatabase
    private lateinit var tabLayout: TabLayout
    private  lateinit var listaCategory:MutableList<Category>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaCategory= mutableListOf()
        lista= mutableListOf()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // tabLayout=tabsFoods

        var database= FirebaseDatabase.getInstance()
        var referenceRoot = database.getReference("").child("Category")
        referenceRoot.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                var j=snapshot.children.count()
                for (i in snapshot.children){
                    listaCategory.add(i.getValue(Category::class.java)!!)

                }
                miAdapter = FoodAdapter(lista)
                miRecycler.adapter = miAdapter;
                // miAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }
    companion object {
    }
}