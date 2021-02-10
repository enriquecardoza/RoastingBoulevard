package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.tools.App


class DeliveryInfoAdapter() : RecyclerView.Adapter<DeliveryInfoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var amount: TextView = itemView.findViewById(R.id.textViewTotalAmountInfoDelivery)
        var name: TextView = itemView.findViewById(R.id.textViewDateInfoDelivery)
        private var recycler: RecyclerView = itemView.findViewById(R.id.reciclerListFoodInDelivery)
        var visible = false
        public fun setClickListener() {
            itemView.setOnClickListener {
                if (!visible) {
                    recycler.visibility = View.VISIBLE
                    val anim: Animation = ScaleAnimation(
                        1f, 1f,  // Start and end values for the X axis scaling
                        0f, 1f,  // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0f,  // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 1f
                    )
                    anim.fillAfter = true // Needed to keep the result of the animation
                    anim.duration = 200
                    visible = true
                } else {
                    recycler.visibility = View.GONE
                    visible = false
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.item_delivery_info, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val indexes: List<String> = ArrayList<String>(App.user?.deliveries?.keys)

        val delivery = App.user?.deliveries?.get(indexes[position]) as Delivery
        holder.name.text = delivery.id
        holder.amount.text = delivery.amount.toString()
        holder.setClickListener()

        val arr = arrayListOf<String>()
        for (i in delivery.foods)
            arr.add(i.name)

        //holder.recickler.adapter=itemsAdapter
        // holder.setClickListener(food)
    }

    override fun getItemCount(): Int {
        return App.user?.deliveries?.size!!
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}