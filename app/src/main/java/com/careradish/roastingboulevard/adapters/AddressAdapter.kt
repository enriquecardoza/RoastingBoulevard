package com.careradish.roastingboulevard.adapters


import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.DirectionListActivity
import com.careradish.roastingboulevard.activities.EditAddressActivity
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.TranslationStrings


class AddressAdapter(var addresses: List<Address>) :
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var label: TextView = itemView.findViewById(R.id.textViewLabel)
        var addresWithNumber: TextView = itemView.findViewById(R.id.textViewAddressWithNumber)
        var postalCodeCity: TextView = itemView.findViewById(R.id.textViewPostalCodeCity)
        var addresType: TextView = itemView.findViewById(R.id.textViewAddressType)
        var editButton: ImageButton = itemView.findViewById(R.id.buttonGoEditAddress)
        var eraseButton: ImageButton = itemView.findViewById(R.id.buttonEraseAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater? = LayoutInflater.from(parent.context)
        val view: View? = inflater?.inflate(R.layout.item_address, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = addresses[position]

        holder.label.text = address.label
        holder.addresWithNumber.text = address.address + " Nº" + address.number
        holder.postalCodeCity.text = address.postalCode.toString() + " " + address.city
        holder.addresType.text = address.zoneType
        val context=holder.label.context
        holder.editButton.setOnClickListener {
            val inte = Intent(context, EditAddressActivity::class.java)
            inte.putExtra(Constants.addressToEdit, address)
            context.startActivity(inte)
        }

        holder.eraseButton.setOnClickListener {
            val builder = Builder( context)

            builder.setTitle("Confirm")
            builder.setMessage(TranslationStrings.get(R.string.are_you_sure))

            builder.setPositiveButton(
                R.string.yes
            ) { dialog, _ ->
                FirebaseConnection.eraseAddress(address)
                DirectionListActivity.adapterErased(address)
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
        return addresses.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

   /* private fun setFadeAnimation(view: View, delay: Long) {

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

    }*/


}