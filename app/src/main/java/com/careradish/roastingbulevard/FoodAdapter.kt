package com.careradish.roastingbulevard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FoodAdapter(var comidas: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
 //  database.child("users").child(userId).setValue(user)




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView;
        lateinit var image: ImageView;
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Foods/"+"Migas")
        var ss:String="test"
        init {
            name = itemView.findViewById(R.id.textView)
            image = itemView.findViewById(R.id.imageView)
            itemView.setOnClickListener {


                myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        ss=dataSnapshot.getValue().toString()
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // ...
                    }
                })
                Toast.makeText(name.context, ss, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.fooditemcard, parent, false)
        return FoodAdapter.ViewHolder(view!!)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = comidas[position]
        holder.name.text = food.nombre
        holder.image.setImageResource(food.image)

    }

    override fun getItemCount(): Int {
        return comidas.size
    }
}