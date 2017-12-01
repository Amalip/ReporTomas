package com.example.amalip.reportomas.ReportMap

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amalip.reportomas.R
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass.
 */
class ReportMapFragment : Fragment(), OnMapReadyCallback, ReportMapContract.View, LocationListener {
    lateinit var mMap: GoogleMap
    lateinit var latLng: LatLng
    lateinit var mapFrag: SupportMapFragment
    lateinit var presenter: ReportMapPresenter
    var firstRun = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_report_map, container, false)

        val activity = context as BaseActivity
        presenter = ReportMapPresenter(activity, this)
        presenter.getMarkers()

        mapFrag = getChildFragmentManager().findFragmentById(R.id.mapB) as SupportMapFragment
        mapFrag.getMapAsync(this)

        return view
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map!!
    }

    override fun setMarkers(latLng: LatLng, title : String, body : String) {
        mMap.addMarker(MarkerOptions().position(latLng)
                .title(title)
                .snippet(body))
        if(firstRun) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder()
                    .target(latLng).zoom(15f).build()))
            firstRun = false
        }
    }

    override fun onLocationChanged(location: Location?) {
        latLng = LatLng(location!!.latitude, location.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }
}