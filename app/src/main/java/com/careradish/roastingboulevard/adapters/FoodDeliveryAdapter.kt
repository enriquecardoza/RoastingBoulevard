package com.careradish.roastingboulevard.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Tools
import com.careradish.roastingboulevard.tools.TranslationStrings

class FoodDeliveryAdapter() : RecyclerView.Adapter<FoodDeliveryAdapter.ViewHolder>() {
    //  database.child("users").child(userId).setValue(user)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.textViewFoodDeliveryName)
        var price: TextView = itemView.findViewById(R.id.textViewFoodDeliveryPrice)
        var buttErase: ImageButton = itemView.findViewById(R.id.buttonEraseFoodDelivery)
        var amount: TextView = itemView.findViewById(R.id.textViewFoodDeliveryAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.item_food_delivery, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = App.actualDelivery!!.foods[position]
        holder.name.text = food.getTranslatedName()
        val price = food.price
        holder.price.text = "$price €"
        val amount = App.actualDelivery!!.amountsOfFoods[position]
        holder.amount.text = "X$amount"
        holder.buttErase.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)

            builder.setTitle(TranslationStrings.get(R.string.confirmation))
            builder.setMessage(TranslationStrings.get(R.string.are_you_sure))

            builder.setPositiveButton(
                R.string.yes
            ) { dialog, _ ->
                val pos = App.actualDelivery!!.foods.indexOf(food)
                App.actualDelivery!!.amountsOfFoods.removeAt(pos)
                App.actualDelivery!!.foods.remove(food)
                notifyItemRemoved(position)
                dialog.dismiss()
                if (App.actualDelivery!!.foods.size <= 0) {
                    MainActivity.hideOrderButton()
                }
            }

            builder.setNegativeButton(
                R.string.No
            ) { dialog, _ ->
                dialog.dismiss()
            }

            val alert: AlertDialog? = builder.create()
            alert?.setOnShowListener(DialogInterface.OnShowListener {
                alert.getButton(AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(Tools.getColor(R.color.paleRed))
                alert.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(Tools.getColor(R.color.paleOrange))
            })
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