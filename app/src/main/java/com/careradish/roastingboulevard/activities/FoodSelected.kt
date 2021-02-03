package com.careradish.roastingboulevard.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Allergen
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Constants
import com.careradish.roastingboulevard.tools.TranslationStrings
import kotlinx.android.synthetic.main.activity_food_selected.*

class FoodSelected : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_selected)

        val food=intent.getSerializableExtra(Constants.selectedFood) as Food
        foodSelectedTittle.text=TranslationStrings.get(food.name)
        foodSelectedDescription.text=TranslationStrings.get(food.decription)
        foodSelectedIngredients.text=TranslationStrings.get(food.ingredients)

        var allergens:String=""

        for (i in 0 until food.allergens.count()-2)
            allergens+=Allergen.getAllergenString(i)+" ,"
        allergens+=Allergen.getAllergenString(food.allergens.count()-1)
        foodSelectedAllergens.text=allergens

        orderSelectedFood.setOnClickListener {
            App.actualDelivery!!.foods.add(food)
            MainActivity.setVisibleSeeOrderButton()
            val int=Intent(this,MainActivity::class.java)
            startActivity(int)
        }
    }
}