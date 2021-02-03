package com.careradish.roastingboulevard.tools

import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Allergen
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Food

class DeveloperFreshData {


    companion object {


        fun getCategories():ArrayList<Category> {
            return arrayListOf(
                Category(0,R.string.snack, arrayListOf(0,1,2,3,4,5)),
                Category(1,R.string.portion, arrayListOf(6,7,8,9,10)),
                Category(2,R.string.chicken, arrayListOf(11,12,13,14)),
                Category(3,R.string.desserts, arrayListOf(15,16,17)),
            )
        }

        fun getSnacks():ArrayList<Food> {
            return arrayListOf(
                Food(
                    0,
                    R.string.croquetasJamon,
                    R.string.descript_croquetasPollo,
                    R.string.ingredientsCroquetasJamon,
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.croquetas_jamon,
                    3.0f
                ),
                Food(
                    1,
                    R.string.bolasPollo,
                    R.string.descript_bolasPollo,
                    R.string.ingredients_bolasPollo,
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.bolitas_pollo,
                    3.0f
                ),
                Food(
                    2,
                    R.string.nuggets,
                    R.string.descript_nuggets,
                    R.string.ingredients_nuggets,
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.nuggets_pollo,
                    3.0f
                ),
                Food(
                    3,
                    R.string.croquetasPollo,
                    R.string.descript_croquetasPollo,
                    R.string.ingredients_croquetasPollo,
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.croquetas_pollo,
                    3.0f
                ),
                Food(
                    4,
                    R.string.patatasFritas,
                    R.string.descript_patatasFritas,
                    R.string.ingredients_patatasFritas,
                    arrayListOf(),
                    R.mipmap.patatas_fritas,
                    1.5f
                ),
                Food(
                    5,
                    R.string.patatasPanaderas,
                    R.string.descript_patatasPanaderas,
                    R.string.ingredients_patatasPanaderas,
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
                    R.string.costillas,
                    R.string.descript_costillas,
                    R.string.ingredients_costillas,
                    arrayListOf(),
                    R.mipmap.costillas,
                    4.0f
                ),
                Food(
                    7,
                    R.string.paella,
                    R.string.descript_paella,
                    R.string.ingredients_paella,
                    arrayListOf(Allergen.Marisco.pos,Allergen.Pescado.pos),
                    R.mipmap.paella,
                    3.0f
                ),
                Food(
                    8,
                    R.string.cachopo,
                    R.string.descript_cachopo,
                    R.string.ingredients_cachopo,
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.cachopo,
                    4.0f
                ),
                Food(
                    9,
                    R.string.bacalao,
                    R.string.descript_bacalao,
                    R.string.ingredients_bacalao,
                    arrayListOf(Allergen.Pescado.pos),
                    R.mipmap.bacalao_pisto,
                    3.0f
                ),
                Food(
                    10,
                    R.string.ensaladilla,
                    R.string.descript_ensaladilla,
                    R.string.ingredients_ensaladilla,
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
                    R.string.pollo,
                    R.string.descript_pollo,
                    R.string.ingredients_pollo,
                    arrayListOf(),
                    R.mipmap.pollo_asado,
                    8.0f
                ),
                Food(
                    12,
                    R.string.filetePollo,
                    R.string.descript_filetePollo,
                    R.string.ingredients_filetePollo,
                    arrayListOf(Allergen.Gluten.pos),
                    R.mipmap.filete_pollo,
                    3.0f
                ),
                Food(
                    13,
                    R.string.librito,
                    R.string.descript_librito,
                    R.string.ingredients_librito,
                    arrayListOf(Allergen.Lacteos.pos,Allergen.Gluten.pos),
                    R.mipmap.librito,
                    3.0f
                ),
                Food(
                    14,
                    R.string.alitas,
                    R.string.descript_alitas,
                    R.string.ingredients_alitas,
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
                    R.string.macedonia,
                    R.string.descript_macedonia,
                    R.string.ingredients_macedonia,
                    arrayListOf(),
                    R.mipmap.macedonia,
                    2.0f
                ),
                Food(
                    16,
                    R.string.tartaQueso,
                    R.string.descript_tartaQueso,
                    R.string.ingredients_tartaQueso,
                    arrayListOf(Allergen.Lacteos.pos),
                    R.mipmap.tarta_queso,
                    2.0f
                ),
                Food(
                    17,
                    R.string.tartaChocolate,
                    R.string.descript_tartaChocolate,
                    R.string.ingredients_tartaChocolate,
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