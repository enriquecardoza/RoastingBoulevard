package com.careradish.roastingboulevard.classes

import android.provider.ContactsContract

data class User(
    var id: String,
    var name: String,
    var surname: String,
    var email: String,
    var phone: Int) {

    var deliveries:MutableList<Delivery>?= mutableListOf()
    var paymentMethods:PaymentMethod?=PaymentMethod()

    constructor():this("","","","",-1)

}

