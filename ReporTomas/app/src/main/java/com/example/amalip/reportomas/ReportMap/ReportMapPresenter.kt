package com.example.amalip.reportomas.ReportMap

import com.example.amalip.reportomas.Utilities.BaseActivity
import com.google.android.gms.maps.model.LatLng

/**
 * Created by Amalip on 12/1/2017.
 */
class ReportMapPresenter (activity : BaseActivity, fragment : ReportMapFragment) : ReportMapContract.Presenter {
    val activity = activity
    val fragment = fragment
    val interactor = ReportMapInteractor(activity, this)

    override fun getMarkers() {
        interactor.getMarkers()
    }

    override fun setMarkers(latLng: LatLng, title : String, body : String) {
        fragment.setMarkers(latLng, title, body)
    }
}