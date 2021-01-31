package com.careradish.roastingboulevard.classes
import java.io.Serializable

data class Food(var id:Int, var name:Int, var decriptions:Int, var ingredients:Int, var allergens:ArrayList<Int>, var photo:Int, var price:Float):Serializable
{

    override fun toString(): String {
        return "Food(id=$id, name='$name', decriptions='$decriptions', ingredients=$ingredients, allergens=$allergens, photo=$photo, price=$price)"
    }
    constructor(food: Food):this(food.id,food.name,food.decriptions,food.ingredients,food.allergens,food.photo,food.price){}
    constructor():this(-1,-1,-1,-1, arrayListOf(),0,0f){}


    enum class Allergens(val pos: Int) {
        Gluten(0), Huevos(1), Leche(2), FrutosSecos(3), Pescado(4), Marisco(5)
    }

}
