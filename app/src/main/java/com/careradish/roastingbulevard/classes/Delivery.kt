package com.careradish.roastingbulevard.classes

import java.util.*
import kotlin.collections.ArrayList

data class Delivery(
        var id:Int,
        var foods: ArrayList<Food>,
        var user: User,
        var bill: Bill,
        var direction:String,
        var createdDate: Date,
        var deliveryDate: Date,
        var amount:Float,
        var paymentMethod: PaymentMethod,
        )
