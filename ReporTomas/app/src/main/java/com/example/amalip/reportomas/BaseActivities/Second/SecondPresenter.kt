package com.example.amalip.reportomas.BaseActivities.Second

import com.example.amalip.reportomas.Utilities.GPSHelper

/**
 * Created by Amalip on 12/1/2017.
 */
class SecondPresenter (activity: SecondActivity) : SecondContract.Presenter {
    val activity = activity

    override fun setFragment() {
        activity.setFragment()
    }

    override fun enableGPS(resultCode : Int) {
        if(resultCode == 0)
            GPSHelper(activity).enableGPS()
    }
}