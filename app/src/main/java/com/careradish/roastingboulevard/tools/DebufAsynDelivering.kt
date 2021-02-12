package com.careradish.roastingboulevard.tools

import android.os.AsyncTask
import android.view.View
import com.careradish.roastingboulevard.classes.Delivery

class DebufAsynDelivering : AsyncTask<Void?, Int?, String>() {

    override fun doInBackground(vararg params: Void?): String? {
        for (i in 0..3) {
            try {
                FirebaseConnection.updateDeliveryState(Delivery.DeliveryState.values()[i])
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        return "true"
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(s: String) {
        super.onPostExecute(s)
        FirebaseConnection.unAttachToDeliveryState()
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }
}