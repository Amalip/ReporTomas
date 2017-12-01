package com.example.amalip.reportomas.Register

import com.example.amalip.reportomas.BaseActivities.Main.MainActivity
import com.example.amalip.reportomas.Utilities.ActivitiesUtils
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.example.amalip.reportomas.Utilities.Validations
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult

/**
 * Created by Amalip on 11/29/2017.
 */
class RegisterPresenter (activity : BaseActivity, fragment : RegisterFragment) : RegisterContract.Presenter {
    val activity = activity
    val fragment = fragment
    var interactor = RegisterInteractor(activity, this)
    val validations = Validations(activity)

    override fun registerFB() {
        val activity = activity as MainActivity
        fragment.btnLoginFb.registerCallback(activity.mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                interactor.registerFB(loginResult.accessToken)
            }

            override fun onCancel() {
                ActivitiesUtils.showToat(activity, "Lol")
            }

            override fun onError(error: FacebookException) {
                ActivitiesUtils.showToat(activity, "Lol")
            }
        })
    }

    override fun register(name: String, lastName: String, email: String, password : String, phone: String) {
        if(validations.isConnected())
            if(validations.isValidEmail(email)) {
                if(!name.isEmpty() || !lastName.isEmpty() || !email.isEmpty() || !password.isEmpty() || !phone.isEmpty())
                    interactor.register(name, lastName, email, validations.doSHA256(password), phone)
                else
                    ActivitiesUtils.showToat(activity, "Completa todos los campos prro!")
            }
            else
                ActivitiesUtils.showToat(activity, "Correo no válido")
    }

}