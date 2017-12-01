package com.example.amalip.reportomas.Report

import com.example.amalip.reportomas.Models.Report
import com.google.android.gms.maps.model.LatLng

/**
 * Created by Amalip on 11/30/2017.
 */
interface ReportContract {

    interface View {
        fun setMarker(latLng : LatLng)
    }

    interface Presenter {
        fun getLocation(street : String)
        fun saveData(report : Report)
    }

    interface Interactor {
        fun saveData(report: Report)
    }

}