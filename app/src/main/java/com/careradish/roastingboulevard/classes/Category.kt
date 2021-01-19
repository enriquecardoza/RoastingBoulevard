package com.careradish.roastingboulevard.classes

data class Category(var id:Int,var name:String,var foods:ArrayList<Food>){


    constructor(category: Category):this(category.id,category.name,category.foods){}
    constructor():this(-1,"",arrayListOf()){}
}
