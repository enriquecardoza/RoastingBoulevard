package com.careradish.roastingbulevard.classes

import java.util.*
import kotlin.collections.ArrayList

data class Offer(var id:Int, var foodsToDiscount:ArrayList<Food>, var discountPercentage:Float, var endDate:Date){}
