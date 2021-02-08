package com.careradish.roastingboulevard.classes

import com.careradish.roastingboulevard.tools.App
import java.util.*

data class Delivery(
    var id: String,
    var foods: ArrayList<Food>,
    var user: User,
    var address: Address,
    var deliveredDate: Date,
    var paymentMethod: PaymentMethod,
) {
    var amount: Float = 0.0f
        get() {
            var count:Float = 0f
            for (i in foods) {
                count += i.price
            }
            return count
        }
    var createdDate: Date = Calendar.getInstance().time

    constructor(address: Address) : this(
        Calendar.getInstance().time.toString(),
        arrayListOf(),
        App.user!!,
        address,
        Calendar.getInstance().time,
        PaymentMethod()
    )
    constructor() : this(
        Calendar.getInstance().time.toString(),
        arrayListOf(),
        App.user!!,
        Address(),
        Calendar.getInstance().time,
        PaymentMethod()
    )
}
