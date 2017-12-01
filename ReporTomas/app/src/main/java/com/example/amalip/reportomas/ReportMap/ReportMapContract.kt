package com.example.amalip.reportomas.ReportMap

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Amalip on 12/1/2017.
 */
interface ReportMapContract {

    interface View{
        fun setMarkers(latLng: LatLng, title : String, body : String)
    }

    interface Presenter{
        fun getMarkers()
        fun setMarkers(latLng: LatLng, title : String, body : String)
    }

    interface Interactor{
        fun getMarkers()
    }
}