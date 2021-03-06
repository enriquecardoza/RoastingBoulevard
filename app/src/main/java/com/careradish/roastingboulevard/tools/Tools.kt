package com.careradish.roastingboulevard.tools

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class Tools {
    companion object {
        fun isEmailValid(email: String?): Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher: Matcher = pattern.matcher(email)
            return matcher.matches()
        }

        fun getToday(): String? {
            val cal: Calendar = Calendar.getInstance()
            val date: Date = cal.time

            val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            return format1.format(date)
        }

        fun findSuitableParent(view: View?): ViewGroup? {
            var mView = view
            var fallback: ViewGroup? = null
            do {
                if (mView is CoordinatorLayout) {
                    // We've found a CoordinatorLayout, use it
                    return mView
                } else if (mView is FrameLayout) {
                    if (mView.id == android.R.id.content) {
                        // If we've hit the decor content view, then we didn't find a CoL in the
                        // hierarchy, so use it.
                        return mView
                    } else {
                        // It's not the content view but we'll use it as our fallback
                        fallback = mView
                    }
                }

                if (mView != null) {
                    // Else, we will loop and crawl up the view hierarchy and try to find a parent
                    val parent = mView.parent
                    mView = if (parent is View) parent else null
                }
            } while (mView != null)

            // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
            return fallback
        }

        fun getColor(@ColorRes stringRes: Int): Int {
            return App.context!!.resources.getColor(stringRes)
        }
        fun  getDisplayLanguage():Locale{
           return App.context.resources.configuration.locales[0]
        }

        fun getResourceNameByID(resourceID: Int): String? {

             return App.context.resources.getResourceName(resourceID)
        }
        fun getFullResourceNameByID(resourceID: Int): String? {
            val value = TypedValue()
            App.context.resources.getValue(resourceID, value, true)
            value.string.toString()
            val arr=value.string.toString().split("/")
            return arr[arr.size-1]

        }
    }


}

object TranslationStrings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return App.context.getString(stringRes, *formatArgs)
    }

    fun get(stringRes: String): String {


        val text_id: Int = App.context.resources!!
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

    private fun getLocalizedResources(desiredLocale: Locale?): Resources {
        var conf: Configuration = App.context.resources.configuration
        conf = Configuration(conf)
        conf.setLocale(desiredLocale)
        val localizedContext: Context = App.context.createConfigurationContext(conf)
        return localizedContext.resources
    }

    fun getTranslationsStrings(@StringRes stringRes: Int, desiredLocale: Locale?): String {
        val resources= getLocalizedResources(desiredLocale)
        return resources.getString(stringRes)
    }

    fun getTranslationsStrings(@StringRes stringRes: Int): ArrayList<String> {

        var arr = arrayListOf<String>()
        for (i in App.Languages.values()) {
            val string=getTranslationsStrings(stringRes, i.state)
            arr.add(string)
        }
        return arr
    }
}