package com.careradish.roastingboulevard.classes

import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.tools.TranslationStrings

data class PaymentMethod(
    var method: Method,
    var accountNumber:Int,
    var creditCardNumber:Int,
) {
    constructor():this(Method.Cash,-1,-1)
    enum class Method(name:String) {
        MasterCard("MasterCard"), Visa("VISA"),Cash(TranslationStrings.get(R.string.cash))
    }

    companion object {
        fun getArrMethods():ArrayList<Method>{
            return  arrayListOf(Method.Cash,Method.MasterCard,Method.Visa)
        }
    }
}