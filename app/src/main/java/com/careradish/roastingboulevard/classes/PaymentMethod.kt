package com.careradish.roastingboulevard.classes

data class PaymentMethod(
    var method: Method,
    var accountNumber:Int,
    var creditCardNumber:Int,
) {
    constructor():this(Method.None,-1,-1)
    enum class Method {
        None,MasterCard, Visa,Paypal,Cash
    }

}