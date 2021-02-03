package com.careradish.roastingboulevard.classes

import com.careradish.roastingboulevard.tools.App
import java.util.*
import kotlin.collections.ArrayList

data class Delivery(
    var id: Int,
    var foods: ArrayList<Food>,
    var user: User,
    var direction: Address,
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
        -1,
        arrayListOf(),
        App.user,
        address,
        Calendar.getInstance().time,
        PaymentMethod()
    )
}
