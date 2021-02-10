package com.careradish.roastingboulevard.classes

data class User(
    var id: String,
    var name: String,
    var surname: String,
    var email: String,
    var phone: Int?) {

    var deliveries:HashMap<String,Delivery>?= hashMapOf()
    var addresses:MutableList<Address>?= mutableListOf()


    constructor():this("","","","",null)

}

