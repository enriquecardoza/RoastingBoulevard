package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.Tools
import com.careradish.roastingboulevard.tools.TranslationStrings
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.snackbar_delivering.view.*

class DeliveringSnackBar(
    parent: ViewGroup,
    content: View,
    contentViewCallback: com.google.android.material.snackbar.ContentViewCallback
) : BaseTransientBottomBar<DeliveringSnackBar>(parent, content, contentViewCallback) {

    lateinit var state: TextView
    lateinit var proressBar: ProgressBar

    init {
        this.getView().setPadding(0, 0, 0, 0)
        this.getView().layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        this.getView()
            .setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        state = getView().findViewById(R.id.textViewDeliveringState)
        proressBar = getView().findViewById(R.id.progressBar)
    }

    fun setState(newState: Delivery.DeliveryState) {
        state.text = TranslationStrings.get(newState.toString())
    }

    fun setProgress(newState: Delivery.DeliveryState) {
        proressBar.progress = newState.state
    }

    companion object {

        fun makeSnackbarDeliveing(
            view: View
        ): DeliveringSnackBar {
            val customSnackbar = createDeliveringSnackbar(view).apply {
                duration = Snackbar.LENGTH_INDEFINITE
                MainActivity.LockTabs()
                FirebaseConnection.attachToDeliveryState( {
                    setState(it)
                    setProgress(it)
                })
            }

            return customSnackbar
        }


        private fun createDeliveringSnackbar(view: View): DeliveringSnackBar {
            val parent = Tools.findSuitableParent(view) ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            val inflater = LayoutInflater.from(view.context)
            val content = inflater.inflate(
                R.layout.snackbar_delivering,
                parent,
                false
            )


            val contentViewCallback =
                object : com.google.android.material.snackbar.ContentViewCallback {

                    override fun animateContentIn(delay: Int, duration: Int) {
                    }

                    override fun animateContentOut(delay: Int, duration: Int) {
                    }
                }
            val snackbar = DeliveringSnackBar(parent, content, contentViewCallback)
            content.imageButtonCloseDelivering.setOnClickListener {
                MainActivity.UnlockTabs()
                snackbar.dismiss()
            }
            content.parentDelivering.setOnFocusChangeListener { v, hasFocus ->

                if (!hasFocus) {
                    MainActivity.UnlockTabs()
                    snackbar.dismiss()
                }
            }
            return snackbar
        }


    }

}