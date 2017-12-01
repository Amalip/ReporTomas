package com.example.amalip.reportomas.BaseActivities.Second

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.amalip.reportomas.R
import com.example.amalip.reportomas.ReportMap.ReportMapFragment
import com.example.amalip.reportomas.Utilities.ActivitiesUtils
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.example.amalip.reportomas.Utilities.DrawerMenu
import kotterknife.bindView

class SecondActivity : BaseActivity(), SecondContract.View {
    val placeHolder : FrameLayout by bindView(R.id.secondPlaceHolder)
    val imgHam : ImageView by bindView(R.id.imgHam)
    val presenter = SecondPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DrawerMenu().initDrawer(this)
        presenter.setFragment()
        presenter.enableGPS(0)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        imgHam.setOnClickListener { DrawerMenu().initDrawer(this) }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_second
    }

    override fun onBackPressed() {

    }

    override fun setFragment() {
        ActivitiesUtils.addFragmentToActivity(this, supportFragmentManager, ReportMapFragment(), placeHolder.id)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.enableGPS(resultCode)
    }
}