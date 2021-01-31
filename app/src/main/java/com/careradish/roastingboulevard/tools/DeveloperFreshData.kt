package com.careradish.roastingboulevard.tools

import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Food

class DeveloperFreshData {


    companion object {



        fun getFood():ArrayList<Food> {
            return arrayListOf(
                Food(
                    0,
                    R.string.croquetasJamon,
                    R.string.descript_croquetasPollo,
                    R.string.ingredientsCroquetasJamon,
                    arrayListOf(Food.Allergens.Leche.pos,Food.Allergens.Gluten.pos),
                    0,
                    3.0f
                ),
                Food(
                    1,
                    R.string.bolasPollo,
                    R.string.descript_bolasPollo,
                    R.string.ingredientsbolasPollo,
                    arrayListOf(Food.Allergens.Leche.pos,Food.Allergens.Gluten.pos),
                    0,
                    3.0f
                ),
            )
        }
    }
}