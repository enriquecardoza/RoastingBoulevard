package com.careradish.roastingboulevard.classes



data class Address(var label:String, var address: String, var number:Int, val postalCde:String, var city:String){

    var floor:String=""
    var zoneType:String=""

}
