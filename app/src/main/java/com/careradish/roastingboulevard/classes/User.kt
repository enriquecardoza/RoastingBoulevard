package com.careradish.roastingboulevard.classes

import android.provider.ContactsContract

data class User(
    var id: Int,
    var name: String,
    var surname: String,
    var address: String,
    var city: String,
    var postalCode: Int,
    var email: ContactsContract.CommonDataKinds.Email,
    var password: String,
    var phone: Int,
    var paymentMethod: PaymentMethod,

    ) {
    private fun Getpassword(): String {
        return password
    }

    public fun Setpassword(newPassword: String) {
        password = newPassword
    }
}

