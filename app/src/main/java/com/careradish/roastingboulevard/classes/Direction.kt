package com.careradish.roastingboulevard.classes



data class Direction(var address: String,var number:Int,val postalCde:String,var city:String){

    var floor:String?=""
    var zoneType:ZoneType?=ZoneType.None

    enum class ZoneType {
        None,Chalet,Apartment, Street
    }
}
