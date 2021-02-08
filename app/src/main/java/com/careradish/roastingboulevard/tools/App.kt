package com.careradish.roastingboulevard.tools

import android.app.Application
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.careradish.roastingboulevard.adapters.CustomSnackbar
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.classes.User
import com.google.android.material.tabs.TabLayout


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
            UnlockTabs()
        }

        fun LockTabs() {
            tabLayout.visibility = View.GONE
        }

        fun UnlockTabs() {
            tabLayout.visibility = View.VISIBLE
        }

        fun Init(contexto: Context) {
            context = contexto
        }
    }

    // override fun onCreate() {
    //     super.onCreate()
    //     context = this
    // }
    val PREFS_NAME: String = "MyPrefsFile"
    var test = false

    private fun store() {
        val settings = getSharedPreferences(PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putBoolean("test", test) // Commit editings editor.commit();
    }

    private fun recover() {
        val settings = getSharedPreferences(PREFS_NAME, 0)
        test = settings.getBoolean("test", false)
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