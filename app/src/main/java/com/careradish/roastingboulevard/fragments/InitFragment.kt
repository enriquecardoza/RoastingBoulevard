package com.careradish.roastingboulevard.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.careradish.roastingboulevard.R
import com.careradish.roastingboulevard.activities.MainActivity
import com.careradish.roastingboulevard.tools.App
import com.careradish.roastingboulevard.tools.DeveloperFreshData
import com.careradish.roastingboulevard.tools.Tools
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_init.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [InitFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitFragment : Fragment() {

    lateinit var mapFragment:MapView
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var tempInflater = inflater.inflate(R.layout.fragment_init, container, false)
        tempInflater.buttonInitStartOrder.setOnClickListener {

            MainActivity.changueSelectedTab(1)

        }
        image =tempInflater.findViewById(R.id.imageViewInitUser)
        mapFragment = tempInflater.mapViewInit
        SetDebugUpload()
        SetMap(savedInstanceState, container, tempInflater)
        //if (App.logged)//no carga tan rapido
        //    tempInflater.textViewInitWelcome.text=TranslationStrings.get(R.string.init_welcome)+" ${App.user?.name}"
        return tempInflater
    }
    private var notificationManager: NotificationManager? = null

    private fun SetDebugUpload() {
        image.setOnClickListener {


            DeveloperFreshData.UploadAllData()
            Toast.makeText(context, "Created", Toast.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InitFragment().apply {

            }
    }

    private fun SetMap(savedInstanceState: Bundle?, container: ViewGroup?, tempInflater: View) {
        val status = GooglePlayServicesUtil
            .isGooglePlayServicesAvailable(container?.context)
        if (status == ConnectionResult.SUCCESS) {

            mapFragment.onCreate(savedInstanceState)
            try {
                MapsInitializer.initialize(this.activity)
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
            }
            //mapFragment.onResume()
            mapFragment.getMapAsync(OnMapReadyCallback {

                val latLng = LatLng(40.294237, -3.746450)
                val cameraPosition = CameraPosition.Builder().target(latLng).zoom(15.0f).build()
                val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
                it.moveCamera(cameraUpdate)
                it.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title("")
                )
                it.uiSettings.isMyLocationButtonEnabled = true
                it.uiSettings.isZoomControlsEnabled = true
                it.setOnMyLocationButtonClickListener { false }
            })
        }
    }
    override fun onResume() {
        mapFragment.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapFragment.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapFragment.onLowMemory()
    }

}