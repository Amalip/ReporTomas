package com.example.amalip.reportomas.Report

import android.location.Address
import android.location.Geocoder
import com.example.amalip.reportomas.Models.Report
import com.example.amalip.reportomas.Utilities.ActivitiesUtils
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.example.amalip.reportomas.Utilities.Validations
import com.google.android.gms.maps.model.LatLng

/**
 * Created by Amalip on 11/30/2017.
 */
class ReportPresenter(activity: BaseActivity, fragment : ReportFragment) : ReportContract.Presenter{
    val activity = activity
    val fragment = fragment
    val interactor = ReportInteractor(activity, this)
    val validations = Validations(activity)

    override fun getLocation(street : String) {
        var geocoder = Geocoder(activity)
        var addresses : List<Address>
        addresses = geocoder.getFromLocationName(street, 1)
        if(addresses.size > 0)
            fragment.setMarker(LatLng(addresses[0].latitude, addresses[0].longitude))
    }

    override fun saveData(report: Report) {
        if(validations.isConnected()) {
            if (!report.street.isEmpty() && !report.number.isEmpty() && report.category != 0 && !report.description.isEmpty())
                interactor.saveData(report)
            else
                ActivitiesUtils.showToat(activity, "Completar todos los campos!")
        }
    }
}