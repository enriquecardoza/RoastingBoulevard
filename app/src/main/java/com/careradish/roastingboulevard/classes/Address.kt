package com.careradish.roastingboulevard.classes

import java.io.Serializable


data class Address(var label:String, var address: String, var number:Int,var zoneType:String, val postalCode:Int, var city:String):Serializable{
    constructor():this("","",-1,"",-1,""){}

     fun simpleString(): String {
        return "$address $number $zoneType"
    }
}
