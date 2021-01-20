package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.adapters.FoodAdapter
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Food
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_foods.*
import kotlinx.android.synthetic.main.fragment_foods.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [FoodsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodsFragment : Fragment() {

    private lateinit var miRecycler: RecyclerView
    private lateinit var miAdapter: RecyclerView.Adapter<*>

    private lateinit var listaFoods: MutableList<Food>
    private lateinit var database: FirebaseDatabase
    private lateinit var tabLayout: TabLayout
    private lateinit var listaCategory: MutableList<Category>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaCategory = mutableListOf<Category>()
        listaFoods = mutableListOf()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var tempInflater = inflater.inflate(R.layout.fragment_foods, container, false)
        miRecycler = tempInflater.recyclerViewFoods
        miRecycler.setHasFixedSize(true)
        tabLayout = tempInflater.tabsFoods
        tabLayout.removeAllTabs()
        database = FirebaseDatabase.getInstance()
        var referenceRoot = database.getReference("").child("Category")
        referenceRoot.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for (i in snapshot.children) {
                    var category: Category = i.getValue(Category::class.java)!!
                    listaCategory.add(category)
                    tabLayout.addTab(tabLayout.newTab().setText(category.name))
                }
                //ReadFoodsFromCategory(listaCategory.get(0))
                referenceRoot.removeEventListener(this)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                ReadFoodsFromCategory(listaCategory.get(tab.position))
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return tempInflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // tabLayout=tabsFoods


    }

    companion object {
    }


    private fun ReadFoodsFromCategory(category: Category) {

        listaFoods.clear()
        var counter = 0
        var counterNotNull = 0
        for (id in category.foods) {
            if (id != null)
                counterNotNull++;
        }
        for (id in category.foods) {
            if (id != null) {
                var referenceRoot = database.getReference("").child("Food").child(id.toString())
                referenceRoot.addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        listaFoods.add(snapshot.getValue(Food::class.java)!!)
                        if (counter == counterNotNull - 1) {
                            miAdapter = FoodAdapter(listaFoods)
                            miRecycler.adapter = miAdapter;
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