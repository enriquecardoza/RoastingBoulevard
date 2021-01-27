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
    var phone: Int,
    var password: String,

    ) {
    private fun Getpassword(): String {
        return password
    }

    public fun Setpassword(newPassword: String) {
        password = newPassword
    }
}

