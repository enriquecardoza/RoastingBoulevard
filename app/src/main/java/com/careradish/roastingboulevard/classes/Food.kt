package com.careradish.roastingboulevard.classes
import java.io.Serializable

data class Food(var id:Int, var name:Int, var decription:Int, var ingredients:Int, var allergens:ArrayList<Int>, var photo:Int, var price:Float):Serializable
{

    override fun toString(): String {
        return "Food(id=$id, name='$name', decription='$decription', ingredients=$ingredients, allergens=$allergens, photo=$photo, price=$price)"
    }
    constructor(food: Food):this(food.id,food.name,food.decription,food.ingredients,food.allergens,food.photo,food.price){}
    constructor():this(-1,-1,-1,-1, arrayListOf(),0,0f){}

}
