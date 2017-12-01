package com.example.amalip.reportomas.Report

import com.example.amalip.reportomas.Models.Report
import com.example.amalip.reportomas.Utilities.ActivitiesUtils
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_second.*

/**
 * Created by Amalip on 11/30/2017.
 */
class ReportInteractor(activity: BaseActivity, presenter: ReportPresenter) : ReportContract.Interactor {
    val activity = activity
    val presenter = presenter
    val database = FirebaseDatabase.getInstance().getReference("reports")

    override fun saveData(report: Report) {
        try {
            database.child(database.push().getKey()).setValue(report)
            ActivitiesUtils.showToat(activity, "Registrado!")
            ActivitiesUtils.addFragmentToActivity(activity, activity.supportFragmentManager
                    , ReportFragment(), activity.secondPlaceHolder.id)
        } catch (ex: Exception) {
            ActivitiesUtils.showToat(activity, "Error!")
        }
    }
}