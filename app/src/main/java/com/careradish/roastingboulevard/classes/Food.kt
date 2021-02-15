package com.careradish.roastingboulevard.classes
import com.careradish.roastingboulevard.tools.App
import java.io.Serializable

data class Food(var id:Int, var name:ArrayList<String>, var description:ArrayList<String>, var ingredients:ArrayList<String>, var allergens:ArrayList<Int>, var photo:String, var price:Float):Serializable
{

    override fun toString(): String {
        return "Food(id=$id, name='$name', decription='$description', ingredients=$ingredients, allergens=$allergens, photo=$photo, price=$price)"
    }
    constructor(food: Food):this(food.id,food.name,food.description,food.ingredients,food.allergens,food.photo,food.price){}
    constructor():this(-1,arrayListOf(),arrayListOf(),arrayListOf(), arrayListOf(),"",0f){}

     fun returnTranslatedName():String{
        return  name[App.getFoodLanguagePos(this)]
    }
    fun returnTranslatedDescription():String{
        return  description[App.getFoodLanguagePos(this)]
    }
    fun returnTranslatedIngredients():String{
        return  ingredients[App.getFoodLanguagePos(this)]
    }
}
