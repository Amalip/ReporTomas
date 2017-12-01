package com.example.amalip.reportomas.BaseActivities.Main

import android.support.v4.app.Fragment
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Amalip on 11/27/2017.
 */
interface MainContract {

    interface View{
        fun setFragment(fragment: Fragment)
    }

    interface Presenter{
        fun setView(user : FirebaseUser?)
        fun onBackPressed()
        fun onActivityResult()
    }

    interface Interactor{

    }
}