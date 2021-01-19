package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodAdapter
import com.careradish.roastingboulevard.classes.Food
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 * Use the [InitFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitFragment : Fragment() {

    private lateinit var miRecycler: RecyclerView
    private lateinit var miAdapter: RecyclerView.Adapter<*>
    private  lateinit var lista:MutableList<Food>
    private lateinit var database: FirebaseDatabase
    lateinit var referenceRoot: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lista= mutableListOf()
        database = FirebaseDatabase.getInstance()
        referenceRoot = database.getReference("").child("Food")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var tempInflater= inflater.inflate(R.layout.fragment_init, container, false)
        miRecycler=tempInflater.findViewById(R.id.recyclerViewInit)
        miRecycler.setHasFixedSize(true)
        referenceRoot.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children) {
                    lista.add(i.getValue(Food::class.java)!!)
                }
                miAdapter = FoodAdapter(lista)
                miRecycler.adapter = miAdapter;
                // miAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return tempInflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InitFragment().apply {

            }
    }

    /*
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
        Lista.add(
            Food(
                1, "a", "b", arrayListOf("a", "b", "c"), arrayListOf(
                    "a",
                    "v"
                ), R.mipmap.shrek, 0f
            )
        )
        return Lista
    }
*/
}