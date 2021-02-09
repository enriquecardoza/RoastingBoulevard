package com.careradish.roastingboulevard.classes

import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.tools.TranslationStrings
import java.util.*
import kotlin.collections.ArrayList

data class PaymentMethod(
    var method: Method
) {
    var creditCardNumber: Int? =null
    var expirationDate: String? =null
    var cvv:Int?=null

    constructor():this(Method.Cash)
    enum class Method(name:String) {
        MasterCard("MasterCard"), Visa("VISA"),Cash("TranslationStrings.get(R.string.cash");
    }

    companion object {
        fun getArrMethods():ArrayList<Method>{
            return  arrayListOf(Method.Cash,Method.MasterCard,Method.Visa)
        }
        fun getArrMethodsString():ArrayList<String>{
            return  arrayListOf(TranslationStrings.get(R.string.cash),Method.MasterCard.name,Method.Visa.name)
        }
    }
}