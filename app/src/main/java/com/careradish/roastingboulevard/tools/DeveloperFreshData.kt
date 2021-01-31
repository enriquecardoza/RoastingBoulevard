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
                    arrayListOf(),
                    0,
                    2.0f
                ),
            )
        }
    }
}