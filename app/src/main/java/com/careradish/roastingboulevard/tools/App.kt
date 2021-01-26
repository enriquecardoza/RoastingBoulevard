package com.careradish.roastingboulevard.tools

import android.app.Application
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.app.myapplication.CustomSnackbar
import com.careradish.roastingboulevard.classes.User
import com.google.android.material.tabs.TabLayout

class App : Application() {
    companion object {
        var user:User?=null
        lateinit var context: Context
        lateinit var contentView: View
        var foodSnackBar: CustomSnackbar?=null
        lateinit var tabLayout: TabLayout
        val logged=user!=null
        fun hideFoodSnackbar() {
            if(foodSnackBar!=null) {
                foodSnackBar!!.dismiss()
                foodSnackBar=null
            }
            UnlockTabs()
        }

        fun LockTabs(){
            tabLayout.isClickable=false
        }
        fun UnlockTabs(){
            tabLayout.isClickable=true
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