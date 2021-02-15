package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.squareup.picasso.Picasso


class FoodAdapter(var comidas: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.foodItemName)
        var image: ImageView = itemView.findViewById(R.id.foodItemImage)


        public  fun setClickListener(food: Food){
            itemView.setOnClickListener {
                val snackbar= CustomSnackbar.makeSnackbarFood(App.contentView, food)
                App.actualSnackBar=snackbar
                snackbar.show()
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.item_food, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = comidas[position]
        holder.name.text = food.returnTranslatedName()
        FirebaseConnection.getImageUri(food.photo, {
            Picasso.get().load(it).fit().centerCrop().into(holder.image)
        })

        //val delay=(position*Constants.DELAY_INCREMENT).toLong()
        //setFadeAnimation(holder.itemView, delay);
        holder.setClickListener(food)
        // Picasso.get().load(food.photo).fit().centerCrop().into(holder.image)
    }

    override fun getItemCount(): Int {
        return comidas.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView!!)
    }
}