package com.example.amalip.reportomas.BaseActivities.Second

/**
 * Created by Amalip on 12/1/2017.
 */
interface SecondContract {

    interface View{
        fun setFragment()
    }

    interface Presenter{
        fun setFragment()
        fun enableGPS(resultCode : Int)
    }

}