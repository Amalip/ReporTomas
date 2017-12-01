package com.example.amalip.reportomas.Login

import android.content.Intent
import com.example.amalip.reportomas.BaseActivities.Main.MainActivity
import com.example.amalip.reportomas.BaseActivities.Second.SecondActivity
import com.example.amalip.reportomas.Register.RegisterFragment
import com.example.amalip.reportomas.Utilities.ActivitiesUtils
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.example.amalip.reportomas.Utilities.Validations
import kotlinx.android.synthetic.main.activity_main.*
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback

/**
 * Created by Amalip on 11/28/2017.
 */
class LoginPresenter (activity: BaseActivity, fragment : LoginFragment) : LoginContract.Presenter {
    val activity = activity
    val fragment = fragment
    val interactor = LoginInteractor(activity, this)
    val validations = Validations(activity)

    override fun login(username: String, password: String) {
        interactor.login(username, validations.doSHA256(password))
    }

    override fun signUp() {
        ActivitiesUtils.addFragmentToActivity(activity, activity.supportFragmentManager, RegisterFragment()
                , activity.mainPlaceHolder.id)
    }

    override fun facebook() {
        val activity = activity as MainActivity
        fragment.btnLoginFb.registerCallback(activity.mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                activity.startActivity(Intent(activity, SecondActivity().javaClass))
                ActivitiesUtils.showToat(activity, "Binevenido, prro!")
                interactor.facebookLogin(loginResult.accessToken)
            }

            override fun onCancel() {
                ActivitiesUtils.showToat(activity, "Lol")
            }

            override fun onError(error: FacebookException) {
                ActivitiesUtils.showToat(activity, "Lol")
            }
        })
    }
}