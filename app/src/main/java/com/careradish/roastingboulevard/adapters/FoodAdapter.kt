package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.Constants
import com.squareup.picasso.Picasso


class FoodAdapter(var comidas: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    //  database.child("users").child(userId).setValue(user)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.foodItemName)
        var image: ImageView = itemView.findViewById(R.id.foodItemImage)
        /*
        init {
            itemView.setOnClickListener {
            }

        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.food_item, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = comidas[position]
        holder.name.text = food.name
        Picasso.get().load(food.photo).fit().into(holder.image)
        val delay=(position*Constants.DELAY_INCREMENT).toLong()
        setFadeAnimation(holder.itemView,delay);
        // Picasso.get().load(food.photo).fit().centerCrop().into(holder.image)
    }

    override fun getItemCount(): Int {
        return comidas.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView!!)
    }
    private fun setFadeAnimation(view: View,delay: Long) {
        val scaleAnim = ScaleAnimation(
            0f,
            1f,
            0f,
            1f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnim.startOffset=delay
        scaleAnim.duration = Constants.ANIM_DURATION
        view.startAnimation(scaleAnim)

    }
}