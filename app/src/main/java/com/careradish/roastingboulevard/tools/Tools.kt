package com.careradish.roastingboulevard.tools

import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class Tools {
    companion object{
        fun isEmailValid(email: String?): Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher: Matcher = pattern.matcher(email)
            return matcher.matches()
        }
        fun getToday(): String? {
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