package com.careradish.roastingboulevard.classes



data class Address(var label:String, var address: String, var number:Int,var zoneType:String, val postalCode:Int, var city:String){
    constructor():this("","",-1,"",-1,""){}

}
