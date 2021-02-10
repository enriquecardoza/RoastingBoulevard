package com.careradish.roastingboulevard.classes

data class User(
    var id: String,
    var name: String,
    var surname: String,
    var email: String,
    var phone: Int?) {

    var deliveries:MutableList<Delivery>?= mutableListOf()
    var addresses:MutableList<Address>?= mutableListOf()


    constructor():this("","","","",null)

}

