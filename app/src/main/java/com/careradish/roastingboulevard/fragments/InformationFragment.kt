package com.careradish.roastingboulevard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.careradish.roastingboulevard.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_information.view.*


class InformationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val status = GooglePlayServicesUtil
            .isGooglePlayServicesAvailable(container?.context)
        if (status != ConnectionResult.SUCCESS){}
        // Inflate the layout for this fragment
        var tempInflater = inflater.inflate(R.layout.fragment_information, container, false)
        val mapFragment = tempInflater.mapView
        mapFragment.onCreate(savedInstanceState);
        mapFragment.onResume()
        mapFragment.getMapAsync(OnMapReadyCallback {

            val latLng = LatLng(59.95, 10.75)
            //mapFragment.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
            val cameraPosition = CameraPosition.Builder().target(latLng).zoom(14.0f).build()
            val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
            it.moveCamera(cameraUpdate)
        })

        return tempInflater
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InformationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}