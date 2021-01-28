package com.careradish.roastingboulevard.classes

import android.provider.ContactsContract

data class User(
    var id: String,
    var name: String,
    var surname: String,
    var address: String,
    var city: String,
    var postalCode: Int,
    var email: String,
    var phone: Int) {

    var deliveries:MutableList<Delivery>?= mutableListOf()

    constructor():this("","","","","",-1,"",-1)

}

