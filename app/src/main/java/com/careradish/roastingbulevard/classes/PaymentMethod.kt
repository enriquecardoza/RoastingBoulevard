package com.careradish.roastingbulevard.classes

data class PaymentMethod(
        var id:Int,
        var method:Method,
        var accountNumber:Int,
        var creditCardNumber:Int,
)

enum class Method {
    A, B, C, D
}