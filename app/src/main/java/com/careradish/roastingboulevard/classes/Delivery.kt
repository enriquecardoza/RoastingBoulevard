package com.careradish.roastingboulevard.classes

import com.careradish.roastingboulevard.tools.Tools
import java.util.*

data class Delivery(
    var id: String?,
    var foods: ArrayList<Food>,
    var amountsOfFoods: ArrayList<Int>,
    var address: Address,
    var deliveredDate: String?,
    var paymentMethod: PaymentMethod,
    var deliveryState: DeliveryState,
) {
    val totalPrice: Float
        get() {
            var count: Float = 0f
            for (i in foods) {
                count += i.price
            }
            return count
        }

    constructor(address: Address) : this(
        id = Tools.getToday(),
        foods = arrayListOf(),
        amountsOfFoods = arrayListOf(),
        address = address,
        deliveredDate = Tools.getToday(),
        paymentMethod = PaymentMethod(),
        deliveryState = DeliveryState.received,
    )

    constructor() : this(
        Tools.getToday(),
        arrayListOf(),
        arrayListOf(),
        Address(),
        Tools.getToday(),
        PaymentMethod(),
        DeliveryState.received,
    )

    enum class DeliveryState(val state: Int) {
        received(0), cooking(1), distribution(2), delivered(3);
    }
}
