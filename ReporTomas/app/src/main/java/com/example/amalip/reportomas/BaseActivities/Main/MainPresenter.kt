package com.example.amalip.reportomas.BaseActivities.Main

import android.content.Intent
import com.example.amalip.reportomas.BaseActivities.Second.SecondActivity
import com.example.amalip.reportomas.Login.LoginFragment
import com.example.amalip.reportomas.Register.RegisterFragment
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Amalip on 11/27/2017.
 */
class MainPresenter(val activity : MainActivity) : MainContract.Presenter {
    override fun setView(user: FirebaseUser?) {
        if(user == null)
            activity.setFragment(LoginFragment())
        else
            activity.startActivity(Intent(activity, SecondActivity().javaClass))
    }

    override fun onBackPressed() {
        val fragment = activity.getSupportFragmentManager().findFragmentById(activity.placeHolder.id)
        if(fragment is LoginFragment)
            activity.setFragment(RegisterFragment())
        else if(fragment is RegisterFragment)
            activity.setFragment(LoginFragment())
    }

    override fun onActivityResult() {
        val fragment = activity.getSupportFragmentManager().findFragmentById(activity.placeHolder.id)
        if(fragment is LoginFragment)
            fragment.presenter.facebook()
        else if(fragment is RegisterFragment)
            fragment.presenter.registerFB()
    }
}