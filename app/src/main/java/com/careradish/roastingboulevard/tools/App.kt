package com.careradish.roastingboulevard.tools

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes

class App : Application() {
    companion object {
        lateinit var instance: Context private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun setContext(context: Context) {
        instance = context
    }
}

object Strings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return App.instance.getString(stringRes, *formatArgs)
    }
}