package com.example.amalip.reportomas.Login

import android.content.Intent
import com.example.amalip.reportomas.BaseActivities.Second.SecondActivity
import com.example.amalip.reportomas.Utilities.ActivitiesUtils
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.facebook.AccessToken
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Amalip on 11/28/2017.
 */
class LoginInteractor (activity: BaseActivity, presenter: LoginPresenter) : LoginContract.Interactor {
    val activity = activity
    val presenter = presenter
    val mAuth = FirebaseAuth.getInstance()

    override fun login(username: String, password: String) {
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                activity.startActivity(Intent(activity, SecondActivity().javaClass))
                ActivitiesUtils.showToat(activity, "Binevenido, prro!")
            } else
                ActivitiesUtils.showToat(activity, task.exception!!.localizedMessage)
        }
    }

    override fun facebookLogin(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if(!task.isSuccessful)
                        ActivitiesUtils.showToat(activity, task.exception!!.localizedMessage)
                }
    }
}