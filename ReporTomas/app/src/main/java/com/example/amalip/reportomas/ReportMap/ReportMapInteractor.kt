package com.example.amalip.reportomas.ReportMap

import com.example.amalip.reportomas.Models.Report
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by Amalip on 12/1/2017.
 */
class ReportMapInteractor (activity: BaseActivity, presenter : ReportMapPresenter) : ReportMapContract.Interactor {
    val activity = activity
    val presenter = presenter
    val database = FirebaseDatabase.getInstance().getReference("reports")

    override fun getMarkers() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (report in snapshot.children.toList()) {
                    val repo = report.getValue(Report().javaClass)!!

                    val type = when (repo.category){
                        1 -> "Energy"
                        2 -> "Transit"
                        3 -> "Water"
                        4 -> "Other"
                        else -> ""
                    }

                    presenter.setMarkers(LatLng(repo.lat, repo.lng), type,
                            "${type} problem occurred at ${repo.street} ${repo.number}")
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}