package com.careradish.roastingboulevard.classes

import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.tools.TranslationStrings

enum class Allergen(val pos: Int) {
    Gluten(0), Huevos(1), Lacteos(2), FrutosSecos(3), Pescado(4), Marisco(5);

    companion object{
        fun getAllergenString(pos:Int):String{
            return when(pos){
                0-> TranslationStrings.get(R.string.gluten)
                1-> TranslationStrings.get(R.string.eggs)
                2-> TranslationStrings.get(R.string.dairy)
                3-> TranslationStrings.get(R.string.nuts)
                4-> TranslationStrings.get(R.string.fish)
                5-> TranslationStrings.get(R.string.seafood)
                else-> "No text"
            }
        }
    }
}