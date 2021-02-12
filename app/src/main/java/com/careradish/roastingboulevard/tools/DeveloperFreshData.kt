package com.careradish.roastingboulevard.tools

import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Allergen
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Food


class DeveloperFreshData {


    companion object {


        fun getCategories():ArrayList<Category> {
            return arrayListOf(
                Category(
                    0, TranslationStrings.getTranslationsStrings(R.string.snack), arrayListOf(
                        0,
                        1,
                        2,
                        3,
                        4,
                        5
                    )
                ),
                Category(
                    1, TranslationStrings.getTranslationsStrings(R.string.portion), arrayListOf(
                        6,
                        7,
                        8,
                        9,
                        10
                    )
                ),
                Category(
                    2, TranslationStrings.getTranslationsStrings(R.string.chicken), arrayListOf(
                        11,
                        12,
                        13,
                        14
                    )
                ),
                Category(
                    3, TranslationStrings.getTranslationsStrings(R.string.desserts), arrayListOf(
                        15,
                        16,
                        17
                    )
                ),
            )
        }

        fun getSnacks():ArrayList<Food> {
            return arrayListOf(
                Food(
                    0,
                    TranslationStrings.getTranslationsStrings(R.string.croquetasJamon),
                    TranslationStrings.getTranslationsStrings(R.string.descript_croquetasPollo),
                    TranslationStrings.getTranslationsStrings(R.string.ingredientsCroquetasJamon),
                    arrayListOf(Allergen.Lacteos.pos, Allergen.Lacteos.pos, Allergen.Gluten.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.croquetas_jamon
                    )!!,
                    3.0f
                ),
                Food(
                    1,
                    TranslationStrings.getTranslationsStrings(R.string.bolasPollo),
                    TranslationStrings.getTranslationsStrings(R.string.descript_bolasPollo),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_bolasPollo),
                    arrayListOf(Allergen.Lacteos.pos, Allergen.Gluten.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.bolitas_pollo)!!,
                    3.0f
                ),
                Food(
                    2,
                    TranslationStrings.getTranslationsStrings(R.string.nuggets),
                    TranslationStrings.getTranslationsStrings(R.string.descript_nuggets),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_nuggets),
                    arrayListOf(Allergen.Lacteos.pos, Allergen.Gluten.pos),
                    Tools.getFullResourceNameByID(
                         R.mipmap.nuggets_pollo)!!,
                    3.0f
                ),
                Food(
                    3,
                    TranslationStrings.getTranslationsStrings(R.string.croquetasPollo),
                    TranslationStrings.getTranslationsStrings(R.string.descript_croquetasPollo),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_croquetasPollo),
                    arrayListOf(Allergen.Lacteos.pos, Allergen.Gluten.pos),
                    Tools.getFullResourceNameByID(
                         R.mipmap.croquetas_pollo)!!,
                    3.0f
                ),
                Food(
                    4,
                    TranslationStrings.getTranslationsStrings(R.string.patatasFritas),
                    TranslationStrings.getTranslationsStrings(R.string.descript_patatasFritas),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_patatasFritas),
                    arrayListOf(),
                    Tools.getFullResourceNameByID(
                         R.mipmap.patatas_fritas)!!,
                    1.5f
                ),
                Food(
                    5,
                    TranslationStrings.getTranslationsStrings(R.string.patatasPanaderas),
                    TranslationStrings.getTranslationsStrings(R.string.descript_patatasPanaderas),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_patatasPanaderas),
                    arrayListOf(),
                    Tools.getFullResourceNameByID(
                         R.mipmap.patatas_panaderas)!!,
                    1.5f
                ),
            )
        }

        fun getPortions():ArrayList<Food> {
            return arrayListOf(
                Food(
                    6,
                    TranslationStrings.getTranslationsStrings(R.string.costillas),
                    TranslationStrings.getTranslationsStrings(R.string.descript_costillas),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_costillas),
                    arrayListOf(),
                    Tools.getFullResourceNameByID(
                         R.mipmap.costillas)!!,
                    4.0f
                ),
                Food(
                    7,
                    TranslationStrings.getTranslationsStrings(R.string.paella),
                    TranslationStrings.getTranslationsStrings(R.string.descript_paella),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_paella),
                    arrayListOf(Allergen.Marisco.pos, Allergen.Pescado.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.paella)!!,
                    3.0f
                ),
                Food(
                    8,
                    TranslationStrings.getTranslationsStrings(R.string.cachopo),
                    TranslationStrings.getTranslationsStrings(R.string.descript_cachopo),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_cachopo),
                    arrayListOf(Allergen.Lacteos.pos, Allergen.Gluten.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.cachopo)!!,
                    4.0f
                ),
                Food(
                    9,
                    TranslationStrings.getTranslationsStrings(R.string.bacalao),
                    TranslationStrings.getTranslationsStrings(R.string.descript_bacalao),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_bacalao),
                    arrayListOf(Allergen.Pescado.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.bacalao_pisto)!!,
                    3.0f
                ),
                Food(
                    10,
                    TranslationStrings.getTranslationsStrings(R.string.ensaladilla),
                    TranslationStrings.getTranslationsStrings(R.string.descript_ensaladilla),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_ensaladilla),
                    arrayListOf(Allergen.Huevos.pos, Allergen.Lacteos.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.ensaladilla)!!,
                    3f
                ),
            )
        }

        fun getFood():ArrayList<Food> {
            return arrayListOf(
                Food(
                    11,
                    TranslationStrings.getTranslationsStrings(R.string.pollo),
                    TranslationStrings.getTranslationsStrings(R.string.descript_pollo),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_pollo),
                    arrayListOf(),
                    Tools.getFullResourceNameByID(
                        R.mipmap.pollo_asado)!!,
                    8.0f
                ),
                Food(
                    12,
                    TranslationStrings.getTranslationsStrings(R.string.filetePollo),
                    TranslationStrings.getTranslationsStrings(R.string.descript_filetePollo),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_filetePollo),
                    arrayListOf(Allergen.Gluten.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.filete_pollo)!!,
                    3.0f
                ),
                Food(
                    13,
                    TranslationStrings.getTranslationsStrings(R.string.librito),
                    TranslationStrings.getTranslationsStrings(R.string.descript_librito),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_librito),
                    arrayListOf(Allergen.Lacteos.pos, Allergen.Gluten.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.librito)!!,
                    3.0f
                ),
                Food(
                    14,
                    TranslationStrings.getTranslationsStrings(R.string.alitas),
                    TranslationStrings.getTranslationsStrings(R.string.descript_alitas),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_alitas),
                    arrayListOf(Allergen.Gluten.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.alitas_pollo)!!,
                    3.0f
                ),
            )
        }

        fun getDessets():ArrayList<Food> {
            return arrayListOf(
                Food(
                    15,
                    TranslationStrings.getTranslationsStrings(R.string.macedonia),
                    TranslationStrings.getTranslationsStrings(R.string.descript_macedonia),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_macedonia),
                    arrayListOf(),
                    Tools.getFullResourceNameByID(
                        R.mipmap.macedonia)!!,
                    2.0f
                ),
                Food(
                    16,
                    TranslationStrings.getTranslationsStrings(R.string.tartaQueso),
                    TranslationStrings.getTranslationsStrings(R.string.descript_tartaQueso),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_tartaQueso),
                    arrayListOf(Allergen.Lacteos.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.tarta_queso)!!,
                    2.0f
                ),
                Food(
                    17,
                    TranslationStrings.getTranslationsStrings(R.string.tartaChocolate),
                    TranslationStrings.getTranslationsStrings(R.string.descript_tartaChocolate),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_tartaChocolate),
                    arrayListOf(Allergen.Lacteos.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.tarta_chocolate)!!,
                    2.0f
                )
            )
        }
        fun getCombos():ArrayList<Food> {
            return arrayListOf(
                Food(
                    18,
                    TranslationStrings.getTranslationsStrings(R.string.macedonia),
                    TranslationStrings.getTranslationsStrings(R.string.descript_macedonia),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_macedonia),
                    arrayListOf(),
                    Tools.getFullResourceNameByID(
                        R.mipmap.macedonia)!!,
                    9.0f
                ),
                Food(
                    19,
                    TranslationStrings.getTranslationsStrings(R.string.tartaQueso),
                    TranslationStrings.getTranslationsStrings(R.string.descript_tartaQueso),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_tartaQueso),
                    arrayListOf(Allergen.Lacteos.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.tarta_queso)!!,
                    7f
                ),
                Food(
                    20,
                    TranslationStrings.getTranslationsStrings(R.string.tartaChocolate),
                    TranslationStrings.getTranslationsStrings(R.string.descript_tartaChocolate),
                    TranslationStrings.getTranslationsStrings(R.string.ingredients_tartaChocolate),
                    arrayListOf(Allergen.Lacteos.pos),
                    Tools.getFullResourceNameByID(
                        R.mipmap.tarta_chocolate)!!,
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