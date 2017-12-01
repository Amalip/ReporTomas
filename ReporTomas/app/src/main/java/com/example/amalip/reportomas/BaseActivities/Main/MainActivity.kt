package com.example.amalip.reportomas.BaseActivities.Main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.FrameLayout
import com.example.amalip.reportomas.R
import com.example.amalip.reportomas.Utilities.ActivitiesUtils
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import kotterknife.bindView

class MainActivity : BaseActivity(), MainContract.View {
    val placeHolder : FrameLayout by bindView(R.id.mainPlaceHolder)
    val presenter = MainPresenter(this)
    val mAuth = FirebaseAuth.getInstance()
    val mCallbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = mAuth.currentUser
        presenter.setView(user)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setFragment(fragment: Fragment) {
        ActivitiesUtils.addFragmentToActivity(this, supportFragmentManager, fragment
                , placeHolder!!.id)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult()
    }
}