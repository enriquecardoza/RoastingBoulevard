package com.careradish.roastingboulevard.tools

import com.careradish.roastingboulevard.classes.Food
import com.google.firebase.database.*

class FirebaseConnection {

    private var database:FirebaseDatabase
    var referenceRoot:DatabaseReference

    init {
        database= FirebaseDatabase.getInstance()
        referenceRoot = database.getReference("")
    }

    public fun writeFood(food: Food){

        referenceRoot.child(foodsTittle).child(food.id.toString()).setValue(food)

    }
    public fun readFood(id: Int): Food? {

        var food: Food = Food()
        val foodRef = referenceRoot.child(foodsTittle).child(id.toString())
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                food = snapshot.getValue(Food::class.java) as Food

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return food
    }

    companion object {
        const val foodsTittle:String="Foods"
    }

}