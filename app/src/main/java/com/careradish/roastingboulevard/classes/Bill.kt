package com.careradish.roastingboulevard.classes

import java.util.*

data class Bill(
    var id:Int,
    var delivery: Delivery,
    var amount:Float,
    var date: Date,
)
