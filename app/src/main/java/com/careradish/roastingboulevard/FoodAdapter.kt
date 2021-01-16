package com.careradish.roastingboulevard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.classes.Food
import com.google.firebase.database.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FoodAdapter(var comidas: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
 //  database.child("users").child(userId).setValue(user)

    init {
        comidas=comidas
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.foodItemName)
        var image: ImageView = itemView.findViewById(R.id.foodItemPhoto)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Foods/" + "0")
        init {
            itemView.setOnClickListener {

                val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
                        //delay(1000L)
                    myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            var food: Food? =dataSnapshot.getValue(Food::class.java)
                            Toast.makeText(name.context, food?.name, Toast.LENGTH_SHORT).show()
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            // ...
                        }
                    })
                    }




                /*myRef.addChildEventListener(object : ChildEventListener {

                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        Toast.makeText(name.context, ss, Toast.LENGTH_SHORT).show()
                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {}
                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                    override fun onCancelled(error: DatabaseError) {}
                })*/



            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.food_item, parent, false)
        return ViewHolder(view!!)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = comidas[position]
        holder.name.text = food.name
        holder.image.setImageResource(food.photo)

    }

    override fun getItemCount(): Int {
        return comidas.size
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView!!)
    }
}