package com.careradish.roastingboulevard.tools

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.classes.Address
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.classes.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FirebaseConnection(var context: Context) {

    init {
        referenceRoot = database.getReference("")
    }

    companion object {
        var auth = FirebaseAuth.getInstance()
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var referenceRoot: DatabaseReference = database.getReference("")


//region user

        fun writeUser(user: User) {

            referenceRoot.child(Constants.usersTittle).child(user.id.toString()).setValue(user)

        }

        fun LoginUser(
            email: String, password: String,
            activity: Activity,
            loginSuccess: (() -> Unit)? = null,
            loginFail: (() -> Unit)? = null
        ) {
            auth?.signInWithEmailAndPassword(email, password)!!
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        if (loginSuccess != null) {
                            val userId = auth!!.currentUser?.uid.toString()
                            App.user!!.id = userId
                            loginSuccess()
                        }

                    } else {
                        Toast.makeText(
                            activity.applicationContext,
                            TranslationStrings.get(R.string.errorLogin),
                            Toast.LENGTH_SHORT
                        ).show()
                        if (loginFail != null) {
                            loginFail()
                        }
                    }
                }
        }

        fun createUser(
            user: User, password: String,
            activity: Activity,
            createSuccess: () -> Unit,
            createFail: (() -> Unit)? = null
        ) {
            auth?.createUserWithEmailAndPassword(user.email, password)
                ?.addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            activity.applicationContext,
                            TranslationStrings.get(R.string.registered),
                            Toast.LENGTH_SHORT
                        ).show()


                        LoginUser(user.email, password, activity, {
                            createSuccess()
                            val userId = auth!!.currentUser?.uid.toString()
                            App.user = user
                            App.user!!.id = userId
                            writeUser(App.user!!)
                        })

                    } else {
                        Toast.makeText(
                            App.context,
                            "Error",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (createFail != null) {
                            createFail()
                        }
                    }

                }
        }

        fun readUser(
            userId: String,
            readSucces: (() -> Unit)? = null,
            readFail: (() -> Unit)? = null
        ) {

            val ref = referenceRoot.child(Constants.usersTittle).child(userId)
            ref.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (readSucces != null) {
                        readSucces()
                    }
                    App.user = snapshot.getValue(User::class.java)!!
                }

                override fun onCancelled(error: DatabaseError) {
                    if (readFail != null) {
                        readFail()
                    }
                }
            })
        }

//endregion

//region address

        fun eraseAddress(
            address: Address,
            success: (() -> Unit)? = null,
            fail: (() -> Unit)? = null
        ) {
            val task = referenceRoot.child(Constants.usersTittle).child(App.user.id)
                .child(Constants.addressTittle).child(address.label).removeValue()
            task.addOnCompleteListener {
                if (task.isSuccessful) {
                    if (success != null) {
                        success()
                    }

                } else {
                    if (fail != null) {
                        fail()
                    }
                }
            }
        }

        fun writeAddress(
            address: Address,
            success: (() -> Unit)? = null,
            fail: (() -> Unit)? = null
        ) {

            val task = referenceRoot.child(Constants.usersTittle).child(App.user.id)
                .child(Constants.addressTittle).child(address.label).setValue(address)
            task.addOnCompleteListener {
                if (task.isSuccessful) {
                    if (success != null) {
                        success()
                    }

                } else {
                    if (fail != null) {
                        fail()
                    }
                }
            }

        }

        fun loadAddresses(readSucces: (() -> Unit)? = null, readFail: (() -> Unit)? = null) {

            val ref = referenceRoot.child(Constants.usersTittle).child(App.user.id)
                .child(Constants.addressTittle)
            ref.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    App.user.addresses?.clear()
                    if (readSucces != null) {
                        for (i in snapshot.children) {
                            var address: Address = i.getValue(Address::class.java)!!
                            App.user.addresses?.add(address)
                        }
                        readSucces()
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    if (readFail != null) {
                        readFail()
                    }
                }
            })
        }


        //endregion


        //region Food
        fun writeFood(food: Food) {

            referenceRoot.child(Constants.foodsTittle).child(food.id.toString()).setValue(food)

        }
//endregion

        //region Food
        fun writeCategory(category: Category) {

            referenceRoot.child(Constants.categoryTittle).child(category.id.toString())
                .setValue(category)

        }
//endregion

    }


    public fun readFood(id: Int): Food? {

        var food: Food = Food()
        val foodRef = referenceRoot.child(Constants.foodsTittle).child(id.toString())
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                var food: Food? = snapshot.getValue(Food::class.java)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return food
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


