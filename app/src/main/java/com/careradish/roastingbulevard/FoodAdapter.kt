package com.careradish.roastingbulevard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(var comidas: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView;
        lateinit var image: ImageView;

        init {
            name = itemView.findViewById(R.id.textView)
            image = itemView.findViewById(R.id.imageView)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.fooditemcard, parent, false)
        return FoodAdapter.ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food: Food = comidas[position]
        holder.name.text = food.nombre
        holder.image.setImageResource(food.image)
    }

    override fun getItemCount(): Int {
        return comidas.size
    }
}