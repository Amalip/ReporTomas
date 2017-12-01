package com.example.amalip.reportomas.Utilities

import android.content.Intent
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.example.amalip.reportomas.BaseActivities.Main.MainActivity
import com.example.amalip.reportomas.Report.ReportFragment
import com.example.amalip.reportomas.ReportMap.ReportMapFragment
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_second.*

/**
 * Created by Amalip on 11/27/2017.
 */

class DrawerMenu {
    fun initDrawer(activity: BaseActivity) {
        val fragment = activity.getSupportFragmentManager().findFragmentById(activity.secondPlaceHolder.id)

        activity.drawer {
            primaryItem("REPORTOMAS") {
                enabled = false
            }
            divider {}
            primaryItem("ReportMap") {
                onClick { v ->
                    if (fragment !is ReportMapFragment)
                        ActivitiesUtils.addFragmentToActivity(activity, activity.supportFragmentManager
                                , ReportMapFragment(), activity.secondPlaceHolder.id)
                    false
                }
            }
            primaryItem("New Report") {
                onClick { v ->
                    if (fragment !is ReportFragment)
                        ActivitiesUtils.addFragmentToActivity(activity, activity.supportFragmentManager
                                , ReportFragment(), activity.secondPlaceHolder.id)
                    false
                }
            }
            primaryItem("Logout") {
                onClick { v ->
                    FirebaseAuth.getInstance().signOut()
                    LoginManager.getInstance().logOut()
                    activity.startActivity(Intent(activity, MainActivity().javaClass))
                    false
                }
            }
        }.openDrawer()
    }
}