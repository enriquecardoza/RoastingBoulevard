package com.careradish.roastingboulevard.tools

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.edit
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.adapters.CustomSnackbar
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.classes.User
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*


class App : Application() {
    companion object {

        var user: User? = null
        lateinit var context: Context
        lateinit var contentView: View
        var foodSnackBar: CustomSnackbar? = null
        lateinit var tabLayout: TabLayout
        val logged: Boolean get() = user != null
        var actualDelivery: Delivery? = null


        fun hideFoodSnackbar() {
            if (foodSnackBar != null) {
                foodSnackBar!!.dismiss()
                foodSnackBar = null
            }
            MainActivity.UnlockTabs()
        }



        fun Init(activity: Activity) {
            context = activity.applicationContext
            recoverPrefUser(activity)

        }

        private fun recoverPrefUser(activity: Activity) {
            val sharedPref = context?.getSharedPreferences(
                Constants.prefUser, MODE_PRIVATE
            )
            val email = sharedPref.getString(Constants.prefUserLoggedEmail, "")
            val password = sharedPref.getString(Constants.prefUserLoggedEmail, "")
            if (email != ""&&password!="") {
                FirebaseConnection.LoginUser(email!!,password!!,activity)
            }
        }

        public fun storePrefUser(email:String,password:String,) {
            val sharedPref = context?.getSharedPreferences(
                Constants.prefUser, MODE_PRIVATE
            )
            val editor = sharedPref.edit()
            editor.putString(Constants.prefUserLoggedEmail, email)
            editor.putString(Constants.prefUserLoggedPassword, password)
            editor.apply()
        }

        public fun erasePrefUser(){
            val sharedPref = context?.getSharedPreferences(
                Constants.prefUser, MODE_PRIVATE
            )
            sharedPref.edit(commit = true) {
                clear()
            }
        }

        public fun getToday(): String? {
            val cal: Calendar = Calendar.getInstance()
            val date: Date = cal.time

            val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")

            return format1.format(date)
        }
    }



}

object TranslationStrings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return App.context.getString(stringRes, *formatArgs)
    }

    fun get(stringRes: String): String {


        val text_id: Int = App.context.resources
            .getIdentifier(stringRes, "string", App.context.packageName)
        if (text_id == 0)
            return stringRes
        else
            return App.context.getString(text_id)
    }

    fun getKey(stringRes: String): Int {
        return App.context.resources
            .getIdentifier(stringRes, "string", App.context.packageName)
    }

    fun getKey(@StringRes stringRes: Int): String {
        return App.context.resources.getResourceEntryName(stringRes)

    }

}