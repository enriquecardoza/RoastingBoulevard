package com.careradish.roastingboulevard.tools

import android.app.Application
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.app.myapplication.CustomSnackbar
import com.careradish.roastingboulevard.classes.User
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class App : Application() {
    companion object {

        var user:User=User()
        lateinit var context: Context
        lateinit var contentView: View
        var foodSnackBar: CustomSnackbar?=null
        lateinit var tabLayout: TabLayout
        val logged:Boolean get()= user.id!=""

        fun hideFoodSnackbar() {
            if(foodSnackBar!=null) {
                foodSnackBar!!.dismiss()
                foodSnackBar=null
            }
            UnlockTabs()
        }

        fun LockTabs(){
            tabLayout.visibility=View.GONE
        }
        fun UnlockTabs(){
            tabLayout.visibility=View.VISIBLE
        }

        fun  Init(contexto: Context){
            context=contexto
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }


}

object TranslationStrings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return App.context.getString(stringRes, *formatArgs)
    }
}