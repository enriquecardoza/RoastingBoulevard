package com.careradish.roastingboulevard.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.AddressListActivity
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.App.Companion.context
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.TranslationStrings
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_food_delivery.view.*

class FoodDeliveryAdapter () : RecyclerView.Adapter<FoodDeliveryAdapter.ViewHolder>() {
    //  database.child("users").child(userId).setValue(user)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.textViewFoodDeliveryName)
        var price: TextView = itemView.findViewById(R.id.textViewFoodDeliveryPrice)
        var buttErase: ImageButton = itemView.findViewById(R.id.buttonEraseFoodDelivery)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.item_food, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = App.actualDelivery!!.foods[position]
        holder.name.text = TranslationStrings.get(food.name)
        val price=food.price
        holder.price.text = TranslationStrings.get("$price €")
        holder.buttErase.setOnClickListener {
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Confirm")
            builder.setMessage(TranslationStrings.get(R.string.are_you_sure))

            builder.setPositiveButton(
                R.string.yes
            ) { dialog, _ ->
                App.actualDelivery!!.foods.remove(food)
                notifyItemRemoved(position)
                dialog.dismiss()
            }

            builder.setNegativeButton(
                R.string.No
            ) { dialog, _ ->
                dialog.dismiss()
            }

            val alert: AlertDialog? = builder.create()
            alert!!.show()
        }
    }

    override fun getItemCount(): Int {
        return App.actualDelivery!!.foods.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView!!)
    }
}