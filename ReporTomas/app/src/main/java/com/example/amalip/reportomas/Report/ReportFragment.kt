package com.example.amalip.reportomas.Report

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import com.example.amalip.reportomas.Models.Report
import com.example.amalip.reportomas.R
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import kotterknife.bindView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_report.*

/**
 * A simple [Fragment] subclass.
 */
class ReportFragment : Fragment(), OnMapReadyCallback, ReportContract.View {
    val edtStreet: EditText by bindView(R.id.edtStreet)
    val edtNumber: EditText by bindView(R.id.edtNumber)
    val edtStreetAbj1: EditText by bindView(R.id.edtAdjacents1)
    val edtStreetAbj2: EditText by bindView(R.id.edtAdjacents2)
    val rbEnergy: RadioButton by bindView(R.id.rbEnergy)
    val rbTransit: RadioButton by bindView(R.id.rbTransit)
    val rbWater: RadioButton by bindView(R.id.rbWater)
    val rbOther: RadioButton by bindView(R.id.rbOther)
    val edtDescription: EditText by bindView(R.id.edtDescription)
    val btnReport: Button by bindView(R.id.btnReport)
    var rbVal: Int = 0
    var street: String = ""
    var number: String = ""
    lateinit var mMap : GoogleMap
    lateinit var latLng : LatLng
    lateinit var mapFrag : SupportMapFragment
    lateinit var presenter: ReportPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_report, container, false)

        val activity = context as BaseActivity
        presenter = ReportPresenter(activity, this)
        mapFrag = getChildFragmentManager().findFragmentById(R.id.map) as SupportMapFragment
        mapFrag.getMapAsync(this)

        return view
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map!!
    }

    override fun setMarker(latLng: LatLng) {
        this.latLng = latLng
        mMap.addMarker(MarkerOptions().position(latLng))
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder()
                .target(latLng).zoom(15f).build()))
    }

    override fun onResume() {
        super.onResume()
        //RadioButtons
        rbEnergy.setOnClickListener { rbVal = 1 }
        rbTransit.setOnClickListener { rbVal = 2 }
        rbWater.setOnClickListener { rbVal = 3 }
        rbOther.setOnClickListener { rbVal = 4 }

        edtStreet.setOnFocusChangeListener { _ , hasFocus ->
            if (!hasFocus) {
                if (!number.isEmpty())
                    presenter.getLocation("${edtStreet.text} ${number}")
                else
                    street = edtStreet.text.toString()
            }
        }

        edtNumber.setOnFocusChangeListener { _ , hasFocus ->
            if (!hasFocus) {
                if (!street.isEmpty())
                    presenter.getLocation("${street} ${edtNumber.text}")
                else
                    number = edtNumber.text.toString()
            }
        }

        btnReport.setOnClickListener { presenter.saveData(Report(if(!street.isEmpty()) street else edtStreet.text.toString()
                , if(!number.isEmpty()) number else edtNumber.text.toString(), edtAdjacents1.text.toString()
                , edtAdjacents2.text.toString(), rbVal, edtDescription.text.toString(), latLng.latitude, latLng.longitude)) }
    }
}