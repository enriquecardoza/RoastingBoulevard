package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodAdapter
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodListFragment : Fragment() {


    private var param1: Category? = null
    private lateinit var miRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Category?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var tempInflater = inflater.inflate(R.layout.fragment_food_list, container, false)
        miRecycler=tempInflater.findViewById(R.id.recyclerFoodList)
        miRecycler.setLayoutManager(GridLayoutManager(inflater.context, 2))
        miRecycler.setHasFixedSize(true)
        ReadFoodsFromCategory(param1!!)
        // Inflate the layout for this fragment
        return tempInflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(category: Category) =
            FoodListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, category)
                }
            }
    }

    private fun ReadFoodsFromCategory(category: Category) {

        var listaFoods= mutableListOf<Food>()
        var counter = 0
        var counterNotNull = 0
        for (id in category.foods) {
            if (id != null)
                counterNotNull++;
        }
        for (id in category.foods) {
            if (id != null) {
                var referenceRoot = FirebaseConnection.referenceRoot.child(Constants.foodsTittle).child(id.toString())
                referenceRoot.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        listaFoods.add(snapshot.getValue(Food::class.java)!!)
                        if (counter == counterNotNull - 1) {
                            var miAdapter = FoodAdapter(listaFoods)
                            miRecycler.adapter = miAdapter

                        }
                        counter++
                        referenceRoot.removeEventListener(this)
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

            }
        }

    }
}