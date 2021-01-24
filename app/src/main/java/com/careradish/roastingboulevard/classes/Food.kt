package com.careradish.roastingboulevard.classes
import java.io.Serializable

data class Food(var id:Int, var name:String, var decriptions:String, var ingredients:ArrayList<String>, var allergens:ArrayList<Int>, var photo:Int, var price:Float):Serializable
{

    override fun toString(): String {
        return "Food(id=$id, name='$name', decriptions='$decriptions', ingredients=$ingredients, allergens=$allergens, photo=$photo, price=$price)"
    }
    constructor(food: Food):this(food.id,food.name,food.decriptions,food.ingredients,food.allergens,food.photo,food.price){}
    constructor():this(-1,"","",arrayListOf(), arrayListOf(),0,0f){}



}
