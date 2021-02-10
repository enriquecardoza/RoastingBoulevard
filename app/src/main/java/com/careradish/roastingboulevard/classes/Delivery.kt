package com.careradish.roastingboulevard.classes

import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.Tools
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
        Tools.getToday(),
        arrayListOf(),
        address,
        Tools.getToday(),
        PaymentMethod(),
        DeliveryState.Delivered,
    )
    constructor() : this(
        Tools.getToday(),
        arrayListOf(),
        Address(),
        Tools.getToday(),
        PaymentMethod(),
        DeliveryState.Delivered,
    )
    enum class DeliveryState(state:Int) {
        Receveided(0), Cooking(1),Distribution(2),Delivered(3);
    }
}
