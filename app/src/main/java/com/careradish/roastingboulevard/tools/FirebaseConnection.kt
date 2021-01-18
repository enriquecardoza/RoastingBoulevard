package com.careradish.roastingboulevard.tools

import android.content.Context
import android.widget.Toast
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.classes.Food
import com.google.firebase.database.*

class FirebaseConnection (var context: Context) {

    private var database:FirebaseDatabase
    var referenceRoot:DatabaseReference
    lateinit var activity:MainActivity
    init {
        database= FirebaseDatabase.getInstance()
        referenceRoot = database.getReference("")
    }
    companion object {
        const val foodsTittle:String="Foods"
    }


    public fun writeFood(food: Food){

        referenceRoot.child(foodsTittle).child(food.id.toString()).setValue(food)

    }
    public fun readFood(id: Int): Food? {

        var food: Food = Food()
        val foodRef = referenceRoot.child(foodsTittle).child(id.toString())
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                var food: Food? =snapshot.getValue(Food::class.java)
                showToast(food?.name.toString());
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return food
    }

    public fun getDishes(): List<Food>  {
        val Lista: MutableList<Food> = ArrayList()
        referenceRoot.child(foodsTittle).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    Lista.add(i.getValue(Food::class.java)!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return Lista
    }





    public fun showToast(text:String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}





/*myRef.addChildEventListener(object : ChildEventListener {

    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        Toast.makeText(name.context, ss, Toast.LENGTH_SHORT).show()
    }

    override fun onChildChanged(
        snapshot: DataSnapshot,
        previousChildName: String?
    ) {
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {}
    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
    override fun onCancelled(error: DatabaseError) {}
})*/


