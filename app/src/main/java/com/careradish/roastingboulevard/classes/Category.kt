package com.careradish.roastingboulevard.classes

import java.io.Serializable

data class Category(var id:Int,var name:String,var foods:ArrayList<Int>):Serializable{


    constructor(category: Category):this(category.id,category.name,category.foods){}
    constructor():this(-1,"",arrayListOf()){}
}
