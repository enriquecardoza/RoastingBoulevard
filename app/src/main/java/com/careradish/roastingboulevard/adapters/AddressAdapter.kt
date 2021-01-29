package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.CustomSnackbar
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import com.squareup.picasso.Picasso

class AddressAdapter(var addresses: List<Address>): RecyclerView.Adapter<AddressAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var label: TextView = itemView.findViewById(R.id.textViewLabel)
        var addresWithNumber: TextView = itemView.findViewById(R.id.textViewAddressWithNumber)
        var postalCodeCity: TextView = itemView.findViewById(R.id.textViewPostalCodeCity)
        var addresType: TextView = itemView.findViewById(R.id.textViewAddressType)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.item_food, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = addresses[position]
        holder.label.text=address.label
        holder.addresWithNumber.text=address.address+" Nº"+address.number
        holder.postalCodeCity.text=address.postalCde+" "+address.city
        holder.addresType.text=address.zoneType
    }

    override fun getItemCount(): Int {
        return addresses.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView!!)
    }
    private fun setFadeAnimation(view: View, delay: Long) {

        /* val scaleAnim = ScaleAnimation(
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
         view.startAnimation(scaleAnim)*/

    }


}