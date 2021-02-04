package com.careradish.roastingboulevard.tools

import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Allergen
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Food

class DeveloperFreshData {


    companion object {


        fun getCategories():ArrayList<Category> {
            return arrayListOf(
                Category(0,TranslationStrings.getKey(R.string.snack), arrayListOf(0,1,2,3,4,5)),
                Category(1,TranslationStrings.getKey(R.string.portion), arrayListOf(6,7,8,9,10)),
                Category(2,TranslationStrings.getKey(R.string.chicken), arrayListOf(11,12,13,14)),
                Category(3,TranslationStrings.getKey(R.string.desserts), arrayListOf(15,16,17)),
            )
        }

        fun getSnacks():ArrayList<Food> {
            return arrayListOf(
                Food(
                    0,
                    TranslationStrings.getKey(R.string.croquetasJamon),
                    TranslationStrings.getKey(R.string.descript_croquetasPollo),
                    TranslationStrings.getKey(R.string.ingredientsCroquetasJamon),
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.croquetas_jamon,
                    3.0f
                ),
                Food(
                    1,
                    TranslationStrings.getKey(R.string.bolasPollo),
                    TranslationStrings.getKey(R.string.descript_bolasPollo),
                    TranslationStrings.getKey(R.string.ingredients_bolasPollo),
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.bolitas_pollo,
                    3.0f
                ),
                Food(
                    2,
                    TranslationStrings.getKey(R.string.nuggets),
                    TranslationStrings.getKey(R.string.descript_nuggets),
                    TranslationStrings.getKey(R.string.ingredients_nuggets),
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.nuggets_pollo,
                    3.0f
                ),
                Food(
                    3,
                    TranslationStrings.getKey(R.string.croquetasPollo),
                    TranslationStrings.getKey(R.string.descript_croquetasPollo),
                    TranslationStrings.getKey(R.string.ingredients_croquetasPollo),
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.croquetas_pollo,
                    3.0f
                ),
                Food(
                    4,
                    TranslationStrings.getKey(R.string.patatasFritas),
                    TranslationStrings.getKey(R.string.descript_patatasFritas),
                    TranslationStrings.getKey(R.string.ingredients_patatasFritas),
                    arrayListOf(),
                    R.mipmap.patatas_fritas,
                    1.5f
                ),
                Food(
                    5,
                    TranslationStrings.getKey(R.string.patatasPanaderas),
                    TranslationStrings.getKey(R.string.descript_patatasPanaderas),
                    TranslationStrings.getKey(R.string.ingredients_patatasPanaderas),
                    arrayListOf(),
                    R.mipmap.patatas_panaderas,
                    1.5f
                ),
            )
        }

        fun getPortions():ArrayList<Food> {
            return arrayListOf(
                Food(
                    6,
                   TranslationStrings.getKey( R.string.costillas),
                   TranslationStrings.getKey( R.string.descript_costillas),
                   TranslationStrings.getKey( R.string.ingredients_costillas),
                    arrayListOf(),
                    R.mipmap.costillas,
                    4.0f
                ),
                Food(
                    7,
                   TranslationStrings.getKey( R.string.paella),
                   TranslationStrings.getKey( R.string.descript_paella),
                   TranslationStrings.getKey( R.string.ingredients_paella),
                    arrayListOf(Allergen.Marisco.pos,Allergen.Pescado.pos),
                    R.mipmap.paella,
                    3.0f
                ),
                Food(
                    8,
                    TranslationStrings.getKey(R.string.cachopo),
                    TranslationStrings.getKey(R.string.descript_cachopo),
                    TranslationStrings.getKey(R.string.ingredients_cachopo),
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.cachopo,
                    4.0f
                ),
                Food(
                    9,
                   TranslationStrings.getKey( R.string.bacalao),
                   TranslationStrings.getKey( R.string.descript_bacalao),
                   TranslationStrings.getKey( R.string.ingredients_bacalao),
                    arrayListOf(Allergen.Pescado.pos),
                    R.mipmap.bacalao_pisto,
                    3.0f
                ),
                Food(
                    10,
                    TranslationStrings.getKey(R.string.ensaladilla),
                    TranslationStrings.getKey(R.string.descript_ensaladilla),
                    TranslationStrings.getKey(R.string.ingredients_ensaladilla),
                    arrayListOf(Allergen.Huevos.pos,Allergen.Lacteos.pos),
                    R.mipmap.ensaladilla,
                    3f
                ),
            )
        }

        fun getFood():ArrayList<Food> {
            return arrayListOf(
                Food(
                    11,
                   TranslationStrings.getKey( R.string.pollo),
                   TranslationStrings.getKey( R.string.descript_pollo),
                   TranslationStrings.getKey( R.string.ingredients_pollo),
                    arrayListOf(),
                    R.mipmap.pollo_asado,
                    8.0f
                ),
                Food(
                    12,
                    TranslationStrings.getKey(R.string.filetePollo),
                    TranslationStrings.getKey(R.string.descript_filetePollo),
                    TranslationStrings.getKey(R.string.ingredients_filetePollo),
                    arrayListOf(Allergen.Gluten.pos),
                    R.mipmap.filete_pollo,
                    3.0f
                ),
                Food(
                    13,
                    TranslationStrings.getKey(R.string.librito),
                    TranslationStrings.getKey(R.string.descript_librito),
                    TranslationStrings.getKey(R.string.ingredients_librito),
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.librito,
                    3.0f
                ),
                Food(
                    14,
                    TranslationStrings.getKey(R.string.alitas),
                    TranslationStrings.getKey(R.string.descript_alitas),
                    TranslationStrings.getKey(R.string.ingredients_alitas),
                    arrayListOf(Allergen.Gluten.pos),
                    R.mipmap.alitas_pollo,
                    3.0f
                ),
            )
        }

        fun getDessets():ArrayList<Food> {
            return arrayListOf(
                Food(
                    15,
                    TranslationStrings.getKey(R.string.macedonia),
                    TranslationStrings.getKey(R.string.descript_macedonia),
                    TranslationStrings.getKey(R.string.ingredients_macedonia),
                    arrayListOf(),
                    R.mipmap.macedonia,
                    2.0f
                ),
                Food(
                    16,
                    TranslationStrings.getKey(R.string.tartaQueso),
                    TranslationStrings.getKey(R.string.descript_tartaQueso),
                    TranslationStrings.getKey(R.string.ingredients_tartaQueso),
                    arrayListOf(Allergen.Lacteos.pos),
                    R.mipmap.tarta_queso,
                    2.0f
                ),
                Food(
                    17,
                   TranslationStrings.getKey(R.string.tartaChocolate),
                   TranslationStrings.getKey(R.string.descript_tartaChocolate),
                   TranslationStrings.getKey(R.string.ingredients_tartaChocolate),
                    arrayListOf(Allergen.Lacteos.pos),
                    R.mipmap.tarta_chocolate,
                    2.0f
                )
            )
        }

        public fun  UploadAllData(){
          for (i in getCategories()){
              FirebaseConnection.writeCategory(i)
          }
            for (i in getSnacks()){
                FirebaseConnection.writeFood(i)
            }
            for (i in getPortions()){
                FirebaseConnection.writeFood(i)
            }
            for (i in getFood()){
                FirebaseConnection.writeFood(i)
            }
            for (i in getDessets()){
                FirebaseConnection.writeFood(i)
            }
        }

    }
}