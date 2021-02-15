package com.careradish.roastingboulevard.tools

import android.app.*
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.edit
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.adapters.CustomSnackbar
import com.careradish.roastingboulevard.classes.Category
import com.careradish.roastingboulevard.classes.Delivery
import com.careradish.roastingboulevard.classes.Food
import com.careradish.roastingboulevard.classes.User
import com.google.android.material.tabs.TabLayout
import java.util.*


class App : Application() {
    public enum class Languages(val state: Locale?) {
        default(null),english(Locale.ENGLISH), español(Locale.forLanguageTag("es"))
    }

    companion object {

        var initiated = false;
        var user: User? = null
        lateinit var context: Context
        lateinit var contentView: View
        var actualSnackBar: CustomSnackbar? = null
        lateinit var tabLayout: TabLayout
        val logged: Boolean get() = user != null
        var actualDelivery: Delivery? = null
        var delivering = false
        var deliveringDelivery: Delivery? = null

        fun Init(activity: Activity) {
            if (initiated)
                return
            context = activity.applicationContext
            initiated = true
            notificationManager =
                activity.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager
            createNotificationChannel(
                "com.ebookfrenzy.notifydemo.news",
                "NotifyDemo News",
                "Example News Channel"
            )
        }
        fun hideFoodSnackbar() {
            if (actualSnackBar != null) {
                actualSnackBar!!.dismiss()
                actualSnackBar = null
            }
            MainActivity.UnlockTabs()
        }

        fun getLanguagePos():Int{
            for (c in 0..Languages.values().size) {
                val name=Languages.values()[c].state
                val lang=Tools.getDisplayLanguage().language
                if (name.toString()== lang) {
                    return c
                }
            }
            return 0
        }

        fun getFoodLanguagePos(food: Food):Int{
            val arr= arrayListOf<Int>(food.name.size,food.description.size,food.ingredients.size)
            val lowestCount=arr[arr.indexOf(Collections.min(arr))]
            for (c in 0..lowestCount) {
                val name=Languages.values()[c].state
                val lang=Tools.getDisplayLanguage().language
                if (name.toString()== lang) {
                    return c
                }
            }
            return 0
        }
        fun getCategoryLanguagePos(category: Category):Int{
            for (c in 0..category.name.size) {
                val name=Languages.values()[c].state
                val lang=Tools.getDisplayLanguage().language
                if (name.toString()== lang) {
                    return c
                }
            }
            return 0
        }
        //region prefUser
        fun recoverPrefUser(activity: Activity) {
            val sharedPref = context.getSharedPreferences(
                Constants.prefUser, MODE_PRIVATE
            )
            val email = sharedPref.getString(Constants.prefUserLoggedEmail, "")
            val password = sharedPref.getString(Constants.prefUserLoggedPassword, "")
            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                FirebaseConnection.LoginUser(email!!, password!!, activity)
            }
        }

        public fun storePrefUser(email: String, password: String) {
            val sharedPref = context.getSharedPreferences(
                Constants.prefUser, MODE_PRIVATE
            )
            val editor = sharedPref.edit()
            editor.putString(Constants.prefUserLoggedEmail, email)
            editor.putString(Constants.prefUserLoggedPassword, password)
            editor.apply()
        }

        public fun erasePrefUser() {
            val sharedPref = context.getSharedPreferences(
                Constants.prefUser, MODE_PRIVATE
            )
            sharedPref.edit(commit = true) {
                clear()
            }
        }
//endregion

        private var notificationManager: NotificationManager? = null

        private fun createNotificationChannel(
            id: String, name: String,
            description: String
        ) {

            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(id, name, importance)

            channel.description = description
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager?.createNotificationChannel(channel)
        }

        fun sendNotification() {
            val notificationID = 101

            val channelID = "com.ebookfrenzy.notifydemo.news"

            val notification = Notification.Builder(
                App.context,
                channelID
            )
                .setContentTitle(TranslationStrings.get(R.string.notification_tittle))
                //.setContentText("This is an  example notification.")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setChannelId(channelID)
                .build()

            notificationManager?.notify(notificationID, notification)
        }

    }
}

