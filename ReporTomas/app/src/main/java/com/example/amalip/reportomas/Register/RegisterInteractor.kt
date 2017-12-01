package com.example.amalip.reportomas.Register

import android.content.Intent
import com.example.amalip.reportomas.BaseActivities.Second.SecondActivity
import com.example.amalip.reportomas.Models.Users
import com.example.amalip.reportomas.Utilities.ActivitiesUtils
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.facebook.AccessToken
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider

/**
 * Created by Amalip on 11/29/2017.
 */
class RegisterInteractor (activity : BaseActivity, presenter : RegisterPresenter) : RegisterContract.Interactor {
    val activity = activity
    var name: String = ""
    var lastName: String = ""
    var email: String = ""
    var password: String = ""
    var phone: String = ""
    val database = FirebaseDatabase.getInstance().getReference("users")
    val mAuth = FirebaseAuth.getInstance()

    override fun register(name: String, lastName: String, email: String, password: String, phone: String) {
        try {
            this.name = name
            this.lastName = lastName
            this.email = email
            this.password = password
            this.phone = phone

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    try {
                        val firebaseUser = mAuth.currentUser!!
                        database.child(firebaseUser.uid).setValue(Users(name, lastName, email, password, phone))
                        activity.startActivity(Intent(activity, SecondActivity().javaClass))
                        ActivitiesUtils.showToat(activity, "Bienvenido!")
                    }
                    catch (ex : Exception){
                        ActivitiesUtils.showToat(activity, "Error!")
                    }
                } else {
                    ActivitiesUtils.showToat(activity, task.exception!!.localizedMessage)
                }
            }

        } catch (ex: Exception) {
            ActivitiesUtils.showToat(activity, "Error al registrar")
        }
    }

    override fun registerFB(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = mAuth.currentUser!!
                        database.child(firebaseUser.uid).setValue(Users(firebaseUser.displayName.toString()
                                , "", firebaseUser.email.toString(), "", ""))
                        activity.startActivity(Intent(activity, SecondActivity().javaClass))
                        ActivitiesUtils.showToat(activity, "Bienvenido!")
                    }
                    else {
                        ActivitiesUtils.showToat(activity, task.exception!!.localizedMessage)
                    }
                }
    }
}