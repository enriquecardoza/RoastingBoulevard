package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.tools.App


class DeliveryInfoAdapter() : RecyclerView.Adapter<DeliveryInfoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var amount: TextView = itemView.findViewById(R.id.textViewTotalAmountInfoDelivery)
        var name: TextView = itemView.findViewById(R.id.textViewDateInfoDelivery)
        var recycler: RecyclerView = itemView.findViewById(R.id.reciclerListFoodInDelivery)
        var visible = false
        //((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        val anim: Animation = ScaleAnimation(
            1f, 1f,  // Start and end values for the X axis scaling
            0f, 1f,  // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0f,  // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, -1f
        )
        public fun setClickListener() {
            recycler.itemAnimator?.changeDuration=1000L
            itemView.setOnClickListener {
                if (!visible) {
                    recycler.visibility = View.VISIBLE

                    anim.fillAfter = true // Needed to keep the result of the animation
                    anim.duration = 200
                    recycler.startAnimation(anim)
                } else {
                    recycler.visibility = View.GONE
                }
                visible=!visible

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
        holder.amount.text = delivery.totalPrice.toString()+"€"


        val arr = arrayListOf<String>()
        for (i in delivery.foods)
            arr.add(i.returnTranslatedName())
        holder.recycler.visibility = View.GONE
        val adapter = DeliverySubInfoListAdapter(delivery)
        holder.recycler.adapter = adapter

        holder.recycler.layoutManager = LinearLayoutManager(holder.name.context)
        //holder.recycler.setHasFixedSize(true)
        holder.itemView.setOnClickListener {
            if (holder.visible) {
                holder.recycler.visibility = View.GONE
            }
            else {
                holder.recycler.visibility = View.VISIBLE
                TransitionManager.beginDelayedTransition(
                    holder.itemView as ViewGroup,
                    AutoTransition()
                )
            }
            holder.visible=!holder.visible

        }
    }

    override fun getItemCount(): Int {
        return App.user?.deliveries?.size!!
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}