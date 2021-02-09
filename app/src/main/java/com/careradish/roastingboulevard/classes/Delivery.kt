package com.careradish.roastingboulevard.classes

import com.careradish.roastingboulevard.tools.App
import java.util.*

data class Delivery(
    var id: String?,
    var foods: ArrayList<Food>,
    var address: Address,
    var deliveredDate: String?,
    var paymentMethod: PaymentMethod,
    var deliveryState :DeliveryState,
) {
    var amount: Float = 0.0f
        get() {
            var count:Float = 0f
            for (i in foods) {
                count += i.price
            }
            return count
        }

    constructor(address: Address) : this(
        App.getToday(),
        arrayListOf(),
        address,
        App.getToday(),
        PaymentMethod(),
        DeliveryState.Delivered,
    )
    constructor() : this(
        App.getToday(),
        arrayListOf(),
        Address(),
        App.getToday(),
        PaymentMethod(),
        DeliveryState.Delivered,
    )
    enum class DeliveryState(state:Int) {
        Receveided(0), Cooking(1),Distribution(2),Delivered(3);
    }
}
