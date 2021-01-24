package com.app.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.App
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.snackbar_food.view.*


class CustomSnackbar(
    parent: ViewGroup,
    content: View,
    contentViewCallback: com.google.android.material.snackbar.ContentViewCallback
) : BaseTransientBottomBar<CustomSnackbar>(parent, content, contentViewCallback) {

 lateinit var food:Food

    init {
        this.getView().setPadding(0, 0, 0, 0)
        this.getView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT
        this.getView()
            .setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
    }

    private fun setTitle(title: String): CustomSnackbar {
        val titleView = getView().findViewById<TextView>(R.id.tittleSnack)
        titleView.text = title
        return this
    }
    private fun setprice(price: Float): CustomSnackbar {
        val textView = getView().priceSnack
        textView.text = price.toString()
        return this
    }


    private fun setDescription(description: String): CustomSnackbar {
        val titleView = getView().findViewById<TextView>(R.id.descriptionSnack)
        titleView.text = description
        return this
    }
    private fun setImage(photo: Int): CustomSnackbar {
        val image = getView().findViewById<ImageView>(R.id.foodImageSnack)
        Picasso.get().load(photo).fit().into(image)
        return this
    }
    private fun setAllergens(allergens: ArrayList<Int>): CustomSnackbar {

        val image1 = getView().icon1
        val image2 = getView().icon2
        val image3 = getView().icon3
        val image4 = getView().icon4
        if (allergens.contains(0)){
            image1.visibility=View.VISIBLE
        }else {
            image1.visibility = View.GONE
        }
        if (allergens.contains(1)){
            image2.visibility=View.VISIBLE
        } else {
            image2.visibility = View.GONE
        }
        if (allergens.contains(2)){
            image3.visibility=View.VISIBLE
        } else {
            image3.visibility = View.GONE
        }
        if (allergens.contains(3)){
            image4.visibility=View.VISIBLE
        }else {
            image4.visibility = View.GONE
        }
        return this
    }

    companion object {

        /*fun make(
            view: View,
            title: Int,
            action: Int,
            duration: Int
        ): CustomSnackbar {
            return make(
                view,
                view.context.getString(title),
                view.context.getString(action)
            )
        }

        fun make(
            view: View,
            title: String,
            decription: String
        ): CustomSnackbar {
            val customSnackbar = createCustomSnackbar(view).apply {
                setTitle(title)
                setDescription(decription)
                setDuration(Snackbar.LENGTH_INDEFINITE)
                val orderButton = getView().orderButtonSnack
                orderButton.setOnClickListener {
                    Toast.makeText(orderButton.context, "texto", Toast.LENGTH_LONG).show()
                }
            }

            return customSnackbar
        }
        */
        fun makeSnackbarFood(
            view: View,food: Food): CustomSnackbar {
            val customSnackbar = createCustomSnackbar(view).apply {
                setDuration(Snackbar.LENGTH_INDEFINITE)
                setTitle(food.name)
                setDescription(food.decriptions)
                setImage(food.photo)
                setAllergens(food.allergens)
                setprice(food.price)

                val closeButton = getView().closeSnack
                closeButton.setOnClickListener {
                    App.hideFoodSnackbar()
                }


                val orderButton = getView().orderButtonSnack
                orderButton.setOnClickListener {
                    Toast.makeText(orderButton.context, "texto", Toast.LENGTH_LONG).show()
                }
            }
            customSnackbar.food=food
            return customSnackbar
        }


        private fun createCustomSnackbar(view: View): CustomSnackbar {
            val parent = findSuitableParent(view) ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            val inflater = LayoutInflater.from(view.context)
            val content = inflater.inflate(
                R.layout.snackbar_food,
                parent,
                false
            )

            val contentViewCallback =
                object : com.google.android.material.snackbar.ContentViewCallback {

                    override fun animateContentIn(delay: Int, duration: Int) {
                        /*content.alpha = 0f
                        content.animate().alpha(1f).setDuration(duration.toLong())
                            .setStartDelay(delay.toLong()).start()*/
                    }

                    override fun animateContentOut(delay: Int, duration: Int) {
                       /* content.alpha = 1f
                        content.animate().alpha(0f).setDuration(duration.toLong())
                            .setStartDelay(delay.toLong()).start()*/
                    }
                }
            return CustomSnackbar(parent, content, contentViewCallback)
        }


        private fun findSuitableParent(view: View?): ViewGroup? {
            var mView = view
            var fallback: ViewGroup? = null
            do {
                if (mView is CoordinatorLayout) {
                    // We've found a CoordinatorLayout, use it
                    return mView
                } else if (mView is FrameLayout) {
                    if (mView.id == android.R.id.content) {
                        // If we've hit the decor content view, then we didn't find a CoL in the
                        // hierarchy, so use it.
                        return mView
                    } else {
                        // It's not the content view but we'll use it as our fallback
                        fallback = mView
                    }
                }

                if (mView != null) {
                    // Else, we will loop and crawl up the view hierarchy and try to find a parent
                    val parent = mView.parent
                    mView = if (parent is View) parent else null
                }
            } while (mView != null)

            // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
            return fallback
        }
    }

}