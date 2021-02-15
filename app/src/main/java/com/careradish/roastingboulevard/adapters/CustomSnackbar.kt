package com.careradish.roastingboulevard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.classes.Allergen
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.FirebaseConnection
import com.careradish.roastingboulevard.tools.Tools
import com.careradish.roastingboulevard.tools.TranslationStrings
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.snackbar_food.view.*


class CustomSnackbar(
    parent: ViewGroup,
    content: View,
    contentViewCallback: com.google.android.material.snackbar.ContentViewCallback
) : BaseTransientBottomBar<CustomSnackbar>(parent, content, contentViewCallback) {

    lateinit var food: Food

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
        textView.text = price.toString() + "€"
        return this
    }


    private fun setDescription(description: String): CustomSnackbar {
        val titleView = getView().findViewById<TextView>(R.id.descriptionSnack)
        titleView.text = description
        return this
    }

    private fun setImage(photo: String): CustomSnackbar {
        val image = getView().findViewById<ImageView>(R.id.foodImageSnack)
        //Picasso.get().load(photo).fit().centerCrop().into(image)

        FirebaseConnection.getImageUri(photo, {
            Picasso.get().load(it).fit().centerCrop().into(image)
        }
        )
        return this
    }

    private fun setAllergens(allergens: ArrayList<Int>): CustomSnackbar {

        val image1 = getView().icon1
        val image2 = getView().icon2
        val image3 = getView().icon3
        val image4 = getView().icon4
        val image5 = getView().icon5
        val image6 = getView().icon6

        if (allergens.contains(Allergen.Gluten.pos)) {
            image1.visibility = View.VISIBLE
        } else {
            image1.visibility = View.GONE
        }
        if (allergens.contains(Allergen.Huevos.pos)) {
            image2.visibility = View.VISIBLE
        } else {
            image2.visibility = View.GONE
        }
        if (allergens.contains(Allergen.Lacteos.pos)) {
            image3.visibility = View.VISIBLE
        } else {
            image3.visibility = View.GONE
        }
        if (allergens.contains(Allergen.FrutosSecos.pos)) {
            image4.visibility = View.VISIBLE
        } else {
            image4.visibility = View.GONE
        }
        if (allergens.contains(Allergen.Pescado.pos)) {
            image5.visibility = View.VISIBLE
        } else {
            image5.visibility = View.GONE
        }
        if (allergens.contains(Allergen.Marisco.pos)) {
            image6.visibility = View.VISIBLE
        } else {
            image6.visibility = View.GONE
        }
        return this
    }

    companion object {

        fun makeSnackbarFood(
            view: View, food: Food
        ): CustomSnackbar {
            val customSnackbar = createCustomSnackbar(view).apply {
                setDuration(Snackbar.LENGTH_INDEFINITE)
                setTitle(food.returnTranslatedName())
                setDescription(food.returnTranslatedDescription())
                setImage(food.photo)
                setAllergens(food.allergens)
                setprice(food.price)

                val closeButton = getView().closeSnack
                closeButton.setOnClickListener {
                    App.hideFoodSnackbar()
                }

                MainActivity.LockTabs()
                val orderButton = getView().orderButtonSnack
                orderButton.setOnClickListener {

                    if (!App.logged) {
                        MainActivity.changueSelectedTab(2)
                        Toast.makeText(
                            orderButton.context,
                            TranslationStrings.get(R.string.please_login_register),
                            Toast.LENGTH_LONG
                        ).show()
                        App.hideFoodSnackbar()
                    } else {
                        App.hideFoodSnackbar()
                        if (App.actualDelivery == null) {
                            App.actualDelivery = Delivery()
                            MainActivity.showOrderButton()
                        }

                        if ( App.actualDelivery!!.foods.contains(food)) {
                            val pos=  App.actualDelivery!!.foods.indexOf(food)
                            App.actualDelivery!!.amountsOfFoods[pos]+=1
                        }
                            else {
                            App.actualDelivery!!.foods.add(food)
                            App.actualDelivery!!.amountsOfFoods.add(1)
                        }
                        MainActivity.showToast(TranslationStrings.get(R.string.added_cart,food.returnTranslatedName()))
                    }

                }
            }
            customSnackbar.food = food
            return customSnackbar
        }


        private fun createCustomSnackbar(view: View): CustomSnackbar {
            val parent = Tools.findSuitableParent(view) ?: throw IllegalArgumentException(
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


    }

}