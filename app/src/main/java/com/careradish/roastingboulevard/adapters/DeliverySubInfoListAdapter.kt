package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.TranslationStrings
import com.squareup.picasso.Picasso

class DeliverySubInfoListAdapter(val delivery:Delivery) : RecyclerView.Adapter<DeliverySubInfoListAdapter.ViewHolder>() {
    //  database.child("users").child(userId).setValue(user)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.textViewDeliverysubInfoListName)
        var amount: TextView = itemView.findViewById(R.id.textViewDeliverysubInfoListAmount)
        var price: TextView = itemView.findViewById(R.id.textViewDeliverysubInfoListprice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.item_delivery_subinfo_list, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food= delivery.foods[position]
        val amount=delivery.amountsOfFoods[position]
        holder.name.text=TranslationStrings.get(food.name)
        holder.amount.text="X$amount"
        val price=food.price
        holder.price.text="$price€"

    }

    override fun getItemCount(): Int {
        return delivery.foods.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView!!)
    }
}