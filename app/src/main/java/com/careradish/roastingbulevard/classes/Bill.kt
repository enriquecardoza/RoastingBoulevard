package com.careradish.roastingbulevard.classes

import java.util.*

data class Bill(
        var id:Int,
        var delivery: Delivery,
        var amount:Float,
        var date: Date,
)
