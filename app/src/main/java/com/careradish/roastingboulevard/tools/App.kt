package com.careradish.roastingboulevard.tools

import android.app.Application
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.app.myapplication.CustomSnackbar

class App : Application() {
    companion object {
        lateinit var context: Context
        lateinit var contentView: View
        var foodSnackBar: CustomSnackbar?=null

        fun hideFoodSnackbar() {
            if(foodSnackBar!=null) {
                foodSnackBar!!.dismiss()
                foodSnackBar=null
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }


}

object Strings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return App.context.getString(stringRes, *formatArgs)
    }
}