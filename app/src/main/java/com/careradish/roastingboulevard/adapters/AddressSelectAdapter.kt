package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.tools.App

class AddressSelectAdapter() :
    RecyclerView.Adapter<AddressSelectAdapter.ViewHolder>() {
    var onSelectedAddress: ((result: Address?)->Unit)? = null


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var label: TextView = itemView.findViewById(R.id.textViewLabelSelectAddress)
        var addresWithNumber: TextView =
            itemView.findViewById(R.id.textViewAddressWithNumberSelectAddress)
        var postalCodeCity: TextView =
            itemView.findViewById(R.id.textViewPostalCodeCitySelectAddress)
        var addresType: TextView = itemView.findViewById(R.id.textViewAddressTypeSelectAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.item_address_select, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val address = App.user?.addresses!!.get(App.user?.addresses!!.keys.elementAt(position))
        if (address != null) {
            holder.label.text = address.label
            holder.addresWithNumber.text = address.address + " Nº" + address.number
            holder.postalCodeCity.text = address.postalCode.toString() + " " + address.city
            holder.addresType.text = address.zoneType
        }
        holder.itemView.setOnClickListener {
            onSelectedAddress?.invoke(address)
        }
    }

    override fun getItemCount(): Int {
        return App.user?.addresses!!.size
    }


}

