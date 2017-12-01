package com.example.amalip.reportomas.Utilities

import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by Amalip on 11/29/2017.
 */
class Validations (activity: BaseActivity) {
    val activity = activity

    fun doSHA256(password: String): String {
        var digest: MessageDigest? = null
        try {
            digest = MessageDigest.getInstance("SHA-256")
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }

        digest!!.reset()
        val data = digest.digest(password.toByteArray())
        return String.format("%0" + data.size * 2 + 'x', BigInteger(1, data))
    }

    fun isConnected(): Boolean {
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        if (netInfo != null && netInfo.isConnectedOrConnecting)
            return true
        else {
            ActivitiesUtils.showToat(activity, "No estas conectado a internet")
            return false
        }
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}